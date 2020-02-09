package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the future angle offset
 * @author Madison J.
 * @category AUTON
 */
public class SetGyroAngleOffset extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final OperatorAngleAdjustment m_subsystem;
  /* --- Strings --- */
  private String mode;

/**
 * Sets the future angle offset
 * @param subsystem - OperatorAngleAdjustment subsystem object
 * @param mode - Changes the angle of the robot depending on the mode selected (modes may do more than change robot angles)
 * @see OperatorAngleAdjustment 
 */
  public SetGyroAngleOffset(OperatorAngleAdjustment subsystem, String mode) {
    m_subsystem = subsystem;
    /* --- Parameters Being Set to Global Variables --- */
    this.mode = mode;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    // Sets the future angle offset
    Robot.OperatorAngleAdjustment.setFutureOffsetAngle(mode);
  }

  @Override
  public void end(boolean interrupted) {
  }

}
