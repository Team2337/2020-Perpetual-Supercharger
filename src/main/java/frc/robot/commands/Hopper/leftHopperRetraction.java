package frc.robot.commands.Hopper;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Hopper;
/**
 * A command that uses the hopper subsystem to retract the left flipper
 * @author Nicholas.S
 */
public class leftHopperRetraction extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Hopper subsystem;

  /**
   * A command that uses the hopper subsystem to retract the left flipper
   */
  public leftHopperRetraction(Hopper m_subsystem) {
    subsystem = m_subsystem;
    
    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {

    // Retracts left flipper
    subsystem.extendLeftFlipper(false);
  }

  /*
   * Called once the command ends or is interrupted. When interupted, retract
   * the left hopper
   */
  @Override
  public void end(boolean interrupted) {
    
  }

}