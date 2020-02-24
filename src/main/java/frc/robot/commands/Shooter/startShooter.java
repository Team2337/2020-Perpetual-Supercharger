package frc.robot.commands.Shooter;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Shoots the ball
 * @author Michael Francis
 */
public class startShooter extends CommandBase {
  
  private final Shooter subsystem;

  /**
   * Shoots the ball at a specified speed.
   * @param m_subsystem
   * The subsystem that the command uses (Shooter)
   */
  public startShooter(Shooter m_subsystem) {
    //Puts the parameters in the command's variables to be used around as a shortcut.
    subsystem = m_subsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    subsystem.currentLimitConfigurationMotor.currentLimit = 50;
    subsystem.leftShootMotor.configStatorCurrentLimit(subsystem.currentLimitConfigurationMotor, 0);
    subsystem.rightShootMotor.configStatorCurrentLimit(subsystem.currentLimitConfigurationMotor, 0);
    //Sets the ramp rate. We set them here because in the execute of this command,
    // they are set to another value after a set speed.
    //Sets the speed.
    subsystem.setShooterSpeed(8700);
    System.out.println("Time: " + System.currentTimeMillis());
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Once the velocity reaches a certain speed, the closed-loop ramp is turned off.
    //This is to ensure that the motors get up to speed quickly without damaging themselves.
    /** Left shooter motor velocity */
    double lsm = subsystem.leftShootMotor.getSelectedSensorVelocity();
    /** Right shooter motor velocity */
    double rsm = subsystem.rightShootMotor.getSelectedSensorVelocity();
    if(Math.max(lsm, rsm) > Constants.SHOOTERRAMPSWITCHVALUE){
      subsystem.leftShootMotor.configClosedloopRamp(0);
      subsystem.rightShootMotor.configClosedloopRamp(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.leftShootMotor.configClosedloopRamp(0.5);
    subsystem.rightShootMotor.configClosedloopRamp(0.5);
    subsystem.currentLimitConfigurationMotor.currentLimit = 0;
    subsystem.leftShootMotor.configStatorCurrentLimit(subsystem.currentLimitConfigurationMotor, 0);
    subsystem.rightShootMotor.configStatorCurrentLimit(subsystem.currentLimitConfigurationMotor, 0);
    // //Stop the shooter.
    // subsystem.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
