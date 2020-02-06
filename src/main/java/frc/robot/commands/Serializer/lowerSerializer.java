package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the serializer speed
 * @author Michael Francis
 */
public class lowerSerializer extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Serializer subsystem;
  /** Serializer motor speed */
  private double speed;

  /**
   * Sets the serializer speed to a given percent
   * @param m_subsystem The subsystem used by this command.
   * @param m_speed A negative number between 0 and -1 that sets the speed of the serializer
   */
  public lowerSerializer(Serializer m_subsystem, double m_speed) {
    subsystem = m_subsystem;
    speed = Math.abs(m_speed)*-1;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the serializer to run at a set speed
    subsystem.setSerializerSpeed(speed);
  }

  @Override
  public void execute(){
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the serializer
    subsystem.stopSerializer();
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}
