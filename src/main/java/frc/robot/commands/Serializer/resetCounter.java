package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Resets the counter 
 * @author Nicholas Stokes
 */
public class resetCounter extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer subsystem;

  /**
   * Sets the serializer speed
   * 
   * @author Nicholas Stokes
   * @param serializer The subsystem used by this command. (Serializer)
   */
  public resetCounter(Serializer serializer) {
    subsystem = serializer;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the serializer to run at a set speed
    subsystem.resetCounter();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
  
}
