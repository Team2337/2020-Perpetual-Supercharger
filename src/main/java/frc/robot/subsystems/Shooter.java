/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import com.ctre.phoenix.motorcontrol.can.TalonFXPIDSetConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Shoots the ball with a certain strength
 * 
 * @author Michael Francis
 */
public class Shooter extends SubsystemBase {

  public TalonFX shootMotor1;
  public TalonFX shootMotor2;

  public TalonFXConfiguration FXConfig;

  private StatorCurrentLimitConfiguration currentLimitConfigurationMotor1 = new StatorCurrentLimitConfiguration();
  private StatorCurrentLimitConfiguration currentLimitConfigurationMotor2 = new StatorCurrentLimitConfiguration();

  /**
   * Shoots the ball with a certain strength
   */
  public Shooter() {
    //Creates motors
    shootMotor1 = new TalonFX(0);
    shootMotor2 = new TalonFX(1);

    FXConfig = new TalonFXConfiguration();

    //Configs motors to factory default and configures their sensors
    shootMotor1.configFactoryDefault();
    shootMotor2.configFactoryDefault();
    shootMotor1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    shootMotor2.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    
    FXConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

    // Use current limit for each motor
    currentLimitConfigurationMotor1 .currentLimit = 50;
    currentLimitConfigurationMotor1 .enable = true;
    currentLimitConfigurationMotor1 .triggerThresholdCurrent = 40;
    currentLimitConfigurationMotor1 .triggerThresholdTime = 3;
    currentLimitConfigurationMotor2 .currentLimit = 50;
    currentLimitConfigurationMotor2 .enable = true;
    currentLimitConfigurationMotor2 .triggerThresholdCurrent = 40;
    currentLimitConfigurationMotor2 .triggerThresholdTime = 3;
    // Set amperage limits
    shootMotor1.configStatorCurrentLimit(currentLimitConfigurationMotor1, 0);
    shootMotor2.configStatorCurrentLimit(currentLimitConfigurationMotor2, 0);

    //Configs PIDs
    final double kP = 0.25;
    final double kI = 0;
    shootMotor1.config_kP(0, kP);
    shootMotor1.config_kI(0, kI);
    shootMotor2.config_kP(0, kP);
    shootMotor2.config_kI(0, kI);
    shootMotor1.configClosedloopRamp(0.5);
    shootMotor2.configClosedloopRamp(0.5);

    shootMotor1.enableVoltageCompensation(true);
    shootMotor2.enableVoltageCompensation(true);

    // Configs other information
    shootMotor1.setNeutralMode(NeutralMode.Coast);
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
    SmartDashboard.putBoolean("Is Either Motor Above 70C",
        shootMotor1.getTemperature() > 70 || shootMotor2.getTemperature() > 70);
  }

  public void setShooterSpeed(final double velo) {
    //if (shootMotor1.getTemperature() < 70 && shootMotor2.getTemperature() < 70) {
      shootMotor1.set(ControlMode.Velocity,  velo);//velo * 4096 / 600);
      shootMotor2.set(ControlMode.Velocity,  velo);//velo * 4096 / 600);
    //}
  }

  public void stopShooter() {
    shootMotor1.set(TalonFXControlMode.Velocity, 0);
    shootMotor2.set(TalonFXControlMode.Velocity, 0);
  }
}
