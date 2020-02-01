package frc.robot.subsystems;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * 
 */

public class CanbusDistanceSensor extends SendableBase implements Sendable {
  // Message IDs

  private static final int HEARTBEAT_MESSAGE = 0x18F0FF00;
  private static final int CALIBRATION_STATE_MESSAGE = 0x0CF91200;
  private static final int MEASURED_DISTANCE_MESSAGE = 0x0CF91000;
  private static final int MEASUREMENT_QUALITY_MESSAGE = 0x0CF91100;
  private static final int RANGING_CONFIGURATION_MESSAGE = 0x0CF91300;
  private static final int DEVICE_CONFIGURATION_MESSAGE = 0x0CAAFFF9;
  private static final int kSendMessagePeriod = 0;
  // LiveWindow color update MS maximum interval (milliseconds)
  protected final static int LIVE_WINDOW_UPDATE_INTERVAL = 50;

  private static double lastDistance = 0;
  public static byte[] hwdata = new byte[8];
  private double serialNumber;
  private double partNumber;
  private double firmWare;

  public CanbusDistanceSensor() {

    // LiveWindow.add(this);

  }

  // Messages from device
  public static byte[] readHeartbeat(int id) {

    long read = CANSendReceive.readMessage(HEARTBEAT_MESSAGE, id);
    if (read != -1)
      hwdata = CANSendReceive.result;
    return hwdata;
  }

  public static int[] getSensorInfo(byte[] hwdata) {
    int[] temp = new int[3];
    // SmartDashboard.putNumber("D5", (int) hwdata[5]);
    // SmartDashboard.putNumber("D4", (int) hwdata[4]);
    temp[0] = extractValue(hwdata, 3, 1);
    temp[1] = extractValue(hwdata, 5, 4);
    temp[2] = extractValue(hwdata, 7, 6);
    return temp;
  }

  public static int findSensor(int id) {
    hwdata = readHeartbeat(id);
    if (hwdata[4] != 0) {
      return id;
    } else {
      return 999;
    }
  }

  public static int[] getDistanceMM(int id) {
    int[] temp = { 0, 0 };
    long read = CANSendReceive.readMessage(MEASURED_DISTANCE_MESSAGE, id);

    if (read == -1) {
      return temp;
    } else {
      int rangingStatus = Byte.toUnsignedInt(CANSendReceive.result[2]);
      if (rangingStatus != 0) {
        temp[0] = -rangingStatus;
        return temp;
      } else {
        // SmartDashboard.putNumber("D1",Byte.toUnsignedInt(CANSendReceive.result[1]));
        // SmartDashboard.putNumber("D0",Byte.toUnsignedInt(CANSendReceive.result[0]));

        temp[0] = extractValue(CANSendReceive.result, 1, 0);
        // temp[0 ]= CANSendReceive.result[1]<<8;
        // temp[1]= Byte.toUnsignedInt(CANSendReceive.result[0]);
        // temp[0]+=temp[1];
        temp[1] = extractValue(CANSendReceive.result, 7, 4) / 65536;
        return temp;
      }
    }
  }

  public static int[] readQuality(int id) {
    int temp[] = { 0, 0 };
    long read = CANSendReceive.readMessage(MEASUREMENT_QUALITY_MESSAGE, id);
    // SmartDashboard.putNumber("Q3",(int)CANSendReceive.result[3]);
    // SmartDashboard.putNumber("Q2",(int)CANSendReceive.result[2]);

    // SmartDashboard.putNumber("Q1",(int)CANSendReceive.result[1]);
    // SmartDashboard.putNumber("Q0",(int)CANSendReceive.result[0]);
    if (read != -1) {
      temp[0] = extractValue(CANSendReceive.result, 3, 0) / 65536;
      temp[1] = extractValue(CANSendReceive.result, 7, 4) / 65536;
    }
    return temp;
  }

  public static int[] readCalibrationState(int id) {
    int temp[] = { 0, 0, 0, 0, 0, 0, 0 };
    long read = CANSendReceive.readMessage(CALIBRATION_STATE_MESSAGE, id);

    if (read != -1) {
      temp[2] = extractValue(CANSendReceive.result, 2, 1);
      temp[0] = CANSendReceive.result[0] & 0b00001111;
      temp[1] = CANSendReceive.result[0] >> 4;
    }

    return temp;

  }

  // // Messages to device
  public static void configureRange(int id, int mode) {
    byte[] data = new byte[3];
    int interval = 100;
    data[0] = (byte) mode;

    switch (mode) {
    case 0:
      interval = 100;
      break;
    case 1:// 150ms in 2 byte format
      interval = 150;
      break;
    case 2:// 200ms in 2 byte format
      interval = 200;
      break;
    default:
      interval = 100;
      break;
    }
    ByteBuffer b = ByteBuffer.allocate(4);
    b.putInt(interval);
    byte[] result = b.array();
    data[1] = result[1];
    data[2] = result[2];

    CANSendReceive.sendMessage(RANGING_CONFIGURATION_MESSAGE | id, data, 3, kSendMessagePeriod);

  }

  public static void identifyDevice(int id) {
    byte[] hwdata = new byte[8];

    hwdata = readHeartbeat(id);

    hwdata[0] = 0x0D;
    CANSendReceive.sendMessage(DEVICE_CONFIGURATION_MESSAGE, hwdata, 6, kSendMessagePeriod);
  }

  public static void configureDevice(int oldID, int newID) {
    byte[] hwdata = new byte[8];
    if (newID >= 0 && newID < 33) {
      hwdata = readHeartbeat(oldID);
      getSensorInfo(hwdata);
      hwdata[0] = 0x0C;
      hwdata[6] = (byte) newID;
      CANSendReceive.sendMessage(DEVICE_CONFIGURATION_MESSAGE, hwdata, 7, kSendMessagePeriod);
    }
  }

  public static int extractValue(byte[] src, int high, int low) {
    int temp = 0;
    int temp1 = 0;
    int i = 0;
    temp = (src[high]);
    for (i = high - 1; i >= low; i--) {
      temp1 = Byte.toUnsignedInt(src[i]);
      temp = (temp * 256) + temp1;
    }
    return temp;
  }

  // https://www.chiefdelphi.com/t/creating-custom-smartdashboard-types-like-pidcommand/162737/8
  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("ColorProx");
    // builder.addDoubleProperty("Range Offset", () -> readCalibrationState()[2],
    // null);
    // builder.addDoubleProperty("X Position", () -> readCalibrationState()[0],
    // null);
    // builder.addDoubleProperty("Y Position", () -> readCalibrationState()[1],
    // null);

    builder.addDoubleProperty("Serial Number", () -> serialNumber, null);
    builder.addDoubleProperty("Part Number", () -> (double) partNumber, null);
    builder.addDoubleProperty("Firmware", () -> (double) firmWare, null);

    // builder.addDoubleProperty("Distance MM", () -> getDistanceMM(), null);
    // builder.addDoubleProperty("Distance Inch", () -> getDistanceMM() / 25.4,
    // null);
    // builder.addDoubleProperty("Ambient Light", () -> readQuality()[0], null);
    // builder.addDoubleProperty("Std Dev", () -> readQuality()[1], null);

  }

}