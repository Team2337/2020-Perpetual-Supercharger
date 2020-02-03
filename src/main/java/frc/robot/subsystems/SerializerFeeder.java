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
  public final boolean feederDebug = true;
  public double targetPosition;

  // Motors
  TalonFX serializerMotor;
  public TalonFXConfiguration FXConfig;

  // Current limit configuration
  private StatorCurrentLimitConfiguration currentLimitConfigurationFeederMotors = new StatorCurrentLimitConfiguration();
TalonFXConfiguration config = new TalonFXConfiguration();
  /**
   * Creates a new Serializer+Feeder subsystem and sets up the motors to their corresponding
   * ports.
   */
  public SerializerFeeder() {
  // These are the motors, Falcons, and they are set up here. Ports are referenced in the Constants file
    serializerMotor = new TalonFX(Constants.SERIALIZING);
    serializerMotor.setInverted(false);
    serializerMotor.configOpenloopRamp(0.2);
    FXConfig = new TalonFXConfiguration();
    
    // Set up the current configuration
    currentLimitConfigurationFeederMotors.currentLimit = 50;
    currentLimitConfigurationFeederMotors.enable = true;
    currentLimitConfigurationFeederMotors.triggerThresholdCurrent = 40;
    currentLimitConfigurationFeederMotors.triggerThresholdTime = 3;

     //Set variables
     final double kP = 0.95;
     final double kI = 0;
     final double kD = 0;
     final double kF = 0;
     //Implement variables into the PIDs

     FXConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
     FXConfig.slot0.kP = kP;
     FXConfig.slot0.kI = kI;
     FXConfig.slot0.kD = kD;
     FXConfig.slot0.kF = kF;
     FXConfig.slot0.allowableClosedloopError = (5);
     FXConfig.peakOutputForward = (0.1);
     serializerMotor.configAllSettings(FXConfig);

    // Set amperage limits
    serializerMotor.configStatorCurrentLimit(currentLimitConfigurationFeederMotors, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // If in debug mode, put the feeder speed and temperature on Shuffleboard as well as 
    //values for the Positional PIDs
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
   * 
   * @param speed Sets the speed of the motor as a value -1 through 1
   * 
   */
  public void setFeederSpeed(double speed) {
    // Sets the speed of the feeder motor
    serializerMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * This returns the speed of the motor as a percent.
   * In Debug mode, this value is put on SmartDashboard
   */
  public double getFeederSpeed() {
    double speed = serializerMotor.getMotorOutputPercent();
    return speed;
  }

  public int getFeederPosition() {
    int position = serializerMotor.getSelectedSensorPosition();
    return position;
  }

  /**
   * A method that stops the feeder motors.
   * 
   */
  public void stopFeeder() {
    serializerMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * This returns the temperature of the motor
   * In Debug mode, this value is put on SmartDashboard
   */
  public double getFeederTemperature() {
    // Returns an array containing the temperature of the left and right feeder
    // motors in that order.
    double temp =  serializerMotor.getTemperature();
    return temp;
  }
  /**
   * 
   * @param position
   * This is the position that the motor is in
   * targetPosition is the target position of the motor
   * This is calculated by what the position is subtracted by what we want the position to be decreased by
   */
    public void positionShift(double position ) {
      targetPosition = getFeederPosition()-position;
    serializerMotor.set(ControlMode.Position, targetPosition);

    }
}