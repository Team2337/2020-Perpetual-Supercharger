package frc.robot.commands.Compressor;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.AirCompressor;

/**
 * Enables the compressor
 */
public class compressorTurnOn extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final AirCompressor subsystem;

  /**
   * Enables the compressor
   */
  public compressorTurnOn(AirCompressor m_subsystem) {
    subsystem = m_subsystem;
    addRequirements(Robot.AirCompressor);
  }

  @Override
  public void initialize() {
    subsystem.enable();
  }

  // Ends the command once the compressor is enabled
  @Override
  public void end(boolean interrupted) {
  }
}
