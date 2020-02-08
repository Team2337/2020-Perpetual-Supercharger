package frc.robot.commands.ControlPanelSpinner;

import frc.robot.subsystems.ControlPanelSpinner;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Stops the control panel spinner motors.
 * @author Michael Francis
 */
public class stopCPSMotor extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ControlPanelSpinner m_subsystem;

  /**
   * Stops the control panel spinner motors.
   * @param subsystem The subsystem used by this command.
   */
  public stopCPSMotor(ControlPanelSpinner subsystem) {
    m_subsystem = subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will stop the control panel spinner
    m_subsystem.stopControlPanelSpinner();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
