package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.LED.LEDRuntime;

/**
 * Where all the LEDs are controlled
 * 
 * @author Zayd A.
 */

public class LED extends SubsystemBase {
	/*
	 * PREDEFINED COLORS - Add more as needed
	 * http://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf#page=14
	 */
	public static double green = 0.75;
	public static double off = 0.99;// When the robot is enabled
	public static double rainbow = -0.99; // When the robot is disabled
	public static double red = 0.61;
	public static double strobeWhite = -0.05; // When the cargo intake is running
	public static double white = 0.92;
	public static double blue = 0.97;
	public static double purple = 0.96;
	public static double pink = 0.88;
	public static double darkBlue = 0.84;
	public static double aqua = 0.83;
	public static double lightBlue = 0.7;
	public static double yellow = 0.65;

	// The Blinkin LED controller is treated as a Spark
	private static Spark blinkin;

	// The currently set color
	private static double setColor = 0;

	/**
	 * Controls the LEDs on the Robot using a Blinkin
	 * 
	 * @param pwm The PWM port that the blinkin is plugged into
	 */
	public LED() {
		//blinkin = new Spark(Robot.Constants.blinkinPWM);
	//	blinkin.setSafetyEnabled(false);
	}

	/**
	 * Sets the color of the LEDs
	 * 
	 * @param color A decimal value from -1 to 1 representing a preset color
	 */
	public static void setColor(double color) {
		// If the color being set is the currently set color, do nothing
		if (color != setColor) {
			// Sets the LED colors
			blinkin.set(color);
			// Set the currently set color to the given color
			setColor = color;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new LEDRuntime());
	}

	public double status() {
		return blinkin.get();
	}
}