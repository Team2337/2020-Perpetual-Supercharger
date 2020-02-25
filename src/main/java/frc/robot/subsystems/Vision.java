package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


  /**
   * The vision code for the limelight and camera
   * @author Madison J. and Zayd A.
   * @category VISION
   */
public class Vision extends SubsystemBase {
  public AnalogInput pixyRight;
  public AnalogInput pixyLeft;
  public DigitalInput pixyDigitalRight;
  private boolean rotateLimelight = false;

  public Vision() {
    pixyRight = new AnalogInput(4);
    pixyLeft = new AnalogInput(5);
    pixyDigitalRight = new DigitalInput(5);
  }

 /**
   * Sets the LED mode to on, off, or blink
   * @param mode - the mode of the LEDs
   * Example: 
   * 0: Sets the mode to what is in the current pipeline
   * 1: Turns off the LEDs
   * 2: Blink mode on LEDs
   * 3: Turns on the LEDs
   */
   public void setLEDMode(int mode) {
     NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(mode);
   }

    /**
    * Gets the Limelight mode number from the NetworkTable
    * @return - returns the mode number from the NetworkTable 
    */
   public int getLEDMode() {
     return (int)NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").getValue().getDouble();
   }

    /**
    * Sets the pipeline of the limelight
    * @param pipeline - sets the desired pipeline number between 0-9
    * 0 - CloseVision (0-15 ft away from the tower)
    * 1 - MediumVision (between 15-21 ft)
    * 2 - FarVision (21-26 ft)
    * 9 - Drivecam
    */
   public void switchPipeLine(int pipeline) {
    double currentPipeline = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0);
    if(currentPipeline != pipeline){
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
    }
   }

    /**
     * This will get the value from tx, ta, etc. by using a string
     * @param output - string double value
     * @return - returns the double value from the string for example from ta, tx, etc.
     */
    public double getDoubleValue(String output) {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry(output).getDouble(0);
  }
    /**
     * This will get the X coordinte from Opensight
     * @return - returns the x-coordinate value, which will show how far we need to rotate
     */
    public double getOpenSightXCoordinateValue() {
      return NetworkTableInstance.getDefault().getTable("PutCoordinate").getEntry("coord-x").getDouble(0);

  }
    /**
     * This will get the Y coordinate from Opensight
     * @return - returns the y-coordinate value, which will show how far the robot needs to drive
     */
    public double getOpenSightYCoordinateValue() {
      return NetworkTableInstance.getDefault().getTable("PutCoordinate").getEntry("coord-y").getDouble(0);
  }
  /**
   * Receives the NetworkTable values from Opensight
   * @return - returns the opensight networktable which will return a true/false value
   */
  public boolean getOpenSightNTValue() {
    return NetworkTableInstance.getDefault().getTable("PutNT").getEntry("succ").getBoolean(false);
  }

  /**
   * Lets us know if we are in the limelight mode, we are rotating using the limelight
   * @param rotateLimelight - Boolean value (limelight mode: true | not limelight mode: false)
   */
  public void setRotateLimelight(boolean rotateLimelight) {
    this.rotateLimelight = rotateLimelight;
  }

  /**
   * Lets us know if we are in the limelight mode, we are rotating using the limelight
   * @return - Boolean value limelight mode
   */
  public boolean getRotateLimelight() {
    return rotateLimelight;
  }
  
  /**
   * Gets the voltage of the right Pixy camera
   * @return The voltage of the Pixy camera
   */
  public double getPixyRightValue() {
    return (pixyRight.getVoltage() * (60 / 3.3) - 30) /2;
  }
  
  /**
   * Gets the voltage of the left Pixy camera
   * @return The voltage of the Pixy camera
   */
  public double getPixyLeftValue() {
    return (pixyLeft.getVoltage() * (60 / 3.3) - 30) / 2;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Voltage", pixyRight.getVoltage());
    SmartDashboard.putNumber("pixyRight", getPixyRightValue());
    SmartDashboard.putBoolean("Pixyright", pixyDigitalRight.get());
    SmartDashboard.putNumber("Rotation motor value", (Math.toRadians(getPixyRightValue()) * Constants.VISIONROTATIONP));
  }
}