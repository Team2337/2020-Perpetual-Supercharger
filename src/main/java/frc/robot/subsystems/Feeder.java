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
 * @author Nicholas Stokes
 */
public class Feeder extends SubsystemBase {

  // Motors
  TalonFX feederMotor;
  

  /**
   * Creates a new Feeder subsystem and sets up the motors to their corresponding
   * ports.
   */
  public Feeder() {
    /**
     
     */
    feederMotor = new TalonFX(11);

    feederMotor.setInverted(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Feeder Motor Speed", getFeederSpeed());
  }

  /**
   * A method that sets the speed of the feeder motor
   * 
   * @param speed Sets the speed of the motors as a value -1 through 1
   */
  public void setFeederSpeed(double speed) {
    // Sets the speed of the feeder motors
    feederMotor.set(ControlMode.PercentOutput, speed);
    
  }

/**
 * @return feederMotor.getMotorOutputPercent();
 * This gets the motor output, speed, in a percent
 */



  public double getFeederSpeed() {
    return feederMotor.getMotorOutputPercent();
  }

  /**
   * A method that stops the feeder motor.
   */
  public void stopFeeder() {
    feederMotor.set(ControlMode.PercentOutput, 0);
    
  }

  /**
   * Method that returns the feeder motor temperature
   */
  public double getFeederTemperature() {
    return feederMotor.getTemperature();
  }
}
