package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Stops the intake motors.
 * @author Michael Francis
 */
public class stopIntakeMotors extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake m_subsystem;

  /**
   * Stops the intake motors.
   * @param subsystem The subsystem used by this command. (Intake)
   */
  public stopIntakeMotors(Intake subsystem) {
    m_subsystem = subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will stop the intake
    m_subsystem.stopIntake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
