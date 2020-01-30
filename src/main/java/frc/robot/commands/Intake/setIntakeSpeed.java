package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the intake speed
 * @author Michael Francis
 */
public class setIntakeSpeed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake subsystem;
  /** Left intake motor speed */
  private double lspeed;
  /** Right intake motor speed */
  private double rspeed;

  /**
   * Sets the intake speed to a given percent
   * @param m_subsystem The subsystem used by this command. (Intake)
   * @param leftSpeed A double number that sets the speed of the left intake motor
   * @param rightSpeed A double number that sets the speed of the right intake motor
   */
  public setIntakeSpeed(Intake m_subsystem, double leftSpeed, double rightSpeed) {
    subsystem = m_subsystem;
    lspeed = leftSpeed;
    rspeed = rightSpeed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the intake to run at a set speed
    subsystem.setIntakeSpeed(lspeed, rspeed);
  }

  @Override
  public void execute(){
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the intake
    subsystem.stopIntake();
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}
