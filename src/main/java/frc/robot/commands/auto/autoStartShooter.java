package frc.robot.commands.auto;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Shoots the ball at a specified speed
 * @author Michael Francis
 */
public class autoStartShooter extends InstantCommand {
  
  private final Shooter subsystem;
  private double velocity;

  /**
   * Shoots the ball at a specified speed
   * @param m_subsystem - The subsystem that the command uses (Shooter)
   * @param m_velocity - The velocity (in encoder ticks per 100ms) in which the shooter will shoot at.
   */
  public autoStartShooter(Shooter m_subsystem, double m_velocity) {
    subsystem = m_subsystem;
    velocity = m_velocity;

    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {
  subsystem.setShooterSpeed(velocity);
  }

  @Override
  public void end(boolean interrupted) {

  }

}
