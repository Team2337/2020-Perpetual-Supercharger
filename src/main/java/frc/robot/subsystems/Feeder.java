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
 * Simple system for the feeder
 * @author Nicholas Stokes, Michael Francis, Jack Engleter
 */
public class Feeder extends SubsystemBase {

  // Motors
  TalonFX leftFeederMotor;
  TalonFX rightFeederMotor;

  /**
   * Creates a new Feeder subsystem and sets up the motors to their corresponding
   * ports.
   */
  public Feeder() {
    /**
     * These are the motors that are part of the feeder system
     */
    leftFeederMotor = new TalonFX(Constants.leftFeeder);
    rightFeederMotor = new TalonFX(Constants.rightFeeder);
    leftFeederMotor.setInverted(false);
    rightFeederMotor.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumberArray("Feeder Motor Speed", getFeederSpeed());
  }

  /**
   * A method that sets the speed of the feeder motor
   * 
   * @param speed Sets the speed of the motors as a value -1 through 1
   */
  public void setFeederSpeed(double lSpeed, double rSpeed) {
    // Sets the speed of the feeder motors
    leftFeederMotor.set(ControlMode.PercentOutput, lSpeed);
    rightFeederMotor.set(ControlMode.PercentOutput, rSpeed);
    
  }

/**
 * @return feederMotor.getMotorOutputPercent();
 * This gets the motor output, speed, in a percent
 */



  public double[] getFeederSpeed() {
    double[] speed = {leftFeederMotor.getMotorOutputPercent(), rightFeederMotor.getMotorOutputPercent()};
    return speed;
  }

  /**
   * A method that stops the feeder motor.
   */
  public void stopFeeder() {
    leftFeederMotor.set(ControlMode.PercentOutput, 0);
    rightFeederMotor.set(ControlMode.PercentOutput, 0);
    
  }

  /**
   * Method that returns the feeder motor temperature
   */
  public double [] getFeederTemperature() {
    //Returns an array containing the temperature of the left and right feeder motors in that order.
    double[] temp = {leftFeederMotor.getTemperature(), rightFeederMotor.getTemperature()};
    return temp;
  }
    
}