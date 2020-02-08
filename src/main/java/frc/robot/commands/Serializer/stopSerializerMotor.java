package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Stops the serializer motor.
 * @author Michael Francis & Nicholas Stokes
 */
public class stopSerializerMotor extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Serializer m_subsystem;

  /**
   * Stops the serializer motor.
   * @param subsystem The subsystem used by this command. (Serializer)
   */
  public stopSerializerMotor(Serializer subsystem) {
    m_subsystem = subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will stop the serializer
    m_subsystem.stopSerializer();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
