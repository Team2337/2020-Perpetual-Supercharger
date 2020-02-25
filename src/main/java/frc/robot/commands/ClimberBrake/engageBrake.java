package frc.robot.commands.ClimberBrake;

import frc.robot.subsystems.ClimberBrake;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the climber brake
 * @author Michael Francis
 */
public class engageBrake extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimberBrake ClimberBrake;

  /**
   * Sets the climber brake
   * @param m_subsystem The subsystem used by this command. (ClimberBrake)
   */
  public engageBrake(ClimberBrake climberBrake) {
    ClimberBrake = climberBrake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climberBrake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Engages the climber brake
    ClimberBrake.engageBrake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
