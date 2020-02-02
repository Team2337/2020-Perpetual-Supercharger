package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 /** 
 *  Subsystem for the Feeder+Serializer 
 * The Feeder is a mechanism to feed 
 * up to the shooter
 * For more information, see the wiki 
 * @author Nicholas Stokes
 */
public class SerializerFeeder extends SubsystemBase {
  /**
   * Specifies whether or not the Feeder will be in debug mode.
   * 
   * @see #periodic()
   */
  public final boolean feederDebug = true;

  // Motors
  TalonFX serializerMotor;
  public TalonFXConfiguration FXConfig;

  // Current limit configuration
  private StatorCurrentLimitConfiguration currentLimitConfigurationFeederMotors = new StatorCurrentLimitConfiguration();

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
    serializerMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    FXConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

    // Set up the current configuration
    currentLimitConfigurationFeederMotors.currentLimit = 50;
    currentLimitConfigurationFeederMotors.enable = true;
    currentLimitConfigurationFeederMotors.triggerThresholdCurrent = 40;
    currentLimitConfigurationFeederMotors.triggerThresholdTime = 3;

     //Set variables
     final double kP = 0.1;
     final double kI = 0;
     final double kD = 0;
     final double kF = 0;
     //Implement variables into the PIDs
     serializerMotor.config_kP(0, kP);
     serializerMotor.config_kI(0, kI);
     serializerMotor.config_kD(0, kD);
     serializerMotor.config_kF(0, kF);

    // Set amperage limits
    serializerMotor.configStatorCurrentLimit(currentLimitConfigurationFeederMotors, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // If in debug mode, put the feeder speed and temperature on Shuffleboard
    if (feederDebug) {
      SmartDashboard.putNumber("CurrentPosisition", getFeederPosition());
      SmartDashboard.putNumber("TargetPosition", 512);
      SmartDashboard.putNumber("Error", getFeederPosition() - 512);
      SmartDashboard.putNumber("Feeder Motor Speed", getFeederSpeed());
      SmartDashboard.putNumber("Feeder Motor Temperature", getFeederTemperature());
    }
  }

  /**
   * A method that sets the speed of the feeder motor
   * 
   * @param lSpeed Sets the speed of the left motor as a value -1 through 1
   * @param rSpeed Sets the speed of the right motor as a value -1 through 1
   */
  public void setFeederSpeed(double speed) {
    // Sets the speed of the feeder motors
    serializerMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * @return feederMotor.getMotorOutputPercent(); This gets the motor output speed
   *         as a percent/array
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
   */
  public void stopFeeder() {
    serializerMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Method that returns the feeder motors temperature
   */
  public double getFeederTemperature() {
    // Returns an array containing the temperature of the left and right feeder
    // motors in that order.
    double temp =  serializerMotor.getTemperature();
    return temp;
  }
    public void positionShift(double position) {
    serializerMotor.set(ControlMode.Position, position);

    }
}