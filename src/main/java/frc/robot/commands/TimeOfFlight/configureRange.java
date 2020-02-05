package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CanbusDistanceSensor;

/**
 * Add your docs here.
 */
public class configureRange extends InstantCommand {
  /**
   * Add your docs here.
   */
  private int myRange;
  private int myId;

  public configureRange(int id, int range) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    myRange = range;
    myId = id;
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    CanbusDistanceSensor.configureRange(myId, myRange);
  }

}
