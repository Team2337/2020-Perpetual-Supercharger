package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Puts a number (always double) on the dashboard using a variety of commands
 * <ul>
 * <li>putN: put a number on the dashboard</li>
 * <li>putN0-putN4: put a rounded number on the dashboard</li>
 * </ul>
 */
public class SD {
	public SD() {

	}

	/**
	 * Puts a number on the dashboard
	 * @param name Name to put on the dashboard
	 * @param value Number (double) to put on the dashboard
	 */
	public static void putN(String name, double value) {

		SmartDashboard.putNumber(name, value);
	}

	/**
	 * Puts a value rounded (with no decimal places) on the dashboard
	 * @param name Name of item to put on dashboard
	 * @param value Value to be rounded and put on the dashboard
	 */
	public static void putN0(String name, double value) {

		SmartDashboard.putNumber(name, Math.round(value));
	}

	/**
	 * Puts rounded number (to tenths place) to put on the dashboard
	 * @param name Name of item to put on dashboard
	 * @param value Value to be rounded and put on the dashboard
	 */
	public static void putN1(String name, double value) {

		SmartDashboard.putNumber(name, Math.round(value * 10.) / 10.);
	}

	/**
	 * Puts rounded number (to hundredths place) to put on the dashboard
	 * @param name Name of item to put on dashboard
	 * @param value Value to be rounded and put on the dashboard
	 */
	public static void putN2(String name, double value) {

		SmartDashboard.putNumber(name, Math.round(value * 100.) / 100.);
	}

	/**
	 * Puts rounded number (to thousandths place) to put on the dashboard
	 * @param name Name of item to put on dashboard
	 * @param value Value to be rounded and put on the dashboard
	 */
	public static void putN3(String name, double value) {

		SmartDashboard.putNumber(name, Math.round(value * 1000.) / 1000.);
	}

	/**
	 * Puts rounded number (to ten thousandths place) to put on the dashboard
	 * @param name Name of item to put on dashboard
	 * @param value Value to be rounded and put on the dashboard
	 */
	public static void putN4(String name, double value) {

		SmartDashboard.putNumber(name, Math.round(value * 10000.) / 10000.);
	}

}
