/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

/**
 * Simple system for the intake
 * @author Michael Francis
 */
public class Intake extends SubsystemBase {
  //Debug: if true, will return the intake speed to Shuffleboard/SmartDashboard
  private final boolean debug = false;

  //Motors
  TalonFX leftIntakeMotor;
  TalonFX rightIntakeMotor;

  /**
   * Creates a new Intake subsystem and sets up the motors to their corresponding ports.
   */
  public Intake() {
    /**
     * This sets the motors up. We have two motors: one on the left side and one on the right side.
     */
    leftIntakeMotor = new TalonFX(Constants.leftIntake);
    rightIntakeMotor = new TalonFX(Constants.rightIntake);
    leftIntakeMotor.setInverted(false);
    rightIntakeMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(debug){
      SmartDashboard.putNumberArray("Intake Motor Speed", getIntakeSpeed());
    }
  }

  /**
   * A method that sets the speed of the intake motor(s)
   * @param lspeed Sets the speed of the left motor as a value -1 through 1
   * @param rspeed Sets the speed of the right motor as a value -1 through 1
   */
  public void setIntakeSpeed(double lspeed, double rspeed){
    //Sets the speed of the intake motors
    leftIntakeMotor.set(ControlMode.PercentOutput, lspeed);
    rightIntakeMotor.set(ControlMode.PercentOutput, rspeed);
  }

  /**
   * Gets the intake speed of the two intake motors.
   * @return The intake speed of both the left and right motors in an array, put in the respective order.
   */
  public double[] getIntakeSpeed(){
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
    double[] temp = {leftIntakeMotor.getTemperature(), rightIntakeMotor.getTemperature()};
    return temp;
  }
}
