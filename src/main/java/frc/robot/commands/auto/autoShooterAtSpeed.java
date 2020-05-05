package frc.robot.commands.auto;

import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Checks to see if the shooter is up to speed based on the speed setting
 * @author Michael Francis
 * @category AUTON
 */
public class autoShooterAtSpeed extends CommandBase {
 
  /**
   * Checks to see if the shooter is up to speed based on the speed setting
   * @param m_subsystem - Operator Angle Adjustment Subsystem 
   */
  public autoShooterAtSpeed(OperatorAngleAdjustment m_subsystem) {

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return Robot.Shooter.shooterAtVelocity;
  }

}
