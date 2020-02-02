package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Shoots the ball with a certain strength
 * 
 * @author Michael F, Sean L
 */
public class Shooter extends SubsystemBase {

  // Shooter debug (adds in more values to SmartDashboard)
  private final boolean shooterDebug = false;

  //////////////////////////
  /* -------------------- */
  /* --- MOTOR SET-UP --- */
  /* -------------------- */
  //////////////////////////

  // Creates motors
  public TalonFX leftShootMotor;
  public TalonFX rightShootMotor;
  // Configures sensors
  public TalonFXConfiguration FXConfig;
  // Configures code for putting limits onto motors
  private StatorCurrentLimitConfiguration currentLimitConfigurationMotor = new StatorCurrentLimitConfiguration();

  /**
   * Shoots the ball with a certain strength
   */
  public Shooter() {
    // Also creates motors
    leftShootMotor = new TalonFX(Constants.SHOOTERLEFTMOTOR);
    rightShootMotor = new TalonFX(Constants.SHOOTERRIGHTMOTOR);

    // Also configures sensors
    FXConfig = new TalonFXConfiguration();

    /** --- CONFIGURE MOTOR AND SENSOR SETTINGS --- **/
    // Configures motors to factory default
    leftShootMotor.configFactoryDefault();
    rightShootMotor.configFactoryDefault();
    // Configures sensors for PID calculations
    leftShootMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    rightShootMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    FXConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

    /** --- SETS UP SETTINGS (Such as current limits) ON MOTORS AND SENSORS --- **/
    // Set up limits
    currentLimitConfigurationMotor.currentLimit = 50;
    currentLimitConfigurationMotor.enable = true;
    currentLimitConfigurationMotor.triggerThresholdCurrent = 40;
    currentLimitConfigurationMotor.triggerThresholdTime = 3;
    // Implements these limits on the motors
    leftShootMotor.configStatorCurrentLimit(currentLimitConfigurationMotor, 0);
    rightShootMotor.configStatorCurrentLimit(currentLimitConfigurationMotor, 0);

    /** --- CONFIGURE PIDS --- **/
    // Set variables
    final double kP = 1.15;
    final double kI = 0;
    final double kD = 0.0002;
    final double kF = 0;
    // Implement variables into the PIDs
    leftShootMotor.config_kP(0, kP);
    leftShootMotor.config_kI(0, kI);
    leftShootMotor.config_kD(0, kD);
    leftShootMotor.config_kF(0, kF);

    rightShootMotor.config_kP(0, kP);
    rightShootMotor.config_kI(0, kI);
    rightShootMotor.config_kD(0, kD);
    rightShootMotor.config_kF(0, kF);
    // Set a closed-loop ramp rate
    leftShootMotor.configClosedloopRamp(0.5);
    rightShootMotor.configClosedloopRamp(0.5);
    // Makes sure the robot recognizes that it needs to use voltage stuff
    leftShootMotor.enableVoltageCompensation(true);
    rightShootMotor.enableVoltageCompensation(true);

    /** --- OTHER MOTOR INFORMATION SET UP --- **/
    // Sets up brakes
    leftShootMotor.setNeutralMode(NeutralMode.Coast);
    rightShootMotor.setNeutralMode(NeutralMode.Coast);
    // Sets up inversions
    leftShootMotor.setInverted(true);
    rightShootMotor.setInverted(false);
  }

  ///////////////////////////////////////////////////
  /* --------------------------------------------- */
  /* --- SMARTDASHBOARD/SHUFFLEBOARD REPORTING --- */
  /* --------------------------------------------- */
  ///////////////////////////////////////////////////

  /**
   * Boolean that returns true when the shooter temperature is over 70 degrees
   * Celsius.
   */
  public boolean shooterTemp;
  /**
   * A number that returns the highest number the speed of the shooter has
   * reached.
   */
  public double shooterMaxSpeed = 0;

  @Override
  public void periodic() {

    /* --- DASHBOARD VALUES --- */
    // VELOCITY VALUES
    SmartDashboard.putNumber("Left Shooter Velocity", leftShootMotor.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Right Shooter Velocity", rightShootMotor.getSelectedSensorVelocity());
    // TEMPERATURE VALUES
    SmartDashboard.putNumber("Left Shooter Temperature", leftShootMotor.getTemperature());
    SmartDashboard.putNumber("Right Shooter Temperature", rightShootMotor.getTemperature());
    // RPM VALUES
    SmartDashboard.putNumber("Left Motor RPM", calculateLeftRPM());
    SmartDashboard.putNumber("Right Motor RPM", calculateRightRPM());

    /* --- BOOLEAN VALUES --- */
    // Variable that returns true when the one of the motors of the shooter are over
    // 70 degrees Celsius
    shooterTemp = leftShootMotor.getTemperature() > 70 || rightShootMotor.getTemperature() > 70;
    SmartDashboard.putBoolean("Is Either Motor Above 70C", shooterTemp);

    /////////////////////////////
    /* ----------------------- */
    /* --- DEBUG MODE CODE --- */
    /* ----------------------- */
    /////////////////////////////

    if (shooterDebug) {
      // Calculate the max speed
      shooterMaxSpeed = Math.max(shooterMaxSpeed,
          (Math.max(leftShootMotor.getSelectedSensorVelocity(), rightShootMotor.getSelectedSensorVelocity())
              + shooterMaxSpeed) / 2);
      if (Math.round(leftShootMotor.getSelectedSensorVelocity()) == 0
          || Math.round(rightShootMotor.getSelectedSensorVelocity()) == 0) {
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
   * 
   * @param velo The <em>velo</em>city at which the motors run at
   */
  public void setShooterSpeed(double velo) {
    leftShootMotor.set(ControlMode.Velocity, velo);
    rightShootMotor.set(ControlMode.Velocity, velo);
  }

  //////////////////////////////////
  /* ---------------------------- */
  /* --- STOPPING THE SHOOTER --- */
  /* ---------------------------- */
  //////////////////////////////////

  /**
   * Stops the shooter motors by setting their velocity to 0
   */
  public void stopShooter() {
    leftShootMotor.set(TalonFXControlMode.PercentOutput, 0);
    rightShootMotor.set(TalonFXControlMode.PercentOutput, 0);
  }

  ////////////////////////////////////////////////////
  /* ---------------------------------------------- */
  /* --- CALCULATE MOTOR REVOLUTIONS PER MINUTE --- */
  /* ---------------------------------------------- */
  ////////////////////////////////////////////////////

  /**
   * Calculates the revolutions per minute of the left motor using its speed
   * 
   * @return The revolutions per minute of the left motor
   */
  public int calculateLeftRPM() {
    // Encoder ticks per 100 ms
    int speed = leftShootMotor.getSelectedSensorVelocity();
    // Encoder ticks per second
    int tps = speed * 10;
    // Encoder revolutions per second
    int rps = tps / 2048;
    // Convert rps into revolutions per minute
    int rpm = rps * 60;
    return rpm;
  }

  /**
   * Calculates the revolutions per minute of the right motor using its speed
   * 
   * @return The revolutions per minute of the right motor
   */
  public int calculateRightRPM() {
    // Encoder ticks per 100 ms
    int speed = rightShootMotor.getSelectedSensorVelocity();
    // Encoder ticks per second
    int tps = speed * 10;
    // Encoder revolutions per second
    int rps = tps / 2048;
    // Convert rps into revolutions per minute
    int rpm = rps * 60;
    return rpm;
  }
}
