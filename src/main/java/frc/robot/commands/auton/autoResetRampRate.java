package frc.robot.commands.auton;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Shoots the ball
 * @author Michael Francis
 */
public class autoResetRampRate extends InstantCommand {

  private final OperatorAngleAdjustment subsystem;

  /**
   * Shoots the ball at a specified speed.
   * 
   * @param m_subsystem The subsystem that the command uses (Shooter)
   */
  public autoResetRampRate(OperatorAngleAdjustment m_subsystem) {
    //Puts the parameters in the command's variables to be used around as a shortcut.
    subsystem = m_subsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Sets the ramp rate. We set them here because in the execute of this command,
    // they are set to another value after a set speed.
    //Sets the speed.
    Robot.Shooter.leftShootMotor.configClosedloopRamp(0);
    Robot.Shooter.rightShootMotor.configClosedloopRamp(0);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
