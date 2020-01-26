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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Shoots the ball with a certain strength
 * 
 * @author Michael Francis
 */
public class Shooter extends SubsystemBase {

  //Put true for debug mode
  public final boolean shooterDebug = true;

  //Creates motors
  public TalonFX shootMotor1;
  public TalonFX shootMotor2;
  public DigitalInput shooterSensor;
  //Configures sensors
  public TalonFXConfiguration FXConfig;
  //Configures code for putting limits onto motors
  private StatorCurrentLimitConfiguration currentLimitConfigurationMotor = new StatorCurrentLimitConfiguration();
  //Creates a variable for shooting
  public int ballCount = 0;

  /**
   * Shoots the ball with a certain strength
   */
  public Shooter() {
    //Also creates motors
    
    shootMotor1 = new TalonFX(0);
    shootMotor2 = new TalonFX(1);
    shooterSensor = new DigitalInput(0);
    //Also configures sensors
    FXConfig = new TalonFXConfiguration();

    /** --- CONFIGURE MOTOR AND SENSOR SETTINGS --- **/
    //Configures motors to factory default
    shootMotor1.configFactoryDefault();
    shootMotor2.configFactoryDefault();
    //Configures sensors for PID calculations
    shootMotor1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    shootMotor2.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    FXConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

    /** --- SETS UP SETTINGS (Such as current limits) ON MOTORS AND SENSORS --- **/
    //Set up limits
    currentLimitConfigurationMotor .currentLimit = 50;
    currentLimitConfigurationMotor .enable = true;
    currentLimitConfigurationMotor .triggerThresholdCurrent = 40;
    currentLimitConfigurationMotor .triggerThresholdTime = 3;
    //Implements these limits on the motors
    shootMotor1.configStatorCurrentLimit(currentLimitConfigurationMotor, 0);
    shootMotor2.configStatorCurrentLimit(currentLimitConfigurationMotor, 0);

    /** --- CONFIGURE PIDS --- **/
    //Set variables
    final double kP = 1.15;
    final double kI = 0;
    final double kD = 0.0002;
    final double kF = 0;
    //Implement variables into the PIDs
    shootMotor1.config_kP(0, kP);
    shootMotor1.config_kI(0, kI);
    shootMotor1.config_kD(0, kD);
    shootMotor1.config_kF(0, kF);

    shootMotor2.config_kP(0, kP);
    shootMotor2.config_kI(0, kI);
    shootMotor2.config_kD(0, kD);
    shootMotor2.config_kF(0, kF);
    //Set a closed-loop ramp rate
    shootMotor1.configClosedloopRamp(0.5);
    shootMotor2.configClosedloopRamp(0.5);
    //Makes sure the robot recognizes that it needs to use voltage stuff
    shootMotor1.enableVoltageCompensation(true);
    shootMotor2.enableVoltageCompensation(true);

    /** --- OTHER MOTOR INFORMATION SET UP --- **/
    //Sets up brakes
    shootMotor1.setNeutralMode(NeutralMode.Coast);
    shootMotor2.setNeutralMode(NeutralMode.Coast);
    //Sets up inversions
    shootMotor1.setInverted(true);
    shootMotor2.setInverted(false);
    // shootMotor.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs)
  }

  /** Boolean that returns true when the shooter temperature is over 70 degrees Celsius. */
  public boolean shooterTemp;
  /** Boolean that returns true when the shooter speed is within a certain range. */
  public boolean shootInRange;
  /** A number that returns the highest number the speed of the shooter has reached. */
  public double shooterMaxSpeed = 0;
  /**
   * The periodic in the Shooter subsystem is mainly for putting useful information on the dashboard to be read.
   */
  @Override
  public void periodic() {

    /* --- SMARTDASHBOARD/SHUFFLEBOARD NUMBERS --- */

    //Puts the velocity of the motors on the dashboard
    SmartDashboard.putNumber("Shooter Motor 1 Velocity", shootMotor1.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Shooter Motor 2 Velocity", shootMotor2.getSelectedSensorVelocity());

    //Puts the temperature of the motors on the dashboard
    SmartDashboard.putNumber("Shooter Motor 1 Temperature", shootMotor1.getTemperature());
    SmartDashboard.putNumber("Shooter Motor 2 Temperature", shootMotor2.getTemperature());


    /* --- CHANGING BOOLEAN VARIABLES --- */

    //Variable that returns true when the one of the motors of the shooter are over 70 degrees Celsius
    shooterTemp = shootMotor1.getTemperature() > 70 || shootMotor2.getTemperature() > 70;

    //Variable that returns true when the velocity of the motor reaches a set range
    shootInRange = 16100 < shootMotor1.getSelectedSensorVelocity() && shootMotor1.getSelectedSensorVelocity() < 16300;


    /* --- SMARTDASHBOARD/SHUFFLEBOARD BOOLEANS --- */

    //This sets up a rectangle on the Smart Dashboard that turns green when shooterTemp returns true (see above)
    SmartDashboard.putBoolean("Is Either Motor Above 70C", shooterTemp);
    SmartDashboard.putBoolean("Shooter In Range?", shootInRange);
    
    SmartDashboard.putBoolean("Shooter Sensor Value", shooterSensor.get());


    /* --- DEBUG MODE CODE --- */

    //Code that runs if debug mode is on
    if(shooterDebug){
      //Increases the variable if the velocity of the shooter is higher than it has been. For testing
      shooterMaxSpeed = Math.max(shooterMaxSpeed, shootMotor1.getSelectedSensorVelocity());

      if(Math.round(shootMotor1.getSelectedSensorVelocity()) == 0){
        shooterMaxSpeed = 0;
      }
      
      SmartDashboard.putNumber("Shooter Max Speed", shooterMaxSpeed);
    }
  }

  /**
   * Sets the shooter motors to run at a certain speed
   * @param velo
   * The <em>velo</em>city at which the motors run at
   */
  public void setShooterSpeed(double velo) {
    //if (shootMotor1.getTemperature() < 70 && shootMotor2.getTemperature() < 70) {
      shootMotor1.set(ControlMode.Velocity,  velo);//velo * 4096 / 600);
      shootMotor2.set(ControlMode.Velocity,  velo);//velo * 4096 / 600);
    //}
  }

  /**
   * Stops the shooter motors by setting their velocity to 0
   */
  public void stopShooter() {
    shootMotor1.set(TalonFXControlMode.Velocity, 0);
    shootMotor2.set(TalonFXControlMode.Velocity, 0);
  }

  /**
   * Shoots a specified number of balls
   */
  public void shootBall(double velo, int balls){

  }
}
