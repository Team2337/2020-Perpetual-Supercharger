package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the serializer speed
 * @author Nicholas S
 */
public class resetCounter extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer subsystem;

  /**
   * Resets the serializer counter
   * @author Michael Francis
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
    //This will reset the counter in the serializer.
    subsystem.resetCounter();
  }
      

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
  
}
