package frc.robot.commands.Climber;

import frc.robot.Robot;
import frc.robot.subsystems.Climber;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the climber speed
 * @author Michael Francis
 */
public class runClimberJoystick extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Climber Climber;

  private double climberSpeed;
  private double deadband = 0.2;
  private double maxSpeed = 1.0;
  private boolean positionNotSet = false;


  /**
   * Sets the climber speed to a given percent
   * @param m_subsystem The subsystem used by this command. (climber)
   * @param m_speed A double number that sets the speed of the climber motor
   */
  public runClimberJoystick(Climber climber) {
    Climber = climber;

    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    //The climber will move based on the operator's right joystick's movement 
    if(Climber.getClimberActivated()) {
      climberSpeed = Robot.OI.operatorJoystick.getRightStickY();
      climberSpeed = Robot.Utilities.deadband(climberSpeed, deadband);
      if(Math.abs(climberSpeed) > deadband) {
        Climber.setClimberSpeed(climberSpeed * maxSpeed);
        positionNotSet = true;
      } else {
        if(positionNotSet) {
          Climber.climberMotor.set(ControlMode.PercentOutput, 0);
          positionNotSet = false;
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
