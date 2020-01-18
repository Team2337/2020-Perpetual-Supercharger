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
   * Method that returns the temperature of the intake motor requested.
   * @param mtr
   * An integer value that determines what temperature the method returns.
   * <ul><li>1 = leftIntakeMotor</li>
   * <li>2 = rightIntakeMotor</li>
   * <li>If any other value is entered it will return a 0</li></ul>
   * @return
   * Returns a double value equal to the temperature of the motor determined 
   * in the parameter <em>mtr</em>.
   */
  public double getIntakeTemperature(int mtr){
    /**
     * The if statement is determined by the integer received.
     * The number received determines what intake motor it checks.
     * If it is any other number, it will return 0.
     */
    if(mtr == 1){
      //If the given variable is 1, return leftIntakeMotor's temperature.
      return leftIntakeMotor.getTemperature();
    }else if(mtr == 2){
      //If the given variable is 2, return rightIntakeMotor's temperature.
      return rightIntakeMotor.getTemperature();
    }else{
      //If the given variable is anything but 1 or 2, return 0.
      return 0;
    }
  }
}
