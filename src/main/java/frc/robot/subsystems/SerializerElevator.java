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
  TalonSRX serializerMotor;
  /**
   * Creates a new ExampleSubsystem.
   */
  public SerializerElevator() {
    serializerMotor = new TalonSRX(0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

public void setSerializerSpeed(final double speed) {
  serializerMotor.set(ControlMode.PercentOutput,speed);

  
}

}
