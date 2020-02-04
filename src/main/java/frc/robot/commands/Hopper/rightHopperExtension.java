package frc.robot.commands.Hopper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

//A command that uses the hopper subsystem to move the right flipper
public class rightHopperExtension extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Hopper subsystem;

//A command that uses the hopper subsystem to move the right flipper
  public rightHopperExtension(Hopper m_subsystem) {
    subsystem = m_subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {

    // Extends right flipper
    subsystem.extendRightFlipper(true);
  }

  /*
   * Called once the command ends or is interrupted. When interupted, retract
   * the right hopper
   */
  @Override
  public void end(boolean interrupted) {
    subsystem.extendRightFlipper(false);
  }

  @Override
  // Retracts right flipper
  public boolean isFinished() {
    return false;
  }
}