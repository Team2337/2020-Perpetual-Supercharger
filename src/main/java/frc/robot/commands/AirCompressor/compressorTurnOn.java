package frc.robot.commands.AirCompressor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/**
 * Enables the compressor
 */
public class compressorTurnOn extends CommandBase {

  // CONSTRUCTOR
  public compressorTurnOn() {
    addRequirements(Robot.AirCompressor);
  }

  // Enables the compressor
  @Override
  public void initialize() {
    Robot.AirCompressor.enable();
  }

  // Ends the command once the compressor is enabled
  @Override
  public boolean isFinished() {
    return true;
  }
}
