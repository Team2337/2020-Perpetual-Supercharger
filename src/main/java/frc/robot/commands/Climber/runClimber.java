package frc.robot.commands.Climber;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the climber speed
 * @author Michael Francis
 */
public class runClimber extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Climber subsystem;
  /** Climber motor speed */
  private double speed;

  /**
   * Sets the climber speed to a given percent
   * @param m_subsystem The subsystem used by this command. (climber)
   * @param m_speed A double number that sets the speed of the climber motor
   */
  public runClimber(Climber m_subsystem, double m_speed) {
    subsystem = m_subsystem;
    speed = m_speed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the climber to run at a set speed
    subsystem.setClimberSpeed(speed);
  }

  @Override
  public void execute(){
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the climber
    subsystem.stopClimber();
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}
