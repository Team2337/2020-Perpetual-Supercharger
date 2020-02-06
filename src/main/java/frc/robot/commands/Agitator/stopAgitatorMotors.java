package frc.robot.commands.Agitator;

import frc.robot.subsystems.Agitator;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Stops the agitator motors.
 * @author Michael Francis
 */
public class stopAgitatorMotors extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Agitator m_subsystem;

  /**
   * Stops the agitator motor.
   * @param subsystem The subsystem used by this command. (agitator)
   */
  public stopAgitatorMotors(Agitator subsystem) {
    m_subsystem = subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will stop the agitator
    m_subsystem.stopAgitator();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
