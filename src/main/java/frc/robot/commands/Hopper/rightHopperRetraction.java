package frc.robot.commands.Hopper;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Hopper;

/**
 * A command that uses the hopper subsystem to retract the right flipper
 * @author Nicholas.S
 */
public class rightHopperRetraction extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Hopper subsystem;

  /**
   * A command that uses the hopper subsystem to retract the right flipper
   */
  public rightHopperRetraction(Hopper m_subsystem) {
    subsystem = m_subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {

    // Retracts right flipper
    subsystem.extendRightFlipper(false);
  }

  @Override
  public void end(boolean interrupted) {
  }

}