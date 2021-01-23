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
public class runClimber extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Climber Climber;

  private double setpoint;
  private boolean isInstantCommand;

  /**
   * Sets the climber speed to a given percent
   * @param m_subsystem The subsystem used by this command. (climber)
   * @param m_speed A double number that sets the speed of the climber motor
   */
  public runClimber(Climber climber, double setpoint, boolean isInstantCommand) {
    Climber = climber;
    this.setpoint = setpoint;
    this.isInstantCommand = isInstantCommand;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(Climber.getClimberActivated()) {
      if(isInstantCommand) {
        setpoint = Climber.getCurrentPosition();
      }
      Climber.setSetpoint(setpoint);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return isInstantCommand;
  }
}
