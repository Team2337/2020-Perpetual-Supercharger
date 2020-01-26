/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
    leftFlipper = new Solenoid(Constants.leftFlipperSolenoid);
    rightFlipper = new Solenoid(Constants.rightFlipperSolenoid);
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

  public void retractLeftFlipper() {
  }

}