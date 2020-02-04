package frc.robot.commands.Shooter;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Shoots the ball
 * @author Michael Francis
 */
public class startShooter extends CommandBase {
  
  private final Shooter subsystem;
  private double velocity;

  /**
   * Shoots the ball at a specified speed.
   * @param m_subsystem
   * The subsystem that the command uses (Shooter)
   * @param m_velocity
   * The velocity (in encoder ticks per 100ms) in which the shooter will shoot at.
   */
  public startShooter(Shooter m_subsystem, double m_velocity) {
    //Puts the parameters in the command's variables to be used around as a shortcut.
    subsystem = m_subsystem;
    velocity = m_velocity;

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
    //Sets the speed.
    subsystem.setShooterSpeed(velocity);
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Once the velocity reaches a certain speed, the closed-loop ramp is turned off.
    //This is to ensure that the motors get up to speed quickly without damaging themselves.
    if(subsystem.leftShootMotor.getSelectedSensorVelocity() > Constants.SHOOTERRAMPSWITCHVALUE){
      subsystem.leftShootMotor.configClosedloopRamp(0);
      subsystem.rightShootMotor.configClosedloopRamp(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stop the shooter.
    subsystem.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
