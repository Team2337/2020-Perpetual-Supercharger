package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMUConfiguration;
import com.ctre.phoenix.sensors.PigeonIMU.CalibrationMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Gyro class for CTRE Pigeon Gyro
 * @category CHASSIS
 * @author Team2337 - EngiNERDs
 * Must be initiated after RobotMap
 */
public class Pigeon extends SubsystemBase {

	public static PigeonIMU pidgey;
	public static PigeonIMU.FusionStatus gyrofusionStatus;
	public static PigeonIMU.GeneralStatus gyroGenStatus;
	public double[] ypr_deg;
	public double[] xyz_dps;
	private int timeoutMs = 20;
	
	/**
	 * The subsystem to calibrate the Pigeon (gyro)
	 */
	public Pigeon() {
		pidgey = new PigeonIMU(0);
		//Put this line in, in order to calibrate the pigeon to the correct degree mode
		// pidgey.enterCalibrationMode(CalibrationMode.BootTareGyroAccel, 10);
		gyrofusionStatus = new PigeonIMU.FusionStatus();
		gyroGenStatus = new PigeonIMU.GeneralStatus();
		ypr_deg = new double[3];
		xyz_dps = new double[3];
	}
		

	/**
	 * Periodically request information from the device
	 */
	public void periodic() {
		
		pidgey.getFusedHeading(gyrofusionStatus);
		pidgey.getGeneralStatus(gyroGenStatus);
		pidgey.getYawPitchRoll(ypr_deg);
		pidgey.getRawGyro(xyz_dps);

		//SmartDashboard.putNumber("FusedHeading", pidgey.getFusedHeading());
		SmartDashboard.putNumber("yaw", getYaw());
		//SmartDashboard.putNumber("Pitch", getPitch());
		//SmartDashboard.putNumber("Roll", getRoll());
	}

	/**
	 * Gets the yaw from the gyro
	 * The yaw is the angle of the robot in the Z axis, 
	 * it has some historical connotations from where this value's name comes from:
	 *  --- History ---
	 * In the heyday of large sailing ships, numerous nautical words appeared on the horizon, 
	 * many of which have origins that have never been traced. "Yaw" is one such word. 
	 * It began showing up in print in the 16th century, first as a noun 
	 * (meaning "movement off course" or "side to side movement") and then as a verb. 
	 * For more than 350 years it remained a sailing word, with occasional side trips 
	 * to the figurative sense "to alternate." 
	 * Then dawned the era of airplane flight in the early 20th century, 
	 * and "yawing" was no longer confined to the sea. Nowadays, 
	 * people who love boats still use "yaw" much as did the sailing-men of old, 
	 * but pilots and rocket scientists also refer to the "yawing" of their crafts.
	 * @return yaw - double angle value of the robot's Z axis
	 */
	public double getYaw() {
		double yaw = 0;
		yaw = ypr_deg[0];
		return yaw;
	}

	/**
	 * Returns the pitch from the gyro
	 * @return pitch
	 */
	public double getPitch() {
		double pitch = 0;
		pitch = ypr_deg[1];
		return pitch;
	}

	/**
     * Returns the roll value from the gyro
	 * @return roll
	 */
	public double getRoll() {
		double roll = 0;
		roll = ypr_deg[2];
		return roll;
	}

	/**
	 * Returns the absolute compass heading of the gyro 
	 * @return returns the absolute compass heading 
	 */
	public double getAbsoluteCompassHeading() {
		return pidgey.getAbsoluteCompassHeading();
	}
	
	/**
	 * Resets the yaw on the pigeon to 0
	 */
	public void resetPidgey() {
		pidgey.setYaw(0, timeoutMs);
	}

	/**
	 * Gets the rate at which the robot is spinning
	 * @return angularRate
	 */
	public double getAngularRate() {
		double angularRate;
		angularRate = xyz_dps[2];
		return angularRate;
	}
	
	/**
	 * Use to manually set the yaw in degrees
	 * 
	 */
	public void manualSetYaw(double yaw) {
		yaw *= 64;
		pidgey.setYaw(yaw, timeoutMs);
	}

	 /**
	 * Returns the FusedHeading value from the gyro
	 * @return FusedHeading
	 */
	public double getFusedHeading() {
		return pidgey.getFusedHeading();
	}	
	
	/**
    * Returns the Temperature value from the gyro
	* @return Temp
	*/
   public double getTemp() {
	   return pidgey.getTemp();

   }
}