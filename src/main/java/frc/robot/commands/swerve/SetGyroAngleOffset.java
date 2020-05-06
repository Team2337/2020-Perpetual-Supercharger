package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;

/**
 * Sets the robot's future angle offset. This should be on the <b>OPERATOR</b> joystick
 * @see OperatorAngleAdjustment
 * @author Bryce G., Madison J.
 * @category SWERVE
 */
public class SetGyroAngleOffset extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final OperatorAngleAdjustment m_subsystem;
  private String mode;
  
  /**
   * Sets the robot's future angle offset. This should be on the <b>DRIVER</b> joystick
   * @param subsystem - OperatorAngleAdjustment Subsystem Object
   * @param mode - String value signifying the rotation mode the robot is in 
   */
  public SetGyroAngleOffset(OperatorAngleAdjustment subsystem, String mode) {
    m_subsystem = subsystem;
    this.mode = mode;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    Robot.OperatorAngleAdjustment.setFutureOffsetAngle(mode);
  }

  @Override
  public void end(boolean interrupted) {
  }

}