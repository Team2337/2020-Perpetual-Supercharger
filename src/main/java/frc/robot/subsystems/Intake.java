/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Simple system for the intake
 * @author Michael Francis
 */
public class Intake extends SubsystemBase {

  //Motors
  TalonSRX leftIntakeMotor;
  TalonSRX rightIntakeMotor;

  /**
   * Creates a new Intake subsystem and sets up the motors to their corresponding ports.
   */
  public Intake() {
    /**
     * Currently, this sets the motors up. We have:
     *   • leftIntakeMotor (9)
     *   • rightIntakeMotor (10)
     */
    leftIntakeMotor = new TalonSRX(9);
    rightIntakeMotor = new TalonSRX(10);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Motor Speed", getIntakeSpeed());
  }

  /**
   * A method that sets the speed of the intake motor(s)
   * @param speed
   * Sets the speed of the motors as a value -1 through 1
   */
  public void setIntakeSpeed(double speed){
    //Sets the speed of the intake motors
    leftIntakeMotor.set(ControlMode.PercentOutput, speed);
    rightIntakeMotor.set(ControlMode.PercentOutput, speed);
  }

  int x = 0;
  public double getIntakeSpeed(){
    /**if(x > 4){
      
      System.out.println("left: "+leftIntakeMotor.getMotorOutputPercent());
      System.out.println("right: "+rightIntakeMotor.getMotorOutputPercent());
      System.out.println("----------------");
      x = 0;
    }
    x++;*/
    return rightIntakeMotor.getMotorOutputPercent();
  }

  /**
   * A method that stops the intake motors. (both)
   * @author Michael Francis
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
