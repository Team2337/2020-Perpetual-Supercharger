package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Where all the LEDs are controlled
 * 
 * @author Zayd A.
 */

public class LEDBlinkin extends SubsystemBase {
	/*
	 * PREDEFINED COLORS - Add more as needed
	 * http://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf#page=14
	 */
	public double green = 0.75;
	public double off = 0.99;// When the robot is enabled
	public double rainbow = -0.99;
	public double red = 0.61;
	public double strobeWhite = -0.05;
	public double white = 0.92;
	public double blue = 0.97;
	public double purple = 0.96;
	public double pink = 0.88;
	public double darkBlue = 0.84;
	public double aqua = 0.83;
	public double lightBlue = 0.7;
	public double yellow = 0.65;

	// The Blinkin LED controller is treated as a Spark
	private static Spark blinkin;

	// The currently set color
	private static double setColor = 0;

	/**
	 * Controls the LEDs on the Robot using a Blinkin
	 * 
	 * @param pwm The PWM port that the blinkin is plugged into
	 */
	public LEDBlinkin() {
		blinkin = new Spark(Constants.PWMBLINKIN);
		blinkin.setSafetyEnabled(false);
	}

	/**
	 * Sets the color of the LEDs
	 * 
	 * @param color A decimal value from -1 to 1 representing a preset color
	 */
	public void setColor(double color) {
		// If the color being set is the currently set color, do nothing
		if (color != setColor) {
			// Sets the LED colors
			blinkin.set(color);
			// Set the currently set color to the given color
			setColor = color;
		}
	}

	public void initDefaultCommand() {
		// setDefaultCommand(new LEDRuntime(Robot.LED));
	}

	public double status() {
		return blinkin.get();
	}
}