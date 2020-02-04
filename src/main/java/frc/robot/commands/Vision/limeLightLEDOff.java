package frc.robot.commands.Vision;

import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.InstantCommand;

  /**
   * Limelight LED will turn off
   * <p><br/>Mode 1 turns off the LEDs</p>
   * @author Zayd A. & Madison J.
   * @category VISION
   */
public class limeLightLEDOff extends InstantCommand {
  private Vision subsystem;

  /**
   * Limelight LED will turn off
   * <p><br/>Mode 1 turns off the LEDs</p>
   */
  public limeLightLEDOff(Vision subsystem) {
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setLEDMode(1);
  }

}