package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Contorls the lightsaber
 * @author Madison J.
 */
public class Lightsaber extends SubsystemBase {

  public static Solenoid LightsaberLight;

  public Lightsaber() {
  LightsaberLight = new Solenoid(Constants.PCM0, 0);
  }

  /**
   * Turns the lightsaber on to the set mode
   * @param mode - The mode of the lightsaber either on or off
   */
  public void turnOnLightSaber(boolean mode) {
    LightsaberLight.set(mode);
  }

}