package frc.robot.commands.Shooter;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Shoots the ball
 * @author Michael Francis
 */
public class stopShooter extends InstantCommand {
  
  private final Shooter subsystem;

  /**
   * Stops the Shooter from Running
   * @param m_subsystem
   * The subsystem that the command uses (Shooter)
   * 
   */
  public stopShooter(Shooter m_subsystem) {
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
    subsystem.leftShootMotor.configClosedloopRamp(0.5);
    subsystem.rightShootMotor.configClosedloopRamp(0.5);
    //Stop the shooter.
    subsystem.stopShooter();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

}
