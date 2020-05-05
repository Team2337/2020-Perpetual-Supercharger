package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.LED.LEDRuntime;

/**
 * Controls the LED strip
 * @author Bryce G.
 */
public class LEDs extends SubsystemBase {

  private Solenoid LEDs;

  /**
   * Controls the LED strip
   */
  public LEDs() {
    LEDs = new Solenoid(Constants.PCM0, Constants.PCMLEDSTRIP);
    setDefaultCommand(new LEDRuntime(this));
  }

  /**
   * Turns the LEDs on
   */
  public void turnOnLEDs() {
    LEDs.set(true);
  }

  /**
   * Turns the LEDs off
   */
  public void turnOffLEDs() {
    LEDs.set(false);
  }

  /**
   * LED Status
   */
  public boolean status() {
    return LEDs.get();
  }
}