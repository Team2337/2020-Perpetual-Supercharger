package frc.robot.commands.AirCompressor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/**
 * Disables the compressor
 */
public class compressorTurnOff extends CommandBase {

  // CONSTRUCTOR
  public compressorTurnOff() {
    addRequirements(Robot.AirCompressor);
  }

  // Disables the compressor
  @Override
  public void initialize() {
    Robot.AirCompressor.disable();
  }

  // Ends the command once the compressor is disabled
  @Override
  public boolean isFinished() {
    return true;
  }
}
