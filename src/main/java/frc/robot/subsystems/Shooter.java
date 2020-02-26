package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Shoots the power cells (balls) at a certain speed.
 * @author Michael F, Sean L
 */
public class Shooter extends SubsystemBase {

  /**
   * Specifies whether or not the Shooter will be in debug mode.
   * During debug mode, the SmartDashboard will show troubleshooting values.
   * @see #periodic()
   */
  private final boolean shooterDebug = true;

  public boolean shooterAtVelocity = false;
  private int m_FutureSpeed = Constants.SHOOTSPEEDCLOSE;
  public BooleanSupplier shooterAtVelocityBooleanSupplier = new BooleanSupplier(){
        
    @Override
    public boolean getAsBoolean() {
        // TODO Auto-generated method stub
        return shooterAtVelocity;
    }
};

  private double targetSpeed;

  //////////////////////////
  /* -------------------- */
  /* --- MOTOR SET-UP --- */
  /* -------------------- */
  //////////////////////////

  public TalonFX leftShootMotor;
  public TalonFX rightShootMotor;
  public TalonFXConfiguration FXConfig;
  // Creates a new current limit configuration for putting current limits onto motors
  public StatorCurrentLimitConfiguration currentLimitConfigurationMotor = new StatorCurrentLimitConfiguration();

  /**
   * Shoots the ball with a certain strength
   */
  public Shooter() {
    leftShootMotor = new TalonFX(Constants.SHOOTERLEFTMOTOR);
    rightShootMotor = new TalonFX(Constants.SHOOTERRIGHTMOTOR);

    FXConfig = new TalonFXConfiguration();

    /** --- CONFIGURE MOTOR AND SENSOR SETTINGS --- **/
    // Configures motors to factory default
    leftShootMotor.configFactoryDefault();
    rightShootMotor.configFactoryDefault();
    // Configures sensors for PID calculations
    FXConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

    //Don't allow either motor to run backwards
    leftShootMotor.configNominalOutputReverse(0);
    rightShootMotor.configNominalOutputReverse(0);

    /** --- SETS UP SETTINGS (Such as current limits) ON MOTORS AND SENSORS --- **/
    // Set up current limits
    currentLimitConfigurationMotor.currentLimit = 50; //50
    currentLimitConfigurationMotor.enable = true;
    currentLimitConfigurationMotor.triggerThresholdCurrent = 40;
    currentLimitConfigurationMotor.triggerThresholdTime = 3;
    // Implements these current limits on the motors
    leftShootMotor.configStatorCurrentLimit(currentLimitConfigurationMotor, 0);
    rightShootMotor.configStatorCurrentLimit(currentLimitConfigurationMotor, 0);

    // Set a closed-loop ramp rate on the motors
    leftShootMotor.configClosedloopRamp(0.1);
    rightShootMotor.configClosedloopRamp(0.1);
    // Enable voltage compensation for all control modes on the motors
    leftShootMotor.enableVoltageCompensation(true);
    rightShootMotor.enableVoltageCompensation(true);

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

    /** --- BRAKE MODES AND INVERSIONS --- **/
    // Sets up control mode.
    // Sets it to neutral mode so that the motors do not brake down to 0.
    leftShootMotor.setNeutralMode(NeutralMode.Coast);
    rightShootMotor.setNeutralMode(NeutralMode.Coast);
    // Sets up inversions
    leftShootMotor.setInverted(false);
    rightShootMotor.setInverted(true);
  }

  //////////////////////////////////////
  /* -------------------------------- */
  /* --- SMARTDASHBOARD REPORTING --- */
  /* -------------------------------- */
  //////////////////////////////////////

  /** Boolean set to true when the shooter temperature is over 70 degrees Celsius. */
  public boolean shooterOver70 = false;
  /** A number set to the highest number the speed (velocity) of the shooter has reached. */
  public double shooterMaxSpeed = 0;

  @Override
  public void periodic() {
    if(getAvgRPM() > (getTargetRPM() * 0.95)) {
      shooterAtVelocity = true;
    } else {
      shooterAtVelocity = false;
    }
    shooterAtVelocityBooleanSupplier = new BooleanSupplier(){
      
      @Override
      public boolean getAsBoolean() {
        // TODO Auto-generated method stub
        return shooterAtVelocity;
      }
    };
    
    /////////////////////////////
    /* ----------------------- */
    /* --- DEBUG MODE CODE --- */
    /* ----------------------- */
    /////////////////////////////
    
    if (shooterDebug) {
      // Calculate the max speed.
      // Calculations: Maximum value between the average of left and right motors and itself.
      // This was used during testing for reporting values.
      double lsm = leftShootMotor.getSelectedSensorVelocity();
      double rsm = rightShootMotor.getSelectedSensorVelocity();
      shooterMaxSpeed = Math.max(shooterMaxSpeed, (Math.max(lsm, rsm) + shooterMaxSpeed) / 2);
      // If either motor's velocity is about 0, reset the max speed variable.
      if (Math.round(lsm) == 0 || Math.round(rsm) == 0) {
        shooterMaxSpeed = 0;
      }
      /* --- DASHBOARD VALUES --- */
      // VELOCITY VALUES
      SmartDashboard.putNumber("Left Shooter Velocity", leftShootMotor.getSelectedSensorVelocity());
      SmartDashboard.putNumber("Right Shooter Velocity", rightShootMotor.getSelectedSensorVelocity());
      
      // RPM VALUES
      SmartDashboard.putNumber("Left Motor RPM", getLeftRPM());
      SmartDashboard.putNumber("Right Motor RPM", getRightRPM());
      
      /* --- BOOLEAN VALUES --- */
      /** Sets the value to true if either motor's temperature is over 70 degrees Celsius */
      shooterOver70 = leftShootMotor.getTemperature() > 70 || rightShootMotor.getTemperature() > 70;
      SmartDashboard.putBoolean("Is Either Motor Above 70C", shooterOver70);
      
      
      // Report the max speed variable to SmartDashboard
      SmartDashboard.putNumber("Shooter Max Speed", shooterMaxSpeed);
      SmartDashboard.putNumber("Target speed", targetSpeed);
    }
    SmartDashboard.putBoolean("At target speed", Robot.Utilities.withinTolerance(targetSpeed, getAvgRPM(), 500));
    // TEMPERATURE VALUES
    SmartDashboard.putNumber("Left Shooter Temperature", leftShootMotor.getTemperature());
    SmartDashboard.putNumber("Right Shooter Temperature", rightShootMotor.getTemperature());
    SmartDashboard.putBoolean("Shooter At Velocity", shooterAtVelocity);
  }
  
  ///////////////////////////////////
  /* ----------------------------- */
  /* --- SHOOTER FUNCTIONALITY --- */
  /* ----------------------------- */
  ///////////////////////////////////

  /**
   * Sets the shooter motors to run at a certain speed
   * @param velocity The velocity at which the motors run at
   */
  public void setShooterSpeed(double velocity) {
    targetSpeed = velocity;
    leftShootMotor.set(ControlMode.Velocity, velocity);
    rightShootMotor.set(ControlMode.Velocity, velocity);
  }

  //////////////////////////////////
  /* ---------------------------- */
  /* --- STOPPING THE SHOOTER --- */
  /* ---------------------------- */
  //////////////////////////////////

  /**
   * Stops the shooter motors by setting their power output to 0.
   * <p>We do this instead of setting the velocity because otherwise the motors will try to
   * get to 0 RPM as quickly as possible.
   * If it does this, you will hear a very horrible noise as the motors damage themselves
   * by skipping the belt on the pinion.
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
  public int getLeftRPM() {
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
  public int getRightRPM() {
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

  public int getTargetRPM() {
    // Encoder ticks per 100 ms
    int speed = (int) getTargetSpeed();
    // Encoder ticks per second
    int tps = speed * 10;
    // Encoder revolutions per second
    int rps = tps / 2048;
    // Convert rps into revolutions per minute
    int rpm = rps * 60;
    return rpm;
  }

  /**
   * Gets the average rpm of the shooter
   * @return The average rpm of both shooter motors
   */
  public double getAvgRPM() {
    return (Math.abs(getRightRPM()) + Math.abs(getLeftRPM())) / 2;
  }

   /**
   * Gets the target speed of the shooter
   * @return - The target speed of the shooter
   */
  public double getTargetSpeed() {
    return targetSpeed;
  }

  /**
   * Gets the future speed of the shooter
   * @return - The future speed of the shooter
   */
  public int getFutureSpeed() {
    return m_FutureSpeed;
  }
  
  /**
   * Sets the future speed of the shooter
   * @param futureSpeed - The future speed of the shooter
   */
  public void setFutureSpeed(int futureSpeed) {
    m_FutureSpeed = futureSpeed;
  }
}
