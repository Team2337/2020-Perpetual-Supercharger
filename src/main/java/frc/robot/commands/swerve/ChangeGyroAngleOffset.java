
package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;

/**
 * Sets the robot's angle offset. This should be on the <b>DRIVER</b> joystick
 * @see OperatorAngleAdjustment
 * @author Bryce G., Madison J.
 * @category SWERVE
 */
public class ChangeGyroAngleOffset extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final OperatorAngleAdjustment OperatorAngleAdjustment;
  private boolean isRotating;

  /**
   * Sets the robot's angle offset. This should be on the <b>DRIVER</b> joystick
   * @param subsystem - OperatorAngleAdjustment Subsystem Object from Robot
   * @param isRotating - determines if the robot will be rotating when the button is pressed
   */
  public ChangeGyroAngleOffset(OperatorAngleAdjustment operatorAngleAdjustment, boolean isRotating) {
    this.OperatorAngleAdjustment = operatorAngleAdjustment;
    this.isRotating = isRotating;
    addRequirements(operatorAngleAdjustment);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.OperatorAngleAdjustment.setOffsetAngle(Robot.OperatorAngleAdjustment.getGyroAngleOffset());
    Robot.OperatorAngleAdjustment.setIsChangingGyroAngle(isRotating);
    if (Robot.Vision.getRotateLimelight() && isRotating) {
      Robot.Vision.setLEDMode(3);
      Robot.OperatorAngleAdjustment.setLimelightRotationMode(true);
    } else {
      Robot.Vision.setLEDMode(1);
      Robot.OperatorAngleAdjustment.setLimelightRotationMode(false);
    }

    if (!isRotating) {
      Robot.Vision.setLEDMode(1);
      if (Robot.OperatorAngleAdjustment.getMode().equals("targetLimelightOn")) {
        Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getPigeonYawMod());
        Robot.OperatorAngleAdjustment.setLimelightRotationMode(false);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}