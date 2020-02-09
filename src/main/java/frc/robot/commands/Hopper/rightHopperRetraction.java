package frc.robot.commands.Hopper;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Hopper;

/**A command that uses the hopper subsystem to move the right flipper
 * 
 * @author Nicholas.S
 */
public class rightHopperRetraction extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Hopper subsystem;

  /**
   * A command that uses the hopper subsystem to move the right flipper
   */
  public rightHopperRetraction(Hopper m_subsystem) {
    subsystem = m_subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {

    // retracts right flipper
    subsystem.extendRightFlipper(false);
  }

  /*
   * Called once the command ends or is interrupted. When interupted, retract
   * the right hopper
   */
  @Override
  public void end(boolean interrupted) {
  }

}