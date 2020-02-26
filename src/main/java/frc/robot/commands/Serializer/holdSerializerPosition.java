package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Holds the serializer position
 * @author Nicholas Stokes
 */
public class holdSerializerPosition extends CommandBase{
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer m_subsystem;
  public double position;

  /**
   * Holds the serializer position
   */
  public holdSerializerPosition(Serializer subsystem){
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //This sets the position to the current position, which causes the position to hold
    position = m_subsystem.getSerializerPosition();
    m_subsystem.setPosition(position);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}
