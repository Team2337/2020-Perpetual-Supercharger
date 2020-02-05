package frc.robot.subsystems;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Get important information and values and also put them on the dashboard
 */
public class CanbusDistanceSensor extends SendableBase implements Sendable {


  //////////////////////////////////
  // ---------------------------- //
  // --- SET-UP AND VARIABLES --- //
  // ---------------------------- //
  //////////////////////////////////
  
  // Message IDs
  private static final int HEARTBEAT_MESSAGE = 0x1F0B01FF;//520815103
  private static final int CALIBRATION_STATE_MESSAGE = 0x060B0300;//101384960
  private static final int MEASURED_DISTANCE_MESSAGE = 0x060B0100;//101384448
  private static final int MEASUREMENT_QUALITY_MESSAGE = 0x060B0200;//101384704
  private static final int RANGING_CONFIGURATION_MESSAGE = 0x060B0400;//101385216
  private static final int DEVICE_CONFIGURATION_MESSAGE = 0x1F0B03FF;//520815615
  private static final int kSendMessagePeriod = 0;
  // LiveWindow color update MS maximum interval (milliseconds)
  protected final static int LIVE_WINDOW_UPDATE_INTERVAL = 50;

  private static double lastDistance = 0;//unused
  public static byte[] hwdata = new byte[8];
  private double serialNumber;
  private double partNumber;
  private double firmWare;

  public CanbusDistanceSensor() {

    // LiveWindow.add(this);

  }



  //////////////////////////////////////////////
  // ---------------------------------------- //
  // --- HEARTBEAT INFORMATION EXTRACTION --- //
  // ---------------------------------------- //
  //////////////////////////////////////////////

  // Messages from device
  /**
   * Reads the information from the device.
   * @param id The ID of the sensor
   * @return Returns the bytes in an array (8).
   * <ul>
   * <li>   0 = Reversed?                </li>
   * <li> 1-3 = Hardware serial number   </li>
   * <li> 4-5 = Manufacturer part number </li>
   * <li> 6-7 = Firmware version         </li>
   * </ul>
   */
  public static byte[] readHeartbeat(int id) {

    // Reads the bytes given by the sensor.
    // If the read failed to retrieve data, do not update the results.
    long read = CANSendReceive.readMessage(HEARTBEAT_MESSAGE, id);
    if (read != -1){
      hwdata = CANSendReceive.result;
    }

    return hwdata;
  }


  /**
   * Extracts important information from the heartbeat
   * @param hwdata Data to get information from (readHeartbeat)
   * @return Returns an array with the following information (when using heartbeat):
   * <ul>
   * <li> 0: hardware serial number   </li>
   * <li> 1: manufacturer part number </li>
   * <li> 2: firmware version         </li>
   * </ul>
   */
  public static int[] getSensorInfo(byte[] hwdata) {
    int[] temp = new int[3];

    //Extract hardware serial number
    temp[0] = extractValue(hwdata, 3, 1);
    //Extract manufacturer part number
    temp[1] = extractValue(hwdata, 5, 4);
    //Extract firmware version
    temp[2] = extractValue(hwdata, 7, 6);
    return temp;
  }


  /**
   * Finds if the sensor of a certain ID exists
   * @param id The ID of the sensor
   * @return Returns the id if the sensor was found; 999 if not found
   */
  public static int findSensor(int id) {
    hwdata = readHeartbeat(id);
    if (hwdata[4] != 0) {
      return id;
    } else {
      return 999;
    }
  }



  ///////////////////////////
  // --------------------- //
  // --- READ DISTANCE --- //
  // --------------------- //
  ///////////////////////////

  /**
   * Gets the distance in MM
   * @param id The ID of the sensor
   * @return
   */
  public static double[] getDistanceMM(int id) {
    double[] temp = { 0, 0 };
    long read = CANSendReceive.readMessage(MEASURED_DISTANCE_MESSAGE, id);

    if (read == -1) {
      //If the timestamp retrieval failed, just return the array with {0,0}
      return temp;
    } else {
      //Create a variable with the status of the robot
      int rangingStatus = Byte.toUnsignedInt(CANSendReceive.result[2]);
      if (rangingStatus != 0) {
        temp[0] = -rangingStatus;
        return temp;
      } else {
        ///////////////////////
        // --- IMPORTANT --- //
        ///////////////////////
        SmartDashboard.putNumber("D1",Byte.toUnsignedInt(CANSendReceive.result[1]));//Is this for another sensor?
        SmartDashboard.putNumber("D0",Byte.toUnsignedInt(CANSendReceive.result[0]));//THIS IS THE INFORMATION WE WANT TO KEEP
        //END IMPORTANT


        temp[0] = extractValue(CANSendReceive.result, 1, 0);
        temp[1] = extractValue(CANSendReceive.result, 7, 4) / 65536;
        return temp;
      }
    }
  }



  //////////////////////////
  // -------------------- //
  // --- READ QUALITY --- //
  // -------------------- //
  //////////////////////////

  /**
   * Reads the measurement quality returned with the completed measurement
   * @param id The ID of the sensor
   * @return Returns the values in an array (2)
   * <ul>
   * <li> 0 = Ambient light in megacounts per second                           </li>
   * <li> 1 = Standard deviation of the measured distance value, in milimeters </li>
   * </ul>
   */
  public static double[] readQuality(int id) {
    double temp[] = { 0, 0 };
    long read = CANSendReceive.readMessage(MEASUREMENT_QUALITY_MESSAGE, id);

    if (read != -1) {
      temp[0] = extractValue(CANSendReceive.result, 3, 0) / 65536;
      temp[1] = extractValue(CANSendReceive.result, 7, 4) / 65536;
    }
    return temp;
  }



  /////////////////////////////
  // ----------------------- //
  // --- CONFIGURE RANGE --- //
  // ----------------------- //
  /////////////////////////////

  /**
   * Reads the calibration state of the sensor
   * @param id The ID of the sensor being read
   * @return Returns the bytes in an array (3)
   * <ul>
   * <li> 0: Y position                 </li>
   * <li> 1: X position                 </li>
   * <li> 2: Range offset in milimeters </li>
   * </ul>
   */
  public static int[] readCalibrationState(int id) {
    int temp[] = { 0, 0, 0, 0, 0, 0, 0 };
    long read = CANSendReceive.readMessage(CALIBRATION_STATE_MESSAGE, id);

    if (read != -1) {
      temp[2] = extractValue(CANSendReceive.result, 2, 1);
      // Reading bits 0-3 by excluding bits 4-7
      temp[0] = CANSendReceive.result[0] & 0b00001111;
      // Bit-shifting to read bits 4-7
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



  //////////////////////////////
  // ------------------------ //
  // --- CONFIGURE DEVICE --- //
  // ------------------------ //
  //////////////////////////////

  /**
   * 
   * @param id The ID of the device
   */
  public static void identifyDevice(int id) {
    byte[] hwdata = new byte[8];

    hwdata = readHeartbeat(id);

    hwdata[0] = 0x0D;//13
    CANSendReceive.sendMessage(DEVICE_CONFIGURATION_MESSAGE, hwdata, 6, kSendMessagePeriod);
  }

  /**
   * Configures the device. Used in ConfigureDevice.java
   * @param oldID Old ID of the device
   * @param newID Current ID of the device
   */
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



  ///////////////////////////
  // --------------------- //
  // --- OTHER METHODS --- //
  // --------------------- //
  ///////////////////////////

  /**
   * Extracts the bits in a byte into a single integer
   * @param src Byte to get the extracted bits from
   * @param high The highest bit to be read
   * @param low The lowest bit to be read
   * @return An integer containing the specified range of bits in the byte strung together
   */
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



  ///////////////////////////////////
  // ----------------------------- //
  // --- ADD DOUBLE PROPERTIES --- //
  // ----------------------------- //
  ///////////////////////////////////

  // https://www.chiefdelphi.com/t/creating-custom-smartdashboard-types-like-pidcommand/162737/8
  @Override
  public void initSendable(SendableBuilder builder) {

    //This has some things that could probably be used but currently aren't.
    //Keep them in case

    builder.setSmartDashboardType("ColorProx");
    // builder.addDoubleProperty("Range Offset", () -> readCalibrationState()[2], null);
    // builder.addDoubleProperty("X Position", () -> readCalibrationState()[0], null);
    // builder.addDoubleProperty("Y Position", () -> readCalibrationState()[1], null);

    builder.addDoubleProperty("Serial Number", () -> serialNumber, null);
    builder.addDoubleProperty("Part Number", () -> (double) partNumber, null);
    builder.addDoubleProperty("Firmware", () -> (double) firmWare, null);

    builder.addDoubleProperty("Distance MM", () -> getDistanceMM(0)[0], null);
    builder.addDoubleProperty("Distance Inch", () -> getDistanceMM(0)[0] / 25.4, null);
    // builder.addDoubleProperty("Ambient Light", () -> readQuality()[0], null);
    // builder.addDoubleProperty("Std Dev", () -> readQuality()[1], null);

  }

}
