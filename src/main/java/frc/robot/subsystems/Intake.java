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
    SmartDashboard.putNumber("Intake Motor Speed", getIntakeSpeed());
  }

  /**
   * A method that sets the speed of the intake motor(s)
   * @param speed Sets the speed of the motors as a value -1 through 1
   */
  public void setIntakeSpeed(double speed){
    //Sets the speed of the intake motors
    leftIntakeMotor.set(ControlMode.PercentOutput, speed);
    rightIntakeMotor.set(ControlMode.PercentOutput, speed);
  }

  public double getIntakeSpeed(){
    return rightIntakeMotor.getMotorOutputPercent();
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
   */
  public double getIntakeTemperature(){
    return Math.max(leftIntakeMotor.getTemperature(), rightIntakeMotor.getTemperature());
  }
}
