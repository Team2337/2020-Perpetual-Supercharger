package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

  /**
   * The vision code for the limelight and camera
   * @author Madison J. and Zayd A.
   */

public class Vision extends SubsystemBase {

   public Vision() {

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
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
   }

    /**
     * This will get the value from tx, ta, etc. by using a string
     * @param output - string double value
     * @return - returns the double value from the string for example from ta, tx, etc.
     */
    
public double getDoubleValue(String output) {
  return NetworkTableInstance.getDefault().getTable("limelight").getEntry(output).getDouble(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}