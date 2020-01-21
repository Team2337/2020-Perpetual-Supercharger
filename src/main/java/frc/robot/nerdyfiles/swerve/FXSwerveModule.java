package frc.robot.nerdyfiles.swerve;

import java.lang.annotation.Target;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.sensors.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * @author Bryce G.
 */
public class FXSwerveModule {

    /* --- Ints --- */
    private int moduleNumber;

    /* --- Doubles --- */
    private double angleMotorOffset;
    private double lastTargetAngle;
    private double modTargetAngle;
    private double lastError = 0;
    private double allowableErrorDegree = 3;
    private double kP = 0.63;
    private double kD = 0.02;

    /* --- Booleans --- */
    private boolean isDriveInverted = false;

    /* --- Motor Controllers --- */
    private TalonFX driveMotor;
    private TalonFX angleMotor;

    /* --- Sensors --- */
    public AnalogInput analogAngleSensor;
    
    /* --- Current Limit Stator --- */
    private StatorCurrentLimitConfiguration currentLimitConfigurationAngle = new StatorCurrentLimitConfiguration();
    private StatorCurrentLimitConfiguration currentLimitConfigurationDrive = new StatorCurrentLimitConfiguration();

    /**
     * 
     * @param moduleNumber
     * @param driveMotor
     * @param angleMotor
     * @param angleMotorOffset
     * @param analogAngleSensor
     */
    public FXSwerveModule(int moduleNumber, TalonFX driveMotor, TalonFX angleMotor, double angleMotorOffset, AnalogInput analogAngleSensor) {
        this.moduleNumber = moduleNumber;
        this.driveMotor = driveMotor;
        this.angleMotor = angleMotor;
        this.angleMotorOffset = angleMotorOffset;
        this.analogAngleSensor = analogAngleSensor;
        
        /* --- Set Factory Default --- */
        angleMotor.configFactoryDefault();
        driveMotor.configFactoryDefault();
        
        /*****************************/
        /* ------------------------- */
        /* --- Angle Motor Setup --- */
        /* ------------------------- */
        /*****************************/

        angleMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        angleMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
                
        angleMotor.setNeutralMode(NeutralMode.Coast);
        angleMotor.configOpenloopRamp(0.1); 
        angleMotor.setSensorPhase(false);
        angleMotor.setInverted(false);
        
        /*****************************/
        /* ------------------------- */
        /* --- Drive Motor Setup --- */
        /* ------------------------- */
        /*****************************/

        driveMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
        driveMotor.configClosedloopRamp(0.1);
        driveMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
        driveMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);

        driveMotor.config_kP(0, 15, 0);
        driveMotor.config_kI(0, 0.01, 0);
        driveMotor.config_kD(0, 0.1, 0);
        driveMotor.config_kF(0, 0.2, 0);

        /* --- Motion Magic --- */
        driveMotor.configMotionCruiseVelocity(640, 0);
        driveMotor.configMotionAcceleration(200, 0);

        driveMotor.setNeutralMode(NeutralMode.Coast);

        /* --- Setup Drive Current Limits --- */
        currentLimitConfigurationAngle.currentLimit = 50;
        currentLimitConfigurationAngle.enable = true;
        currentLimitConfigurationAngle.triggerThresholdCurrent = 40;
        currentLimitConfigurationAngle.triggerThresholdTime = 3;

        /* --- Setup Drive Current Limit --- */
        currentLimitConfigurationDrive.currentLimit = 50;
        currentLimitConfigurationDrive.enable = true;
        currentLimitConfigurationDrive.triggerThresholdCurrent = 40;
        currentLimitConfigurationDrive.triggerThresholdTime = 3;
        
        // Set amperage limits
        angleMotor.configStatorCurrentLimit(currentLimitConfigurationAngle, 0);

        driveMotor.configStatorCurrentLimit(currentLimitConfigurationDrive, 0);        
    }

    /**
     * Gets the raw analog input, and divides it by the current 5V reading from 
     * the robot to normalize the sensor value in terms of (0 -> 1)
     * @return - double sensor positional value from (0 -> 1)
     */
    public double getNormalizedAnalogVoltage() {
        return analogAngleSensor.getVoltage() / RobotController.getVoltage5V();
    }

    /**
     * Takes the normalized sensor value, converting it to RADIANS
     * @return - double angle value in RADIANS
     */
    public double getNormalizedAnalogVoltageRadians() {
        return getNormalizedAnalogVoltage() * (2 * Math.PI);
    }
    
    /**
     * Takes the desired angle, and the current angle and computes the delta (current - target)
     * to set the speed to the angle motors to move the module to the 
     * desired angle, without overshooting. 
     * @param targetAngle - double value in radians
     */
    public void setModuleAngle(double targetAngle) {
        /* --- Local doubles --- */
        double errorRad;
        double currentAngle = getNormalizedAnalogVoltageRadians();

        SmartDashboard.putNumber("CurrentAngle " + moduleNumber, getNormalizedAnalogVoltageRadians());

        // Adds the offset angle to the target angle and gets in terms of (-2PI -> 2PI)
        targetAngle = (targetAngle + this.angleMotorOffset) % (2 * Math.PI);

        // Calculates the error
        errorRad = (currentAngle - targetAngle + (2*Math.PI)) % (2*Math.PI);
        // Error deadband (3 degress || 0.052 RAD)
        errorRad = Math.abs(errorRad) < Math.toRadians(allowableErrorDegree) ? 0 : errorRad;

        // Puts the target in the negative direction
        if (errorRad > Math.PI) {
            errorRad -= (Math.PI*2);
        } 
        
        // Makes the decision to invert the motors rather than turn the module
        if (errorRad > Math.PI/2 || errorRad < -Math.PI/2) {
            driveMotor.setInverted(true);
        } else {
            driveMotor.setInverted(false);
        }

        // Gets error in terms of quadrents and removes odd edge cases
        if(errorRad > Math.PI/2 && errorRad < Math.PI) {
            errorRad -= Math.PI;
        } else if(errorRad < -Math.PI/2 && errorRad > -Math.PI) {
            errorRad += Math.PI;
        }

        // Calculates D value 
        double d = Robot.Utilities.calculateDerivative(errorRad, lastError, 0.02);
        lastError = errorRad;
        double speed = (errorRad * kP) + (d * kD);
        setAngleMotorSpeed(speed);
        SmartDashboard.putNumber("ErrorRad " + moduleNumber, errorRad);
        SmartDashboard.putNumber("Power Output" + moduleNumber, errorRad*kP);
    }

    /**
     * Sets the speed of the module angle motor
     * @param speed
     */
    public void setAngleMotorSpeed(double speed) {
        angleMotor.set(ControlMode.PercentOutput, speed);
    }

    /**
     * 
     * @return
     */
    public double getAngleOffset() {
        return this.angleMotorOffset;
    }

    /* DRIVE METHODS */

    /**
     * 
     * @param isDriveInverted
     */
    public void setDriveInverted(boolean isDriveInverted) {
        this.isDriveInverted = isDriveInverted;
    }

    /**
     * 
     * @param speed
     */
    public void setDriveSpeed(double speed) {
        if(isDriveInverted) speed = -speed; 

        driveMotor.set(ControlMode.PercentOutput, speed);
    }

    /* WHOLE MODULE METHODS */

    /**
     * 
     */
    public int getModuleNumber() {
        return this.moduleNumber;
    }
}