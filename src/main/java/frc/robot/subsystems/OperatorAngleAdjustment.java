package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Class to change the robot's angle based on an offset. 
 * These offsets will be queued on the operator's controller
 * and then put into action on the driver's controller
 * @see OI
 * @author Bryce G., Madison J.
 * @category SWERVE
 */
public class OperatorAngleAdjustment extends SubsystemBase {

   public double gyroOffset;
   public double farShot;
   public double nearShot;
   public double climbing;
   public double futureOffsetAngle;
   public boolean isFieldOrientend;
   public boolean isChangingGyroAngle;

 /**
  * Class to change the robot's angle based on an offset. 
  * These offsets will be queued on the operator's controller
  * and then put into action on the driver's controller
  */
  public OperatorAngleAdjustment() {
    // Sets all the gyro offsets
    gyroOffset = 0;
    farShot = 33;
    nearShot = 90;
    climbing = 180;
    isFieldOrientend = true;
  }

  /**
   * Sets the future offset angle. Used on operator joystick. 
   * The future offset will take affect when the driver button is pressed
   * @param mode - String designating the mode
   * <p>List of modes:</p>
   * <ul>
   *    <li>farShot
   *    <li>nearShot
   *    <li>climbing
   * </ul>
   */
  public void setFutureOffsetAngle(String mode) {
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
      default:
      futureOffsetAngle = 0;
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
   * Sets the current robot angle offset that the 
   * robot will actively attempt to hold
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
   * Calculates the robot's angle offset by intaking error, rotation
   * and kP from the swerve drive command to adjust the rotation 
   * of the entire robot. 
   * @param error - double degree value (current angle - desired angle)
   * @param rotation - double rotation joystick value 
   * @param kP - double proportion value used to scale the error to match the rotational units (-1 -> 1)
   * @return - adjusted rotation value acting as a joystick input
   */
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}