package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Climber;

/**
 * Sets the climber speed
 * @author Michael Francis
 */
public class activateClimber extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Climber subsystem;

  private boolean isActivated;

  /**
   * Sets the climber speed to a given percent
   * @param m_subsystem The subsystem used by this command. (climber)
   * @param m_speed A double number that sets the speed of the climber motor
   */
  public activateClimber(Climber m_subsystem, boolean isActivated) {
    subsystem = m_subsystem;
  this.isActivated = isActivated;    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the climber to run at a set speed
    subsystem.setClimberActivated(isActivated);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
