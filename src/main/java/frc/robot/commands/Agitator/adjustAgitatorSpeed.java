package frc.robot.commands.Agitator;

import frc.robot.subsystems.Agitator;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Adjusts the agitator speed
 * @author Michael Francis
 */
public class adjustAgitatorSpeed extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Agitator subsystem;
  private double modifier;

  /**
   * Increases the agitator speed by a given amount. The motors do not stop after.
   * @param m_subsystem The subsystem used by this command. (agitator)
   * @param m_modifier A double that the robot changes the speed of the motors by.
   */
  public adjustAgitatorSpeed(Agitator m_subsystem, double m_modifier) {
    subsystem = m_subsystem;
    modifier = m_modifier;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will change the agitator speed by a set amount
    subsystem.setAgitatorSpeed(subsystem.getAgitatorSpeed() + modifier);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Because this command is for testing purposes, the motors do not stop. Use stopagitatorMotors to stop.
  }
}
