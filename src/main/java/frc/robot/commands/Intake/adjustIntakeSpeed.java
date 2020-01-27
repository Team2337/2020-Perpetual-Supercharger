package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Adjusts the intake speed
 * @author Michael Francis
 */
public class adjustIntakeSpeed extends CommandBase {
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

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Because this command is for testing purposes, the motors do not stop. Use stopIntakeMotors to stop.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}