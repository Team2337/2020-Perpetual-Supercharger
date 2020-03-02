package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Vision;

  /**
   * Limelight LEDs will turn on
   * <p><br/>Mode 3 is to turn on the LED</p> 
   * @author Zayd A. & Madison J.
   * @category VISION
   */
public class limeLightLEDOn extends InstantCommand {
  private Vision vision;
  /**
  * Limelight LEDs will turn on
  * <p><br/>Mode 3 is to turn on the LED</p> 
  */
  public limeLightLEDOn(Vision vision) {
    this.vision = vision;
    addRequirements(vision);
  }

  @Override
  public void initialize() {
    Robot.Vision.setLEDMode(3);
  }

}