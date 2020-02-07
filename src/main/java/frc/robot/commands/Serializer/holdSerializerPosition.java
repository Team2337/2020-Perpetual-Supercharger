package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the serializer speed
 * @author Michael Francis
 */
public class holdSerializerPosition extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Serializer subsystem;

  /**
   * Sets the serializer speed to a given percent
   * @param m_subsystem The subsystem used by this command. (serializer)
   */
  public holdSerializerPosition(Serializer m_subsystem) {
    subsystem = m_subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the serializer to stay at a certain position
    subsystem.setSerializerPosition(subsystem.getSerializerPosition());
  }

  @Override
  public void execute(){
    subsystem.setSerializerPosition(subsystem.getSerializerPosition());
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
