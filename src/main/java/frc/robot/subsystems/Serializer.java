package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

 /** 
 * Subsystem for the Serializer 
 * The Serializer is a mechanism to feed balls up to the shooter
 * For more information, see the wiki 
 * @author Nicholas Stokes
 */
public class Serializer extends SubsystemBase {
  /**
   * Specifies whether or not the Serializer will be in debug mode.
   * @see #periodic()
   */
  public final boolean serializerDebug = false;
  // Sets up posistion stuff (referenced later)
  public double targetPosition;
  final double kP = 0.95;
  final double kI = 0;
  final double kD = 0;
  final double kF = 0;

  //Variables
  final double tolerance = 5;

  // Motors
  private TalonFX serializerMotor;
  public TalonFXConfiguration FXConfig;

  // Current limit configuration
  private StatorCurrentLimitConfiguration currentLimitConfigurationSerializerMotor = new StatorCurrentLimitConfiguration();
  TalonFXConfiguration config = new TalonFXConfiguration();
  
  /** 
 * Subsystem for the Serializer 
 * The Serializer is a mechanism to feed balls up to the shooter
 * For more information, see the wiki 
 * @author Nicholas Stokes
 */
  public Serializer() {
  // The configuration, ramp rate, and inversion are set here as well as the ports.
    serializerMotor = new TalonFX(Constants.SERIALIZER);
    serializerMotor.setInverted(false);
    serializerMotor.configOpenloopRamp(0.2);
    FXConfig = new TalonFXConfiguration();
    
    // Set up the current configuration
    currentLimitConfigurationSerializerMotor.currentLimit = 50;
    currentLimitConfigurationSerializerMotor.enable = true;
    currentLimitConfigurationSerializerMotor.triggerThresholdCurrent = 40;
    currentLimitConfigurationSerializerMotor.triggerThresholdTime = 3;

    // Set amperage limits
    serializerMotor.configStatorCurrentLimit(currentLimitConfigurationSerializerMotor, 0);

     /*
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
     FXConfig.peakOutputForward = (Constants.SERIALIZERSPEED);
     FXConfig.peakOutputReverse = (-Constants.SERIALIZERSPEED);
     serializerMotor.setNeutralMode(NeutralMode.Brake);
     serializerMotor.configAllSettings(FXConfig);
     
  }

  @Override
  public void periodic() {

    if (serializerDebug) {
      SmartDashboard.putNumber("CurrentPosisition", getSerializerPosition());
      SmartDashboard.putNumber("TargetPosition", targetPosition);
      SmartDashboard.putNumber("Error", getSerializerPosition() - targetPosition);
      SmartDashboard.putNumber("Feeder Motor Speed", getSerializerSpeed());
      SmartDashboard.putNumber("Feeder Motor Temperature", getSerializerTemperature());
    }
  }

  /**
   * A method that sets the speed of the serializer motor
   * @param speed Sets the speed of the motor as a value -1 through 1
   * Positive numbers go up
   */
  public void setSerializerSpeed(double speed) {
    // Sets the speed of the serializer motor
    serializerMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * @return speed
   * Returns the speed of the serializer motor 
   */
  public double getSerializerSpeed() {
    double speed = serializerMotor.getMotorOutputPercent();
    return speed;
  }

/**
 * @return position
 * This returns the current position of the serializer motor
 */
  public int getSerializerPosition() {
    int position = serializerMotor.getSelectedSensorPosition();
    return position;
  }

  /**
   * Stops the serializer motors.
   */
  public void stopSerializer() {
    serializerMotor.set(ControlMode.PercentOutput, 0);
  }


  /**
   * @return temp
   * Returns the temperature of the serializer motor 
   */
  public double getSerializerTemperature() { 
    return serializerMotor.getTemperature();
  }
  /**
   * @param position
   * This is the amount to shift by
   * targetPosition is the target position of the motor
   * This is found by subtracting the position of the motor by the amount to shift by,
   * creating the target position
   */
    public void setPosition(double position ) {
      targetPosition = position;
      serializerMotor.set(ControlMode.Position, targetPosition);

    }
    
}
    