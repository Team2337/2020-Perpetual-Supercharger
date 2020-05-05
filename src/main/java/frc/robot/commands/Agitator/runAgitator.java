package frc.robot.commands.Agitator;

import frc.robot.subsystems.Agitator;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the agitator speed
 * @author Michael Francis
 */
public class runAgitator extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Agitator subsystem;
  /** Agitator motor speed */
  private double speed;

  /**
   * Sets the agitator speed to a given percent
   * @param m_subsystem The subsystem used by this command. (agitator)
   * @param m_speed A double number that sets the speed of the left agitator motor
   */
  public runAgitator(Agitator m_subsystem, double m_speed) {
    subsystem = m_subsystem;
    speed = m_speed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the agitator to run at a set speed
    subsystem.setAgitatorSpeed(speed);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
