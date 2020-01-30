package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Adjusts the intake speed
 * @author Michael Francis
 */
public class adjustIntakeSpeed extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake subsystem;
  private double modifier;

  /**
   * Increases the intake speed by a given amount. The motors do not stop after.
   * @param m_subsystem The subsystem used by this command. (Intake)
   * @param m_modifier A double that the robot changes the speed of the motors by.
   */
  public adjustIntakeSpeed(Intake m_subsystem, double m_modifier) {
    subsystem = m_subsystem;
    modifier = m_modifier;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will change the intake speed by a set amount
    subsystem.setIntakeSpeed(subsystem.getIntakeSpeed()[0] + modifier, 
     subsystem.getIntakeSpeed()[1] + modifier);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Because this command is for testing purposes, the motors do not stop. Use stopIntakeMotors to stop.
  }
}
