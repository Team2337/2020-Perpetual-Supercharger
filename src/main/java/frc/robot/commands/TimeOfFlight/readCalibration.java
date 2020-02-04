package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CanbusDistanceSensor;

/**
 * Add your docs here.
 */
public class ReadCalibration extends InstantCommand {
  /**
   * Add your docs here.
   */
  private int myId;

  public ReadCalibration(int id) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    myId = id;
    ;

  }

  // Called once when the command executes
  @Override
  public void initialize() {
    CanbusDistanceSensor.readCalibrationState(myId);
  }

}
