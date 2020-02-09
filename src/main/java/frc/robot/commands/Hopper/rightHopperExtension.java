package frc.robot.commands.Hopper;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Hopper;

/**
 * A command that uses the hopper subsystem to extend the right flipper
 * @author John.R
 */
public class rightHopperExtension extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Hopper subsystem;

/**
 * A command that uses the hopper subsystem to extend the right flipper
 */
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

  @Override
  public void end(boolean interrupted) {
  }

}