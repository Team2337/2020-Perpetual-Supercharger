package frc.robot.commands.Hopper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

//A command that uses the hopper subsystem to move the left flipper
public class leftHopperExtension extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Hopper subsystem;

  //A command that uses the hopper subsystem to move the left flipper
  public leftHopperExtension(Hopper m_subsystem) {
    subsystem = m_subsystem;
    
    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {

    // Extends left flipper
    subsystem.extendLeftFlipper(true);
  }

  /*
   * Called once the command ends or is interrupted. When interupted, retract
   * the left hopper
   */
  @Override
  public void end(boolean interrupted) {
    subsystem.extendLeftFlipper(false);
  }

  // Retracts left flipper
  @Override
  public boolean isFinished() {
    return false;
  }
}