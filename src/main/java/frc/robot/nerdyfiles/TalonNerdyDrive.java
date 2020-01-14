/**
 * Fusion.Drive.NerdyDrive
 *  Modified WPI Library to allow use of Talons in RobotDrive
 * @author Team2337 - Enginerds
 */

package frc.robot.nerdyfiles;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonNerdyDrive {
	private TalonSRX left;
	private TalonSRX right;

	public double m_quickStopAccumulator = 0.0;
	public double m_quickStopThreshold = 0.2;
	public double m_quickStopAlpha = 0.1;
	public double m_maxOutput = 1.0;
	public double m_deadband = 0.2;

	/**
	 * NerdyDrive - A customized RobotDrive
	 * 
	 * @param left  Left TalonSRX Motor Controller
	 * @param right Right TaonSRX Motor Controller
	 */
	public TalonNerdyDrive(TalonSRX left, TalonSRX right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Arcade Drive - Allows for speed and turn inputs
	 * 
	 * @param speed - Power for moving forward
	 * @param turn  - Power for turning
	 */
	public void arcadeDrive(double xSpeed, double zRotation, boolean squaredInputs) {
		xSpeed = limit(xSpeed);
		xSpeed = applyDeadband(xSpeed, m_deadband);

		zRotation = limit(zRotation);
		zRotation = applyDeadband(zRotation, m_deadband);

		// Square the inputs (while preserving the sign) to increase fine control
		// while permitting full power.
		if (squaredInputs) {
			xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
			zRotation = Math.copySign(zRotation * zRotation, zRotation);
		}

		double leftMotorOutput;
		double rightMotorOutput;

		double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

		if (xSpeed >= 0.0) {
			// First quadrant, else second quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			} else {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			}
		} else {
			// Third quadrant, else fourth quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			}
		}

		this.left.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput);
		this.right.set(ControlMode.PercentOutput, limit(rightMotorOutput) * m_maxOutput);

	}

	public void curvatureDrive(double speed, double zRotation, boolean isQuickTurn) {

		double angularPower;
		boolean overPower;

		if (isQuickTurn) {
			if (Math.abs(speed) < m_quickStopThreshold) {
				m_quickStopAccumulator = (1 - m_quickStopAlpha) * m_quickStopAccumulator
						+ m_quickStopAlpha * limit(zRotation) * 2;
			}
			overPower = true;
			angularPower = zRotation;
		} else {
			overPower = false;
			angularPower = Math.abs(speed) * zRotation - m_quickStopAccumulator;

			if (m_quickStopAccumulator > 1) {
				m_quickStopAccumulator -= 1;
			} else if (m_quickStopAccumulator < -1) {
				m_quickStopAccumulator += 1;
			} else {
				m_quickStopAccumulator = 0.0;
			}
		}

		double leftMotorOutput = speed + angularPower;
		double rightMotorOutput = speed - angularPower;

		// If rotation is overpowered, reduce both outputs to within acceptable range
		if (overPower) {
			if (leftMotorOutput > 1.0) {
				rightMotorOutput -= leftMotorOutput - 1.0;
				leftMotorOutput = 1.0;
			} else if (rightMotorOutput > 1.0) {
				leftMotorOutput -= rightMotorOutput - 1.0;
				rightMotorOutput = 1.0;
			} else if (leftMotorOutput < -1.0) {
				rightMotorOutput -= leftMotorOutput + 1.0;
				leftMotorOutput = -1.0;
			} else if (rightMotorOutput < -1.0) {
				leftMotorOutput -= rightMotorOutput + 1.0;
				rightMotorOutput = -1.0;
			}
		}
		this.left.set(ControlMode.PercentOutput, leftMotorOutput);
		this.right.set(ControlMode.PercentOutput, rightMotorOutput);
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		tankDrive(leftSpeed, rightSpeed, false);
	}

	public void tankDrive(double leftSpeed, double rightSpeed, boolean squaredInputs) {

		leftSpeed = limit(leftSpeed);
		// leftSpeed = applyDeadband(leftSpeed, m_deadband);

		rightSpeed = limit(rightSpeed);
		// rightSpeed = applyDeadband(rightSpeed, m_deadband);

		// Square the inputs (while preserving the sign) to increase fine control
		// while permitting full power.
		if (squaredInputs) {
			leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed);
			rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed);
		}

		this.left.set(ControlMode.PercentOutput, leftSpeed);
		this.right.set(ControlMode.PercentOutput, rightSpeed);
	}

	protected double limit(double value) {
		if (value > 1.0) {
			return 1.0;
		}
		if (value < -1.0) {
			return -1.0;
		}
		return value;
	}

	protected double applyDeadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}
}