package frc.robot.commands.Compressor;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.AirCompressor;

/**
 * Disables the compressor
 */
public class compressorTurnOff extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final AirCompressor subsystem;

  /**
   * Disables the compressor
   */
  public compressorTurnOff(AirCompressor m_subsystem) {
    subsystem = m_subsystem;
    addRequirements(Robot.AirCompressor);
  }

  // Disables the compressor
  @Override
  public void initialize() {
    subsystem.disable();
  }

  @Override
  public void end(boolean inturrupted) {

  }
}
