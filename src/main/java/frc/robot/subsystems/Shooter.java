/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Shoots the ball with a certain strength
 * 
 * @author Michael Francis
 */
public class Shooter extends SubsystemBase {

  TalonFX shootMotor1;
  TalonFX shootMotor2;

  /**
   * Shoots the ball with a certain strength
   */
  public Shooter() {
    shootMotor1 = new TalonFX(0);
    shootMotor2 = new TalonFX(1);
    // shootMotor.config_kP(0, 0.0001);
    // shootMotor.config_kI(0, 0.0001);
    shootMotor1.configOpenloopRamp(1);
    shootMotor1.setNeutralMode(NeutralMode.Coast);
    shootMotor2.configOpenloopRamp(1);
    shootMotor2.setNeutralMode(NeutralMode.Coast);
    shootMotor1.setInverted(true);
    shootMotor2.setInverted(false);
    // shootMotor.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs)
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Shooter Motor 1 Velocity", shootMotor1.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Shooter Motor 2 Velocity", shootMotor2.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Shooter Motor 1 Temperature", shootMotor1.getTemperature());
    SmartDashboard.putNumber("Shooter Motor 2 Temperature", shootMotor2.getTemperature());
    SmartDashboard.putBoolean("Is Either Motor Above 70C", shootMotor1.getTemperature() > 70 || shootMotor2.getTemperature() > 70);
  }

  public void setShooterSpeed(double velo) {
    if (shootMotor1.getTemperature() < 70 && shootMotor2.getTemperature() < 70) {
      shootMotor1.set(ControlMode.PercentOutput, velo);
      shootMotor2.set(ControlMode.PercentOutput, velo);
    }
  }

  public void stopShooter() {
    shootMotor1.set(ControlMode.PercentOutput, 0);
    shootMotor2.set(ControlMode.PercentOutput, 0);
  }
}
