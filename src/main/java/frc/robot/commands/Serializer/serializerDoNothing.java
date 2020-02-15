package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Do Nothing
 * @author Nicholas Stokes
 */
public class serializerDoNothing extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer m_subsystem;
  

  /**
   * Reset the Serializer's internal encoder
   *
   * @param subsystem  The subsystem used by this command. (Serializer)
   */
  public serializerDoNothing(Serializer subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This calls the Subsystem method positionShift with a value designated in OI

  }

  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    // return (m_subsystem.getSerializerPosition() < target);
    return false;
  }

}
