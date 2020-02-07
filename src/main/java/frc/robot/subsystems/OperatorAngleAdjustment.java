/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class OperatorAngleAdjustment extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
 * @return 
   */

   public double gyroOffset;
   public double farShot;
   public double nearShot;
   public double climbing;
   public double futureOffsetAngle;
   public boolean isFieldOrientend;
   public boolean isChangingGyroAngle;
   public boolean limelightRotationMode;
   public String mode;

  public OperatorAngleAdjustment() {
    gyroOffset = 0;
    farShot = 33;
    nearShot = 90;
    climbing = 180;
    isFieldOrientend = true;
  }

  public void setFutureOffsetAngle(String mode) {
    this.mode = mode;
    switch(mode) {
      case "farShot": 
      futureOffsetAngle = farShot;
      break;
      case "nearShot":
      futureOffsetAngle = nearShot;
      break;
      case "climbing":
      futureOffsetAngle = climbing;
      break;
      case "targetLimelightOn":
      Robot.Vision.setRotateLimelight(true);
      break;
      case "targetLimelightOff":
      futureOffsetAngle = -Robot.Vision.getDoubleValue("tx");
      break;
      case "targetLimelightWithForward":
      break;
      default:
      futureOffsetAngle = 0;
      Robot.Vision.setRotateLimelight(false);
    }
  }

  public double getFutureOffsetAngle() {
    return futureOffsetAngle;
  }

  public void setOffsetAngle(double offsetAngle) {
    this.gyroOffset = offsetAngle;
  }
    public double getGyroAngleOffset() {
   return gyroOffset;
  }

  public void setIsChangingGyroAngle(boolean isChangingGyroAngle) {
    this.isChangingGyroAngle = isChangingGyroAngle;
  }

  public boolean getIsChangingGyroAngle() {
    return isChangingGyroAngle;
  }

  public double calculateGyroOffset( double error, double rotation, double kP) {
    error %= 360;
    if (error > 180) {
      error -= 360;
    } else if (error < -180) {
      error += 360;
    }
    rotation = error * kP;
    return (Math.abs(rotation) > 0.6) ? Math.copySign(0.6, rotation) : rotation;
  }

  public void setLimelightRotationMode(boolean limelightRotationMode) {
    this.limelightRotationMode = limelightRotationMode;
  }

  public boolean getLimelightRotationMode() {
    return limelightRotationMode;
  }

  public String getMode() {
    return mode;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
