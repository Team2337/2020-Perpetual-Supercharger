package frc.robot.commands.Serializer;

import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Checks if the kicker is up to speed
 * @author Michael F
 */
public class checkIfKickerUpToSpeed extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final KickerWheel subsystem;
  public double target;

  /**
   * Checks if the kicker is up to speed
   * <p>Check the variable kickerAtTargetSpeed to check the result of the calculation.
   * @param m_subsystem  The subsystem used by this command. (Kicker)
   * @param m_target What the target value for the shooter speed is
   */
  public checkIfKickerUpToSpeed(KickerWheel m_subsystem, double m_target) {
    subsystem = m_subsystem;
    target = m_target;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    subsystem.checkKickerSpeed(target);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}
