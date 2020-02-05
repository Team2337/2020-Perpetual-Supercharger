package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CanbusDistanceSensor;

/**
 * Add your docs here.
 */
public class identifyDevice extends InstantCommand {
  /**
   * Add your docs here.
   */
  private int myDeviceNumber;

  public identifyDevice(int deviceNumber) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    myDeviceNumber = deviceNumber;
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    CanbusDistanceSensor.identifyDevice(myDeviceNumber);
  }

}
