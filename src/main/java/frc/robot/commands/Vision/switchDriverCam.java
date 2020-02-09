package frc.robot.commands.Vision;

import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Drivecam will be turned on
 * @author Zayd A. & Madison J.
 * @category VISION
 */
public class switchDriverCam extends InstantCommand {
  private Vision subsystem;

  /**
   * Drivecam will be turned on
   */
  public switchDriverCam(Vision subsystem) {
    this.subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    subsystem.switchPipeLine(9);
  }
}