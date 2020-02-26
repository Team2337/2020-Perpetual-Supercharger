package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TimeOfFlight;

/**
 * Configures the device by sending previously set information to the sensor.
 */
public class configureDevice extends InstantCommand {
  
  private int myNewDeviceNumber;
  private int myOldDeviceNumber;
  private TimeOfFlight subsystem;

  /**
   * Configures the device by sending previously set information to the sensor.
   * This includes getting its information and setting its reversed mode.
   * 
   * Also used to change the ID of a sensor.
   */
  public configureDevice(TimeOfFlight m_subsystem) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    myOldDeviceNumber = 0;
    myNewDeviceNumber = 0;
    subsystem = m_subsystem;
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    TimeOfFlight.configureDevice(myOldDeviceNumber, myNewDeviceNumber);
  }

}
