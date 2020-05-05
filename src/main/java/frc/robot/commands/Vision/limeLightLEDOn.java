package frc.robot.commands.Vision;

import frc.robot.Robot;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.InstantCommand;

  /**
   * Limelight LEDs will turn on
   * <p><br/>Mode 3 is to turn on the LED</p> 
   * @author Zayd A. & Madison J.
   * @category VISION
   */
public class limeLightLEDOn extends InstantCommand {
  /**
  * Limelight LEDs will turn on
  * <p><br/>Mode 3 is to turn on the LED</p> 
  */
  public limeLightLEDOn(Vision vision) {
    addRequirements(vision);
  }

  @Override
  public void initialize() {
    Robot.Vision.setLEDMode(3);
  }

}