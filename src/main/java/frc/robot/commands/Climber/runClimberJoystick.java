package frc.robot.commands.Climber;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the climber speed
 * @author Michael Francis
 */
public class runClimberJoystick extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Climber Climber;

  private double climberSpeed;
  private double deadband = 0.1;
  private double maxSpeed = 0.6;
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
    if(Climber.getClimberActivated()) {
      climberSpeed = Robot.OI.operatorJoystick.getRightStickY();
      climberSpeed = Robot.Utilities.deadband(climberSpeed, deadband);
      if(Math.abs(climberSpeed) > deadband) {
        Robot.ClimberBrake.disengageBrake();
        Climber.setClimberSpeed(climberSpeed * maxSpeed);
        positionNotSet = true;
      } else {
        if(positionNotSet) {
          Climber.setSetpoint(Climber.getCurrentPosition());
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
