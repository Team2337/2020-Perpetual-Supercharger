package frc.robot.commands.auto;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Starts the shooter
 * @author Michael Francis
 * @category AUTON
 */
public class autoStartShooter extends InstantCommand {
  
  private final Shooter subsystem;
  private double velocity;

  /**
   * Starts the shooter
   * @param m_subsystem
   * The subsystem that the command uses (Shooter)
   * @param m_velocity
   * The velocity (in encoder ticks per 100ms) in which the shooter will shoot at.
   */
  public autoStartShooter(Shooter m_subsystem, double m_velocity) {
    //Puts the parameters in the command's variables to be used around as a shortcut.
    subsystem = m_subsystem;
    velocity = m_velocity;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  subsystem.setShooterSpeed(velocity);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

}
