package frc.robot.commands.Climber;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Adjusts the climber speed
 * @author Michael Francis
 */
public class adjustClimberSpeed extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Climber subsystem;
  private double modifier;

  /**
   * Increases the climber speed by a given amount. The motors do not stop after.
   * @param m_subsystem The subsystem used by this command. (climber)
   * @param m_modifier A double that the robot changes the speed of the motors by.
   */
  public adjustClimberSpeed(Climber m_subsystem, double m_modifier) {
    subsystem = m_subsystem;
    modifier = m_modifier;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will change the climber speed by a set amount
    subsystem.setClimberSpeed(subsystem.getClimberSpeed() + modifier);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Because this command is for testing purposes, the motors do not stop. Use stopclimberMotors to stop.
  }
}
