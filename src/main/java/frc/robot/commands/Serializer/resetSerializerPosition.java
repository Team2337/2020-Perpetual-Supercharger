package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Move balls back to ready the kicker wheel so that the kicker wheel can get up
 * to speed
 * 
 * @author Nicholas Stokes
 */
public class resetSerializerPosition extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer m_subsystem;

  /**
   * Reset the Serializer's internal encoder
   *
   * @param subsystem  The subsystem used by this command. (Serializer)
   */
  public resetSerializerPosition(final Serializer subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This calls the Subsystem method positionShift with a value designated in OI
    m_subsystem.resetSerializerPosition();
  }

  @Override
  public void execute() {
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    // return (m_subsystem.getSerializerPosition() < target);
    return false;
  }

}
