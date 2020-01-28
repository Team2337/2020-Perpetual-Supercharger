package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Subsystem for the Feeder 
 * The Feeder is a mechanism to feed balls from the intake/hopper to the serializer
 * For more information, see the wiki 
 * @author Nicholas Stokes
 */
public class Feeder extends SubsystemBase {
  /**
   * Specifies whether or not the Feeder will be in debug mode.
   * @see #periodic()
   */
  public final boolean feederDebug = false;

  // Motors
  TalonFX leftFeederMotor;
  TalonFX rightFeederMotor;

  //Current limit configuration
  private StatorCurrentLimitConfiguration currentLimitConfigurationFeederMotors = new StatorCurrentLimitConfiguration();

  /**
   * Creates a new Feeder subsystem and sets up the motors to their corresponding
   * ports.
   */
  public Feeder() {
     // These are the motors, Falcons, and they are set up here. Ports are referenced in the Constants file
    leftFeederMotor = new TalonFX(Constants.leftFeeder);
    leftFeederMotor.setInverted(false);
    leftFeederMotor.configOpenloopRamp(0.2);
    rightFeederMotor = new TalonFX(Constants.rightFeeder);
    rightFeederMotor.setInverted(true);
    rightFeederMotor.configOpenloopRamp(0.2);

    //Set up the current configuration
    currentLimitConfigurationFeederMotors .currentLimit = 50;
    currentLimitConfigurationFeederMotors .enable = true;
    currentLimitConfigurationFeederMotors .triggerThresholdCurrent = 40;
    currentLimitConfigurationFeederMotors .triggerThresholdTime = 3;
    
    // Set amperage limits
    rightFeederMotor.configStatorCurrentLimit(currentLimitConfigurationFeederMotors , 0);
    leftFeederMotor.configStatorCurrentLimit(currentLimitConfigurationFeederMotors , 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //If in debug mode, put the feeder speed and temperature on Shuffleboard
    if(feederDebug){
      SmartDashboard.putNumberArray("Feeder Motor Speed", getFeederSpeed());   
      SmartDashboard.putNumberArray("Feeder Motor Temperature", getFeederTemperature());  
    }
  }

  /**
   * A method that sets the speed of the feeder motor
   * 
   * @param lSpeed Sets the speed of the left motor as a value -1 through 1
   * @param rSpeed Sets the speed of the right motor as a value -1 through 1
   */
  public void setFeederSpeed(double lSpeed, double rSpeed) {
    // Sets the speed of the feeder motors
    leftFeederMotor.set(ControlMode.PercentOutput, lSpeed);
    rightFeederMotor.set(ControlMode.PercentOutput, rSpeed); 
  }

  /**
   * @return feederMotor.getMotorOutputPercent();
   * This gets the motor output speed as a percent/array
   */
  public double[] getFeederSpeed() {
    double[] speed = {leftFeederMotor.getMotorOutputPercent(), rightFeederMotor.getMotorOutputPercent()};
    return speed;
  }

  /**
   * A method that stops the feeder motors.
   */
  public void stopFeeder() {
    leftFeederMotor.set(ControlMode.PercentOutput, 0);
    rightFeederMotor.set(ControlMode.PercentOutput, 0); 
  }

  /**
   * Method that returns the feeder motors temperature
   */
  public double [] getFeederTemperature() {
    //Returns an array containing the temperature of the left and right feeder motors in that order.
    double[] temp = {leftFeederMotor.getTemperature(), rightFeederMotor.getTemperature()};
    return temp;
  }
    
}