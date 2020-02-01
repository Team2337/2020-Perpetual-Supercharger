package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CanbusDistanceSensor;

/**
 * Add your docs here.
 */
public class configureDevice extends InstantCommand {
  /**
   * Add your docs here.
   */
  private int myNewDeviceNumber;
  private int myOldDeviceNumber;

  public configureDevice(int oldNumber, int newDeviceNumber) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    myOldDeviceNumber = oldNumber;
    myNewDeviceNumber = newDeviceNumber;
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    CanbusDistanceSensor.configureDevice(myOldDeviceNumber, myNewDeviceNumber);
  }

}