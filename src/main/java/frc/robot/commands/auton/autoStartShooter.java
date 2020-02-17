package frc.robot.commands.auton;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Starts the shooter at the designated speed
 * @author Bryce G.
 */
public class autoStartShooter extends InstantCommand {
  
  private final Shooter subsystem;

  /**
   * Starts the shooter at the designated speed
   * @param m_subsystem - OperatorAngleAdjustment Subsystem object
   */
  public autoStartShooter(Shooter m_subsystem) {
    subsystem = m_subsystem;

    addRequirements(m_subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Sets the shooter speed to the future speed
    subsystem.setShooterSpeed(Robot.Shooter.getFutureSpeed());
  }


  @Override
  public void end(boolean interrupted) {
  }
}
