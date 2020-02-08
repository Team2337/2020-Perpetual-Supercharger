package frc.robot.commands.ControlPanelSpinner;

import frc.robot.subsystems.ControlPanelSpinner;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the control panel spinner speed
 * @author Michael Francis
 */
public class holdCPSPosition extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ControlPanelSpinner subsystem;

  /**
   * Sets the control panel spinner speed to a given percent
   * @param m_subsystem The subsystem used by this command. (Control Panel Spinner)
   */
  public holdCPSPosition(ControlPanelSpinner m_subsystem) {
    subsystem = m_subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the control panel spinner to stay at a certain position
    subsystem.setControlPanelSpinnerPosition(subsystem.getControlPanelSpinnerPosition());
  }

  @Override
  public void execute(){
    subsystem.setControlPanelSpinnerPosition(subsystem.getControlPanelSpinnerPosition());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}
