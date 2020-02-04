package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

 /** 
 *  Subsystem for the Feeder+Serializer 
 * The Feeder+Serializer is a mechanism to feed 
 * up to the shooter
 * Just to clarify, any reference to the feeder
 * is referencing the Feeder+Serializer just to make wording easier
 * For more information, see the wiki 
 * @author Nicholas Stokes
 */
public class SerializerFeeder extends SubsystemBase {
  /**
   * Specifies whether or not the Feeder will be in debug mode.
   * Also sets up posistion stuff (referenced later)
   * @see #periodic()
   */
  public final boolean feederDebug = false;
  public double targetPosition;
  final double kP = 0.95;
  final double kI = 0;
  final double kD = 0;
  final double kF = 0;
  // Motors
  public TalonFX serializerMotor;
  public TalonFXConfiguration FXConfig;

  // Current limit configuration
  private StatorCurrentLimitConfiguration currentLimitConfigurationFeederMotors = new StatorCurrentLimitConfiguration();
TalonFXConfiguration config = new TalonFXConfiguration();
  /**
   * Creates a new Serializer+Feeder subsystem and sets up the motors to their corresponding
   * ports.
   */
  public SerializerFeeder() {
  // The configuration, ramp rate, and inversion are set here as well as the ports.
    serializerMotor = new TalonFX(Constants.SERIALIZING);
    serializerMotor.setInverted(false);
    serializerMotor.configOpenloopRamp(0.2);
    FXConfig = new TalonFXConfiguration();
    
    // Set up the current configuration
    currentLimitConfigurationFeederMotors.currentLimit = 50;
    currentLimitConfigurationFeederMotors.enable = true;
    currentLimitConfigurationFeederMotors.triggerThresholdCurrent = 40;
    currentLimitConfigurationFeederMotors.triggerThresholdTime = 3;

    // Set amperage limits
    serializerMotor.configStatorCurrentLimit(currentLimitConfigurationFeederMotors, 0);

     /**
      * This is where the Talon FX is configured
      * All of the PID values are configured here as well
      * as allowable error and the speed of the motor during the PID
      */

     FXConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
     FXConfig.slot0.kP = kP;
     FXConfig.slot0.kI = kI;
     FXConfig.slot0.kD = kD;
     FXConfig.slot0.kF = kF;
     FXConfig.slot0.allowableClosedloopError = (5);
     FXConfig.peakOutputForward = (0.1);
     serializerMotor.configAllSettings(FXConfig);

  }

  @Override
  public void periodic() {

    if (feederDebug) {
      SmartDashboard.putNumber("CurrentPosisition", getFeederPosition());
      SmartDashboard.putNumber("TargetPosition", targetPosition);
      SmartDashboard.putNumber("Error", getFeederPosition() - targetPosition);
      SmartDashboard.putNumber("Feeder Motor Speed", getFeederSpeed());
      SmartDashboard.putNumber("Feeder Motor Temperature", getFeederTemperature());
    }
  }

  /**
   * A method that sets the speed of the feeder motor
   * @param speed Sets the speed of the motor as a value -1 through 1
   */
  public void setFeederSpeed(double speed) {
    // Sets the speed of the feeder motor
    serializerMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * @return speed
   * Returns the speed of the motor 
   */
  public double getFeederSpeed() {
    double speed = serializerMotor.getMotorOutputPercent();
    return speed;
  }

/**
 * @return position
 * This returns the current position of the motor
 */
  public int getFeederPosition() {
    int position = serializerMotor.getSelectedSensorPosition();
    return position;
  }

  /**
   * Stops the feeder motors.
   */
  public void stopFeeder() {
    serializerMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * @return temp
   * Returns the temperature of the motor 
   */
  public double getFeederTemperature() { 
    return serializerMotor.getTemperature();
  }
  /**
   * @param position
   * This is the amount to shift by
   * targetPosition is the target position of the motor
   * This is found by subtracting the position of the motor by the amount to shift by,
   * creating the target position
   */
    public void positionShift(double position ) {
      targetPosition = getFeederPosition()-position;
    serializerMotor.set(ControlMode.Position, targetPosition);

    }
}