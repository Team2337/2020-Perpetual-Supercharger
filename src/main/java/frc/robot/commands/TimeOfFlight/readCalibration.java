package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TimeOfFlight;

/**
 * Returns the x and y position of the object being read and the offset of the range in milimeters
 */
public class readCalibration extends InstantCommand {
  /**
   * Returns the x and y position of the object being read and the offset of the range in milimeters
   */
  public readCalibration() {

  }

  // Called once when the command executes
  @Override
  public void initialize() {
    TimeOfFlight.readCalibrationState();
  }

}
