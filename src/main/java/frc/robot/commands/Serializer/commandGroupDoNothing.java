package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Checks if the shooter is up to speed
 * @author Michael F
 */
public class commandGroupDoNothing extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer subsystem;
  public int target;

  /**
   * Checks if the shooter is up to speed.
   * <p>Check the variable shooterAtTargetSpeed to check the result of the calculation.
   * @param subsystem  The subsystem used by this command. (Shooter)
   * @param target What the target value for the shooter speed is
   */
  public commandGroupDoNothing(Serializer m_subsystem) {
    subsystem = m_subsystem;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}
