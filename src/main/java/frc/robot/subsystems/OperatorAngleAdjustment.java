package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Class to change the robot's angle based on an offset. 
 * These offsets will be queued on the operator's controller
 * and then put into action on the driver's controller
 * @see OI
 * @author Bryce G., Madison J.
 * @category SWERVE
 */
public class OperatorAngleAdjustment extends SubsystemBase {

   private double gyroOffset = 0;
   private double farShot;
  private double nearShot;
  private double climbing;
  private double futureOffsetAngle;
  private double field0;
  private double field90; 
  private double field180;
  private double field270;
  private boolean isFieldOriented;
  private boolean isChangingGyroAngle;
  private boolean limelightRotationMode = false;
  private String mode = "";

  private double slowRotateSpeed = 0;

  /* --- Private Boolean Values --- */
  private boolean slowRotateMode = false;

  /**
   * Class to change the robot's angle based on an offset. These offsets will be
   * queued on the operator's controller and then put into action on the driver's
   * controller
   */
  public OperatorAngleAdjustment() {
    // Sets all the gyro offsets
    gyroOffset = 0;
    farShot = 0;
    nearShot = 0;
    climbing = 0;
    field0 = 0;
    field90 = 90;
    field180 = 180;
    field270 = 270;
    isFieldOriented = true;
  }

  /**
   * Sets the future offset angle. Used on operator joystick. The future offset
   * will take affect when the driver button is pressed
   * 
   * @param mode - String designating the mode
   *             <p>
   *             List of modes:
   *             </p>
   *             <ul>
   *             <li>farShot
   *             <li>nearShot
   *             <li>climbing
   *             </ul>
   */
  public void setFutureOffsetAngle(String mode) {
    this.mode = mode;
    switch(mode) {
    case "farShot":
      futureOffsetAngle = farShot;
      Robot.Shooter.setFutureSpeed(Constants.SHOOTSPEEDFAR);
      Robot.Vision.setRotateLimelight(true);
      Robot.KickerWheel.setFutureSpeed(Constants.KICKERSPEED);
      break;
    case "nearShot":
      futureOffsetAngle = nearShot;
      Robot.Shooter.setFutureSpeed(Constants.SHOOTSPEEDCLOSE);
      Robot.Vision.setRotateLimelight(true);
      Robot.KickerWheel.setFutureSpeed(Constants.KICKERSPEED);
      break;
    case "climbing":
      futureOffsetAngle = climbing;
      break;
    case "targetLimelightOn":
      Robot.Vision.setRotateLimelight(true);
      break;
    case "0":
      futureOffsetAngle = field0;
      break;
    case "90":
      futureOffsetAngle = field90;
      break;
    case "180":
      futureOffsetAngle = field180;
      break;
    case "270":
      futureOffsetAngle = field270;
      break;
    default:
      futureOffsetAngle = 0;
      Robot.Vision.setRotateLimelight(false);

    }
  }

  /**
   * Gets the future robot offset angle
   * @return - double value in degrees
   */
  public double getFutureOffsetAngle() {
    return futureOffsetAngle;
  }

  /**
   * Sets the current robot angle offset that the robot will actively attempt to
   * hold
   * @param offsetAngle - double value in degrees
   */
  public void setOffsetAngle(double offsetAngle) {
    this.gyroOffset = offsetAngle;
  }

  /**
   * Gets the current robot angle offset
   * @return - double yaw value in degrees
   */
  public double getGyroAngleOffset() {
    return gyroOffset;
  }

  /**
   * Boolean to track if the offset is currently in use
   * @param isChangingGyroAngle - boolean value (in use: true | holding position: false)
   */
  public void setIsChangingGyroAngle(boolean isChangingGyroAngle) {
    this.isChangingGyroAngle = isChangingGyroAngle;
  }

  /**
   * Gets the state of the boolean value
   * @return - boolean value (in use: true | holding position: false)
   */
  public boolean getIsChangingGyroAngle() {
    return isChangingGyroAngle;
  }

  /**
   * Calculates the robot's angle offset by intaking error, rotation and kP from
   * the swerve drive command to adjust the rotation of the entire robot.
   * @param error - double degree value (current angle - desired angle)
   * @param rotation - double rotation joystick value
   * @param kP - double proportion value used to scale the error to match the rotational units (-1 -> 1)
   * @return - adjusted rotation value acting as a joystick input
   */
  public double calculateGyroOffset(double error, double kP) {
    error %= 360;
    if (error > 180) {
      error -= 360;
    } else if (error < -180) {
      error += 360;
    }

    double rotation = error * kP;
    return (Math.abs(rotation) > 0.6) ? Math.copySign(0.6, rotation) : rotation;
  }

  /**
   * Tells if the limelight mode is queued
   * @param limelightRotationMode - Boolean value (limelightRotationMode: true | limelightRotationMode: false)
   */
  public void setLimelightRotationMode(boolean limelightRotationMode) {
    this.limelightRotationMode = limelightRotationMode;
  }

  /**
   * Gets the limelight mode
   * @return - Boolean value limelight mode
   */
  public boolean getLimelightRotationMode() {
    return limelightRotationMode;
  }

  /**
   * Gets the mode for the future offset angle
   * @return - String value currently set future mode
   */
  public String getMode() {
    return mode;
  }

  /**
   * Sets the slow rotate mode on the robot to on or off
   * @param slowRotateMode - boolean value indicating the slow rotate mode (slow rotate on: true | slow rotate off: false)
   */
  public void setSlowRotateMode(boolean slowRotateMode) {
    this.slowRotateMode = slowRotateMode;
  }

  /**
   * Gets the slow rotate mode boolean indicating whether slow rotate is occurring
   * or not
   * @return - boolean value indicating the slow rotate mode (slow rotate on: true | slow rotate off: false)
   */
  public boolean getSlowRotateMode() {
    return slowRotateMode;
  }

  /**
   * Sets the speed of the slow rotation
   * @param speed - double value for the slow rotation speed (-1 -> 1)
   */
  public void setSlowRotateSpeed(double speed) {
    this.slowRotateSpeed = speed;
  }

  /**
   * Gets the speed for slow rotation 
   * @return - double speed value for slow rotation (-1 -> 1)
   */
  public double getSlowRotateSpeed() {
    return this.slowRotateSpeed;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("SlowRotate", getSlowRotateMode());
  }
}