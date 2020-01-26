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
  /** If true, the shooter is in debug mode, so it will return extra shuffleboard values. */
  public final boolean shooterDebug = true;

  /** The number of balls fired so far. */
  public static int ballsFired = 0;
  /** Whether all balls have been fired in the sequence */
  public static boolean allBallsFired = false;



  ////////////////////////////////
  /* -------------------------- */
  /* ---    MOTOR SET-UP    --- */
  /* -------------------------- */
  ////////////////////////////////

  //Creates motors
  public TalonFX leftShootMotor;
  public TalonFX rightShootMotor;
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
    leftShootMotor = new TalonFX(0);
    rightShootMotor = new TalonFX(1);
    shooterSensor = new DigitalInput(0);
    //Also configures sensors
    FXConfig = new TalonFXConfiguration();

    /** --- CONFIGURE MOTOR AND SENSOR SETTINGS --- **/
    //Configures motors to factory default
    leftShootMotor.configFactoryDefault();
    rightShootMotor.configFactoryDefault();
    //Configures sensors for PID calculations
    leftShootMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    rightShootMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    FXConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

    /** --- SETS UP SETTINGS (Such as current limits) ON MOTORS AND SENSORS --- **/
    //Set up limits
    currentLimitConfigurationMotor .currentLimit = 50;
    currentLimitConfigurationMotor .enable = true;
    currentLimitConfigurationMotor .triggerThresholdCurrent = 40;
    currentLimitConfigurationMotor .triggerThresholdTime = 3;
    //Implements these limits on the motors
    leftShootMotor.configStatorCurrentLimit(currentLimitConfigurationMotor, 0);
    rightShootMotor.configStatorCurrentLimit(currentLimitConfigurationMotor, 0);

    /** --- CONFIGURE PIDS --- **/
    //Set variables
    final double kP = 1.15;
    final double kI = 0;
    final double kD = 0.0002;
    final double kF = 0;
    //Implement variables into the PIDs
    leftShootMotor.config_kP(0, kP);
    leftShootMotor.config_kI(0, kI);
    leftShootMotor.config_kD(0, kD);
    leftShootMotor.config_kF(0, kF);

    rightShootMotor.config_kP(0, kP);
    rightShootMotor.config_kI(0, kI);
    rightShootMotor.config_kD(0, kD);
    rightShootMotor.config_kF(0, kF);
    //Set a closed-loop ramp rate
    leftShootMotor.configClosedloopRamp(0.5);
    rightShootMotor.configClosedloopRamp(0.5);
    //Makes sure the robot recognizes that it needs to use voltage stuff
    leftShootMotor.enableVoltageCompensation(true);
    rightShootMotor.enableVoltageCompensation(true);

    /** --- OTHER MOTOR INFORMATION SET UP --- **/
    //Sets up brakes
    leftShootMotor.setNeutralMode(NeutralMode.Coast);
    rightShootMotor.setNeutralMode(NeutralMode.Coast);
    //Sets up inversions
    leftShootMotor.setInverted(true);
    rightShootMotor.setInverted(false);
    // shootMotor.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs)
  }



  ///////////////////////////////////////////////////
  /* --------------------------------------------- */
  /* --- SMARTDASHBOARD/SHUFFLEBOARD REPORTING --- */
  /* --------------------------------------------- */
  ///////////////////////////////////////////////////

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
    SmartDashboard.putNumber("Shooter Motor 1 Velocity", leftShootMotor.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Shooter Motor 2 Velocity", rightShootMotor.getSelectedSensorVelocity());

    //Puts the temperature of the motors on the dashboard
    SmartDashboard.putNumber("Shooter Motor 1 Temperature", leftShootMotor.getTemperature());
    SmartDashboard.putNumber("Shooter Motor 2 Temperature", rightShootMotor.getTemperature());


    /* --- CHANGING BOOLEAN VARIABLES --- */

    //Variable that returns true when the one of the motors of the shooter are over 70 degrees Celsius
    shooterTemp = leftShootMotor.getTemperature() > 70 || rightShootMotor.getTemperature() > 70;

    //Variable that returns true when the velocity of the motor reaches a set range
    shootInRange = 15100 < leftShootMotor.getSelectedSensorVelocity() && leftShootMotor.getSelectedSensorVelocity() < 16300;


    /* --- SMARTDASHBOARD/SHUFFLEBOARD BOOLEANS --- */

    //This sets up a rectangle on the Smart Dashboard that turns green when shooterTemp returns true (see above)
    SmartDashboard.putBoolean("Is Either Motor Above 70C", shooterTemp);
    SmartDashboard.putBoolean("Shooter In Range?", shootInRange);
    
    SmartDashboard.putBoolean("Shooter Sensor Value", shooterSensor.get());



    /////////////////////////////
    /* ----------------------- */
    /* --- DEBUG MODE CODE --- */
    /* ----------------------- */
    /////////////////////////////

    //Code that runs if debug mode is on
    if(shooterDebug){
      //Increases the variable if the velocity of the shooter is higher than it has been. For testing
      shooterMaxSpeed = Math.max(shooterMaxSpeed, leftShootMotor.getSelectedSensorVelocity());

      if(Math.round(leftShootMotor.getSelectedSensorVelocity()) == 0){
        shooterMaxSpeed = 0;
      }
      
      SmartDashboard.putNumber("Shooter Max Speed", shooterMaxSpeed);
    }
  }



  ///////////////////////////////////
  /* ----------------------------- */
  /* --- SHOOTER FUNCTIONALITY --- */
  /* ----------------------------- */
  ///////////////////////////////////

  /**
   * Sets the shooter motors to run at a certain speed
   * @param velo
   * The <em>velo</em>city at which the motors run at
   */
  public void setShooterSpeed(double velo) {
    //if (shootMotor1.getTemperature() < 70 && shootMotor2.getTemperature() < 70) {
      leftShootMotor.set(ControlMode.Velocity,  velo);//velo * 4096 / 600);
      rightShootMotor.set(ControlMode.Velocity,  velo);//velo * 4096 / 600);
    //}
  }

  /**
   * Shoots a specified number of balls
   * @param velo The velocity at which to set the motors to
   * @param balls The number of balls to shoot
   */
  public void shootBall(double velo, int balls){
    if(ballsFired < balls){
      setShooterSpeed(velo);
    }else{
      allBallsFired = true;
    }
  }



  //////////////////////////////////
  /* ---------------------------- */
  /* --- STOPPING THE SHOOTER --- */
  /* ---------------------------- */
  //////////////////////////////////

  /**
   * Slows the shooter by setting the velocity to 90% speed.
   */
  public void slowShooter() {
    leftShootMotor.set(TalonFXControlMode.Velocity, leftShootMotor.getSelectedSensorVelocity()*0.9);
    rightShootMotor.set(TalonFXControlMode.Velocity, rightShootMotor.getSelectedSensorVelocity()*0.9);
  }

  /**
   * Stops the shooter motors by setting their velocity to 0
   */
  public void stopShooter() {
    leftShootMotor.set(TalonFXControlMode.Velocity, 0);
    rightShootMotor.set(TalonFXControlMode.Velocity, 0);
  }



  ////////////////////////////////////////////////////
  /* ---------------------------------------------- */
  /* --- CALCULATE MOTOR REVOLUTIONS PER MINUTE --- */
  /* ---------------------------------------------- */
  ////////////////////////////////////////////////////

  /**
   * Calculates the revolutions per minute of the left motor using its speed
   * @return The revolutions per minute of the left motor
   */
  public int calculateLeftRPM(){
    // Encoder ticks per 100 ms
    int speed = leftShootMotor.getSelectedSensorVelocity();
    // Encoder ticks per second
    int tps = speed*10;
    // Encoder revolutions per second
    int rps = tps/2048;
    // Convert rps into revolutions per minute
    int rpm = rps*60;
    return rpm;
  }

  /**
   * Calculates the revolutions per minute of the right motor using its speed
   * @return The revolutions per minute of the right motor
   */
  public int calculateRightRPM(){
    // Encoder ticks per 100 ms
    int speed = rightShootMotor.getSelectedSensorVelocity();
    // Encoder ticks per second
    int tps = speed*10;
    // Encoder revolutions per second
    int rps = tps/2048;
    // Convert rps into revolutions per minute
    int rpm = rps*60;
    return rpm;
  }
}
