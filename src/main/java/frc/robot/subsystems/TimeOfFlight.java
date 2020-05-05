package frc.robot.subsystems;

import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


/**
 * Read values off of the Time of Flight sensor feedback
 */
public class TimeOfFlight extends SubsystemBase {


  //////////////////////////////////
  // ---------------------------- //
  // --- SET-UP AND VARIABLES --- //
  // ---------------------------- //
  //////////////////////////////////
  
  // Message IDs
  private static final int HEARTBEAT_MESSAGE = 0x1F0B01FF;
  private static final int CALIBRATION_STATE_MESSAGE = 0x060B0300;
  private static final int MEASURED_DISTANCE_MESSAGE = 0x060B0100;
  private static final int MEASUREMENT_QUALITY_MESSAGE = 0x060B0200;
  private static final int RANGING_CONFIGURATION_MESSAGE = 0x060B0400;
  private static final int DEVICE_CONFIGURATION_MESSAGE = 0x1F0B03FF;
  private static final int kSendMessagePeriod = 0;

  public static byte[] hwdata = new byte[8];

  private static int deviceID = 0;

  /**
   * Specifies whether or not the Time of Flight will be in debug mode.
   * During debug mode, it will put measurements on the SmartDashboard.
   */
  public static boolean tofDebug = false;

  public static double loadSensorSerial;
  public static double loadSensorPart;
  public static double loadSensorFirmware;
  // Variables that store data until called
  public static byte[] tofdata = new byte[8];
  public static int[] temp;

  public static double[] temp2 = new double[2];


  /**
   * Code for the TimeOfFlight sensor
   */
  public TimeOfFlight() {
    //Reads the raw heartbeat data
    tofdata = TimeOfFlight.readHeartbeat(deviceID);
    //Converts the data into useful information
    int[] temp = TimeOfFlight.getSensorInfo(tofdata);
    //Puts the corresponding information on the array
    loadSensorSerial = temp[0];
    loadSensorPart = temp[1];
    loadSensorFirmware = temp[2];
    //Configures the range
    TimeOfFlight.configureRange(Constants.TOFMODE);

  }

  @Override
  public void periodic() {
    //////////////////////////////////////////////////////////////
    // -------------------------------------------------------- //
    // --- PUT TIMEOFFLIGHT SENSOR VALUES ON SMARTDASHBOARD --- //
    // -------------------------------------------------------- //
    //////////////////////////////////////////////////////////////

    SmartDashboard.putNumber("Measurement MM", getDistanceMM());
    if(tofDebug){
      SmartDashboard.putNumber("Measurement In", getDistanceIN());
    }
  }


  //////////////////////////////////////////////
  // ---------------------------------------- //
  // --- HEARTBEAT INFORMATION EXTRACTION --- //
  // ---------------------------------------- //
  //////////////////////////////////////////////

  /**
   * Reads the information from the device.
   * @param id The ID of the sensor
   * @return Returns the bytes in an array (8).
   * <ul>
   * <li>   0 = Is it reversed?          </li>
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
      //If it is -1, it did not get a reading and will keep the last result.
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
   * @return Returns the 0 if the sensor was found; 999 if not found
   */
  public static int findSensor(int id) {
    hwdata = readHeartbeat(id);
    if (hwdata[4] != 0) {
      return deviceID;
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
   * @return Returns an array with 2 items containing useful information.
   * <ul>
   * <li> 0 - Measurement in milimieters (divide by 25.4 for inches; 304.8 for feet)</li>
   * <li> 1 - The signal return rate in mega counts per second.</li>
   * </ul>
   */
  public static double[] readDistance() {
    //Set up an array with 2 variables
    double[] temp = { 0, 0 };
    //Retrieve the measured distance from the CAN device
    long read = CANSendReceive.readMessage(MEASURED_DISTANCE_MESSAGE, deviceID);

    //If the message retrieval failed:
    if (read == -1) {
      //Return the last returned values
      return temp2;
    //Otherwise:
    } else {
      //Create a variable with the status of the CAN device
      /**
       * MEANINGS OF THE RESULTS:
       *   0 - Measured distance is invalid
       *   1 - Sigma estimator check is above internally defined threshold
       *   2 - Return signal value is below the internal defined threshold
       *   4 - Return signal phase is out of bounds
       *   5 - Hardware failure
       *   7 - Wrapped target, non-matching phases (probably means more than one target found)
       *   8 - Internal algorithm underflow or overflow
       *  14 - The measured distance is invalid
       */
      int rangingStatus = Byte.toUnsignedInt(CANSendReceive.result[2]);
      //If the status is not 0 (probably failure)
      if (rangingStatus != 0) {
        //Set
        temp[0] = -rangingStatus;
        return temp;
      } else {
        //Extract the results in milimeters
        temp[0] = extractValue(CANSendReceive.result, 1, 0);
        //Extract signal return rate in mega counts per second
        //Value is multiplied by 65536, so it must be divided.
        temp[1] = extractValue(CANSendReceive.result, 7, 4) / 65536;

        //Store the last values
        temp2 = temp;

        //Return the value
        return temp;
      }
    }
  }

  /**
   * Gets the distance read by the sensor in milimeters alone.
   * @return The distance in milimeters
   */
  public static double getDistanceMM() {
    double a = readDistance()[0];
    return a;
  }

  /**
   * Gets the distance read by the sensor in inches alone.
   * @return The distance in inches rounded with 4 decimal places after.
   */
  public static double getDistanceIN() {
    double a = readDistance()[0]/25.4;
    return a;
  }


  //////////////////////////
  // -------------------- //
  // --- READ QUALITY --- //
  // -------------------- //
  //////////////////////////

  /**
   * Reads the measurement quality returned with the completed measurement
   * @return Returns the values in an array (2)
   * <ul>
   * <li> 0 = Ambient light in megacounts per second                           </li>
   * <li> 1 = Standard deviation of the measured distance value, in milimeters </li>
   * </ul>
   */
  public static double[] readQuality() {
    //Set up an array temp with 2 items.
    /** Holds items temporarily to be returned */
    double temp[] = { 0, 0 };
    //Read the measurement quality from the CAN device
    long read = CANSendReceive.readMessage(MEASUREMENT_QUALITY_MESSAGE, deviceID);

    //If the read didn't retrieve nothing:
    if (read != -1) {
      //NOTE: The data comes multiplied by 65536, so we divide this.
      //Extract the ambient light in megacounts per second
      temp[0] = extractValue(CANSendReceive.result, 3, 0) / 65536;
      //Extract the std deviation of the measured distance value (in mm)
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
  public static int[] readCalibrationState() {
    //Set up the temp array with 7 items
    /** Holds the items to be returned */
    int temp[] = { 0, 0, 0, 0, 0, 0, 0 };
    //Read the calibration state from the CAN device
    long read = CANSendReceive.readMessage(CALIBRATION_STATE_MESSAGE, deviceID);

    //If the read was not an error:
    if (read != -1) {
      //Extract the range offset in milimeters
      temp[2] = extractValue(CANSendReceive.result, 2, 1);
      //Extract the Y position
      //Reading bits 0-3 by excluding bits 4-7
      temp[0] = CANSendReceive.result[0] & 0b00001111;
      //Extract the X position
      //Bit-shifting to read bits 4-7
      temp[1] = CANSendReceive.result[0] >> 4;
    }

    //Return the retrieved data
    return temp;
  }

  /**
   * Configures the range of the sensor
   * @param mode The mode (0-short; 1-medium; 2-long)
   */
  public static void configureRange(int mode) {
    //Set up the byte array data with 3 items
    /** Holds the data to be sent to the CAN device to set up the range of the device */
    byte[] data = new byte[3];
    //Set the interval variable to 100
    int interval = 100;
    //Set item 0 in array data to the mode as a byte
    data[0] = (byte) mode;

    //Set up interval using mode
    switch (mode) {
      //Short range (up to 1.3 meters)
      case 0:
        interval = 100;
        break;
      //Medium range (range unknown, nothing listed anywhere)
      case 1:// 150ms in 2 byte format
        interval = 150;
        break;
      //Long range (up to 4 meters depending on lighting conditions)
      case 2:// 200ms in 2 byte format
        interval = 200;
        break;
      //Default range (short)
      default:
        interval = 100;
        break;
    }

    //Sets up an array of bytes to send to the device
    ByteBuffer b = ByteBuffer.allocate(4);
    b.putInt(interval);
    byte[] result = b.array();
    data[1] = result[1];
    data[2] = result[2];

    //Send information to the CAN device
    CANSendReceive.sendMessage(RANGING_CONFIGURATION_MESSAGE | deviceID, data, 3, kSendMessagePeriod);
  }


  //////////////////////////////
  // ------------------------ //
  // --- CONFIGURE DEVICE --- //
  // ------------------------ //
  //////////////////////////////

  /**
   * Reads the sensor information then sends it to the sensor
   */
  public static void identifyDevice() {
    //Set up the array hwdata with 8 values
    /** Holds the data to be sent to the CAN device */
    byte[] hwdata = new byte[8];
    //Read the heartbeat data
    hwdata = readHeartbeat(deviceID);
    //Set the reversed data
    hwdata[0] = 0x0D;//13
    //Send information to the CAN device
    CANSendReceive.sendMessage(DEVICE_CONFIGURATION_MESSAGE, hwdata, 6, kSendMessagePeriod);
  }

  /**
   * Changes the ID of the device
   * @param oldID Old ID of the device
   * @param newID Current ID of the device
   */
  public static void configureDevice(int oldID, int newID) {
    //Set up the array hwdata with 8 values
    /** Holds the data to be sent to the CAN device */
    byte[] hwdata = new byte[8];
    if (newID >= 0 && newID < 33) {
      //Read the heartbeat and set the information to hwdata
      hwdata = readHeartbeat(oldID);
      //Translate the information into useful data
      getSensorInfo(hwdata);
      //Set the reversed data
      hwdata[0] = 0x0C;
      //Translate the newID into a byte
      hwdata[6] = (byte) newID;
      //Send a message to the CAN device
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
    /* --- CREATE VARIABLES --- */
    //Integer that adds together values of a byte
    int temp = 0;
    //A variable that temporarily stores information
    int temp1 = 0;
    //A counting variable that could be set in the for loop it is used in but it wasn't
    int i = 0;

    /* --- Add together byte data --- */
    //Set the temp variable to the high bit
    temp = (src[high]);
    //While i is less greater than the low value, add values to temp
    for (i = high - 1; i >= low; i--) {
      //For each time the loop is run, set the temp1 variable to the bit value
      temp1 = Byte.toUnsignedInt(src[i]);
      //Add the bit value to the temp variable
      temp = (temp * 256) + temp1;
    }
    //Return the result
    return temp;
  }

}
