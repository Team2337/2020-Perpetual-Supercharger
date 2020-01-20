package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * @author John R.
 * Function: Make variables for the two solenoids that operate the hopper system 
 */

public class Hopper extends SubsystemBase {

  // Solenoids being defined
  Solenoid leftFlipper;
  Solenoid rightFlipper;

  // Sets port the Solenoids work from
  public Hopper() {
    leftFlipper = new Solenoid(3);
    rightFlipper = new Solenoid(2);
  }

  // Defines the act of extending and retracting the left flipper
  public void extendLeftFlipper() {
    leftFlipper.set(true);
  }

  public void retractLeftFlipper() {
    leftFlipper.set(false);
  }

  // Defines the act of extending and retracting the right flipper
  public void extendRightFlipper() {
    rightFlipper.set(true);
  }

  public void retractRightFlipper() {
    rightFlipper.set(false);
  }
}