package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * @author John R. 
 * The function of this subsystem is to make variables for the
 * two solenoids that operate the hopper system.
**/

public class Hopper extends SubsystemBase {

  // Solenoids being defined
  Solenoid leftFlipper;
  Solenoid rightFlipper;

  // Sets port the Solenoids work from
  public Hopper() {
    leftFlipper = new Solenoid(Constants.LEFTFLIPPERSOLENOID);
    rightFlipper = new Solenoid(Constants.RIGHTFLIPPERSOLENOID);
  }

  // Defines the act of extending and retracting the left flipper through a
  // boolean state
  public void extendLeftFlipper(boolean state) {
    leftFlipper.set(state);
  }

  // Defines the act of extending and retracting the right flipper through a
  // boolean state
  public void extendRightFlipper(boolean state) {
    rightFlipper.set(state);
  }
}