package frc.robot.commands.auto;

import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Ends the command when the shooter has reached velocity
 * @author Michael Francis
 */
public class autoShooterAtSpeed extends CommandBase {
  
  private final OperatorAngleAdjustment subsystem;

  /**
   * Ends the command when the shooter has reached velocity
   * @param m_subsystem - The required subsystem
   */
  public autoShooterAtSpeed(OperatorAngleAdjustment m_subsystem) {
    subsystem = m_subsystem;

    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return Robot.Shooter.shooterAtVelocity;
  }

}
