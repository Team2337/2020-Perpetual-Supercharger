package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TimeOfFlight;

/**
 * Identifies the device by reading the sensor info and checking to make sure that it is correct
 */
public class identifyDevice extends InstantCommand {
  /**
   * Identifies the device by reading the sensor info and checking to make sure that it is correct
   */
  public identifyDevice() {

  }

  // Called once when the command executes
  @Override
  public void initialize() {
    TimeOfFlight.identifyDevice();
  }

}
