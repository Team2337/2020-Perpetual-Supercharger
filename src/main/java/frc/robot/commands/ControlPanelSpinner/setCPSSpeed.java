package frc.robot.commands.ControlPanelSpinner;

import frc.robot.subsystems.ControlPanelSpinner;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the control panel spinner speed
 * @author Michael Francis
 */
public class setCPSSpeed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ControlPanelSpinner subsystem;
  /** Left control panel spinner motor speed */
  private double speed;

  /**
   * Sets the control panel spinner speed to a given percent
   * @param m_subsystem The subsystem used by this command.
   * @param m_speed A double number that sets the speed of the left control panel spinner motor
   */
  public setCPSSpeed(ControlPanelSpinner m_subsystem, double m_speed) {
    subsystem = m_subsystem;
    speed = m_speed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the control panel spinner to run at a set speed
    subsystem.setControlPanelSpinnerSpeed(speed);
  }

  @Override
  public void execute(){
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the control panel spinner
    subsystem.stopControlPanelSpinner();
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}
