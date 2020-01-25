/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OperatorAngleAdjustment extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
 * @return 
   */

   public double gyroOffset;
   public double farShot;
   public double nearShot;
   public double climbing;
   public boolean isFieldOrientend;

  public OperatorAngleAdjustment() {
    gyroOffset = 0;
    farShot = 0;
    nearShot = 0;
    climbing = 0;
    isFieldOrientend = true;
  }

  public void setOffsetAngle(String mode) {
    switch(mode) {
      case "farShot": 
      gyroOffset = farShot;
      break;
      case "nearShot":
      gyroOffset = nearShot;
      break;
      case "climbing":
      gyroOffset = climbing;
      break;
      default:
     gyroOffset = 0;
    }
  }

    public double getGyroAngleOffset() {
   return gyroOffset;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
