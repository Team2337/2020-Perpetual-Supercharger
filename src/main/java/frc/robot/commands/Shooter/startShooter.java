package frc.robot.commands.Shooter;

import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;



/**
 * Shoots the ball
 * @author Michael Francis
 */
public class startShooter extends CommandBase {
  
  private final Shooter m_subsystem;
  private double m_velocity;



  /**
   * Shoots the ball at a specified speed.
   * @param subsystem
   * The subsystem that the command uses (Shooter)
   * @param velocity
   * The velocity (in encoder ticks per 100ms) in which the shooter will shoot at.
   */
  public startShooter(Shooter subsystem, double velocity) {
    //Puts the parameters in the command's variables to be used around as a shortcut.
    m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Sets the ramp rate. We set them here because in the execute of this command,
    // they are set to another value after a set speed.
    m_subsystem.leftShootMotor.configClosedloopRamp(0.5);
    m_subsystem.rightShootMotor.configClosedloopRamp(0.5);
    //Sets the speed.
    m_subsystem.setShooterSpeed(m_velocity);
  }



  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Once the velocity reaches a certain speed, the closed-loop ramp is turned off.
    //This is to ensure that the motors get up to speed quickly without damaging themselves.
    if(m_subsystem.leftShootMotor.getSelectedSensorVelocity() > Constants.SHOOTERRAMPVALUE){
      m_subsystem.leftShootMotor.configClosedloopRamp(0);
      m_subsystem.rightShootMotor.configClosedloopRamp(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stop the shooter.
    m_subsystem.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
