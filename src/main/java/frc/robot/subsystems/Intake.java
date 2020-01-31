package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

/**
 * Simple subsystem for the intake
 * @author Michael Francis
 */
public class Intake extends SubsystemBase {
  /**
   * Specifies whether or not the Intake will be in debug mode.
   * @see #periodic()
   */
  private final boolean intakeDebug = false;

  //Motors
  TalonFX leftIntakeMotor;
  TalonFX rightIntakeMotor;

  //Sets up current limit config variable
  private StatorCurrentLimitConfiguration currentLimitConfigIntake = new StatorCurrentLimitConfiguration();

  /**
   * Creates a new Intake subsystem and sets up the motors to their corresponding ports.
   */
  public Intake() {
    /**
     * This sets the motors up. We have two motors: one on the left side and one on the right side.
     * We reverse one of the motors.
     * Then, we configure the current limits on the motors.
     * After that, we set up a closed loop ramp rate.
     */
    leftIntakeMotor = new TalonFX(Constants.LEFTINTAKE);
    rightIntakeMotor = new TalonFX(Constants.RIGHTINTAKE);
    leftIntakeMotor.setInverted(false);
    rightIntakeMotor.setInverted(true);

    //Sets up current limits on variables
    currentLimitConfigIntake .currentLimit = 50;
    currentLimitConfigIntake .enable = true;
    currentLimitConfigIntake .triggerThresholdCurrent = 40;
    currentLimitConfigIntake .triggerThresholdTime = 3;
    //Pushes current limits to motors
    leftIntakeMotor.configStatorCurrentLimit(currentLimitConfigIntake, 0);
    rightIntakeMotor.configStatorCurrentLimit(currentLimitConfigIntake, 0);
    //Set up ramp rate
    leftIntakeMotor.configClosedloopRamp(0.5);
    rightIntakeMotor.configClosedloopRamp(0.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(intakeDebug){
      //If in debug mode, put the intake speed and temperature on SmartDashboard/Shuffleboard
      SmartDashboard.putNumberArray("Intake Motor Speed", getIntakeSpeed());
      SmartDashboard.putNumberArray("Intake Motor Temperature", getIntakeTemperature());
    }
  }

  /**
   * A method that sets the speed of the intake motor(s)
   * @param leftSpeed Sets the speed of the left motor as a value -1 through 1
   * @param rightSpeed Sets the speed of the right motor as a value -1 through 1
   */
  public void setIntakeSpeed(double leftSpeed, double rightSpeed){
    //Sets the speed of the intake motors
    leftIntakeMotor.set(ControlMode.PercentOutput, leftSpeed);
    rightIntakeMotor.set(ControlMode.PercentOutput, rightSpeed);
  }

  /**
   * Sets the speed of the left intake motor
   * @param speed Sets the speed of the left intake motor in percent output
   */
  public void setLeftIntakeSpeed(double speed){
    leftIntakeMotor.set(ControlMode.PercentOutput, speed);
  }
  
  /**
   * Sets the speed of the right intake motor
   * @param speed Sets the speed of the right intake motor in percent output
   */
  public void setRightIntakeSpeed(double speed){
    rightIntakeMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Gets the intake speed of the two intake motors.
   * @return The intake speed of both the left and right motors in an array, put in the respective order.
   */
  public double[] getIntakeSpeed(){
    //Returns an array containing the percent output of the left and right intake motors in that order.
    double[] spd = {leftIntakeMotor.getMotorOutputPercent(), rightIntakeMotor.getMotorOutputPercent()};
    return spd;
  }

  /**
   * A method that stops the intake motors. (both)
   */
  public void stopIntake(){
    leftIntakeMotor.set(ControlMode.PercentOutput, 0);
    rightIntakeMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Method that returns the highest intake motor temperature
   * @return An array of the temperature (in Celsius) of the left and right motors in the respective order.
   */
  public double[] getIntakeTemperature(){
    //Returns an array containing the temperature of the left and right intake motors in that order.
    double[] temp = {leftIntakeMotor.getTemperature(), rightIntakeMotor.getTemperature()};
    return temp;
  }
}
