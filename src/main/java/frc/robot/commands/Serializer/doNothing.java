package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Do Nothing
 * @author Nicholas Stokes
 */
public class doNothing extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer m_subsystem;
  private final KickerWheel m_suubsystem;
  

  /**
   * Reset the Serializer's internal encoder
   *
   * @param subsystem  The subsystem used by this command. (Serializer)
   */
  public doNothing(final Serializer subsystem, final KickerWheel suubsystem) {
    m_subsystem = subsystem;
    m_suubsystem = suubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem,suubsystem);
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
  public void end(final boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    // return (m_subsystem.getSerializerPosition() < target);
    return false;
  }

}
