package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the serializer speed
 * @author Michael Francis
 */
public class prepareSerializerShoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Serializer subsystem;
  /** Serializer motor speed */
  private double speed;
  private int getEncoder;
  private int delta;

  /**
   * Sets the serializer speed to a given percent
   * @param m_subsystem The subsystem used by this command.
   * @param m_speed A negative number between 0 and -1 that sets the speed of the serializer
   */
  public prepareSerializerShoot(Serializer m_subsystem) {
    subsystem = m_subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the serializer to run at a set speed
    getEncoder = subsystem.getSerializerPosition();
    speed = -0.1;
    delta = 100;//placeholder value
  }

  @Override
  public void execute(){
    subsystem.setSerializerSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the serializer
    subsystem.stopSerializer();
  }

  @Override
  public boolean isFinished(){
    if(subsystem.getSerializerPosition() <= getEncoder-delta){//This may need to change to a greater than
      return true;
    }else{
      return false;
    }
  }
}
