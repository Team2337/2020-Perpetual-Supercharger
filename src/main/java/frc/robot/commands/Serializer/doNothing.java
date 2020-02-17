package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Does Nothing
 * @author Nicholas Stokes
 */
public class doNothing extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer subsystem;
  public int target;

  /**
   * Does Nothing
   * @author Nicholas Stokes
   */
  public doNothing(Serializer m_subsystem) {
    subsystem = m_subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}
