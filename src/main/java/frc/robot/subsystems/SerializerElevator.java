/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SerializerElevator extends SubsystemBase {
  public TalonSRX SerializerMotor;

  /**
   *  @author Hunter B. & John R. 
   **/
  public SerializerElevator() {
    //This is the port of the motors for the serializer
    SerializerMotor = new TalonSRX(2);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
//Sets the serializer speed for the motors
  public void setSerializerSpeed(double speed) {
    SerializerMotor.set(ControlMode.PercentOutput, speed);
  }

}
