/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.nerdyfiles;


/**
 * Helper class to implement "Cheesy Drive". "Cheesy Drive" simply means that
 * the "turning" stick controls the curvature of the robot's path rather than
 * its rate of heading change. This helps make the robot more controllable at
 * high speeds. Also handles the robot's quick turn functionality - "quick turn"
 * overrides constant-curvature turning for turn-in-place maneuvers.
 * 
 */
public class BobDriveHelper {

	//private static final double kThrottleDeadband = 0.0;
	private static final double kWheelDeadband = 0.0;

	// These factor determine how fast the wheel traverses the "non linear" sine
	// curve.
	private static final double kHighWheelNonLinearity = 0.65;
	private static final double kLowWheelNonLinearity = 0.5;

	private static final double kHighNegInertiaScalar = 4.0;

	private static final double kLowNegInertiaThreshold = 0.65;
	private static final double kLowNegInertiaTurnScalar = 3.5;
	private static final double kLowNegInertiaCloseScalar = 4.0;
	private static final double kLowNegInertiaFarScalar = 5.0;

	private static final double kHighSensitivity = 0.95;
	private static final double kLowSensitiity = 1.3;

	private static final double kQuickStopDeadband = 0.2; 
	private static final double kQuickStopWeight = 0.1;
	private static final double kQuickStopScalar = 1.0;
	
	private static double mOldWheel = 0.0;
	private static double mQuickStopAccumlator = 0.0;
	private static double mNegInertiaAccumlator = 0.0;

public static double leftPwm, rightPwm;

//public DriveSignal cheesyDrive(double throttle, double wheel, boolean isQuickTurn, boolean isHighGear) {

	public static void cheesyDrive(double throttle, double wheel, boolean isQuickTurn, boolean isHighGear) {
		//wheel = handleDeadband(wheel, kWheelDeadband);
		wheel = (Math.abs(wheel) > Math.abs(kWheelDeadband)) ? wheel : 0.0;
		//throttle = handleDeadband(throttle, kThrottleDeadband);
		throttle = (Math.abs(throttle) > Math.abs(kWheelDeadband)) ? throttle : 0.0;

		double negInertia = wheel - mOldWheel;
		mOldWheel = wheel;

		double wheelNonLinearity;
		if (isHighGear) {
			wheelNonLinearity = kHighWheelNonLinearity;
			final double denominator = Math.sin(Math.PI / 2.0 * wheelNonLinearity);
			// Apply a sin function that's scaled to make it feel better.
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / denominator;
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / denominator;
		} else {
			wheelNonLinearity = kLowWheelNonLinearity;
			final double denominator = Math.sin(Math.PI / 2.0 * wheelNonLinearity);
			// Apply a sin function that's scaled to make it feel better.
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / denominator;
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / denominator;
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / denominator;
		}

//double leftPwm, rightPwm;
		double sensitivity, overPower;

		double angularPower;
		double linearPower;

		// Negative inertia!
		double negInertiaScalar;
		if (isHighGear) {
			negInertiaScalar = kHighNegInertiaScalar;
			sensitivity = kHighSensitivity;
		} else {
			if (wheel * negInertia > 0) {
				// If we are moving away from 0.0, aka, trying to get more wheel.
				negInertiaScalar = kLowNegInertiaTurnScalar;
			} else {
				// Otherwise, we are attempting to go back to 0.0.
				if (Math.abs(wheel) > kLowNegInertiaThreshold) {
					negInertiaScalar = kLowNegInertiaFarScalar;
				} else {
					negInertiaScalar = kLowNegInertiaCloseScalar;
				}
			}
			sensitivity = kLowSensitiity;
		}
		double negInertiaPower = negInertia * negInertiaScalar;
		mNegInertiaAccumlator += negInertiaPower;

		wheel = wheel + mNegInertiaAccumlator;
		if (mNegInertiaAccumlator > 1) {
			mNegInertiaAccumlator -= 1;
		} else if (mNegInertiaAccumlator < -1) {
			mNegInertiaAccumlator += 1;
		} else {
			mNegInertiaAccumlator = 0;
		}
		linearPower = throttle;

		// Quickturn!
		if (isQuickTurn) {
			if (Math.abs(linearPower) < kQuickStopDeadband) {
				double alpha = kQuickStopWeight;
				mQuickStopAccumlator = (1 - alpha) * mQuickStopAccumlator
						+ alpha * limit(wheel, 1.0) * kQuickStopScalar;
			}
			overPower = 1.0;
			angularPower = wheel;
		} else {
			overPower = 0.0;
			angularPower = Math.abs(throttle) * wheel * sensitivity - mQuickStopAccumlator;
			if (mQuickStopAccumlator > 1) {
				mQuickStopAccumlator -= 1;
			} else if (mQuickStopAccumlator < -1) {
				mQuickStopAccumlator += 1;
			} else {
				mQuickStopAccumlator = 0.0;
			}
		}

		rightPwm = leftPwm = linearPower;
		leftPwm += angularPower;
		rightPwm -= angularPower;

		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}
//return new DriveSignal(leftPwm, rightPwm);
	}

// added getters
	public static double getLeft() {
		return leftPwm;
	}

	public static double getRight() {
		return rightPwm;
	}

	public double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
    
    /**
	 * Limits the given input to the given magnitude.
	 */
	public static double limit(double v, double maxMagnitude) {
		return limit(v, -maxMagnitude, maxMagnitude);
	}

	public static double limit(double v, double min, double max) {
		return Math.min(max, Math.max(min, v));
	}
}