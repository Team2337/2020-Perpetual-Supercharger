package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.commands.Climber.runClimberJoystick;

/**
 * Simple subsystem for the climber
 * @author Michael Francis
 */
public class Climber extends SubsystemBase {
  /**
   * Specifies whether or not the climber will be in debug mode.
   * @see #periodic()
   */
  private final boolean climberDebug = false;

  private boolean isActivated = true;

  //Motor
  private TalonFX climberMotor;

  private TalonFXConfiguration TalonFXConfigurationClimber;

  //Sets up current limit config variable
  private StatorCurrentLimitConfiguration currentLimitConfigclimber = new StatorCurrentLimitConfiguration();

  /**
   * Creates a new climber subsystem and sets up the motor.
   */
  public Climber() {
    setDefaultCommand(new runClimberJoystick(this));

    climberMotor = new TalonFX(Constants.CLIMBER);
    TalonFXConfigurationClimber = new TalonFXConfiguration();

    climberMotor.configFactoryDefault();

    climberMotor.setInverted(false);
  
    TalonFXConfigurationClimber.slot0.kP = 0.01;
    TalonFXConfigurationClimber.slot0.kI = 0;
    TalonFXConfigurationClimber.slot0.kD = 0;
    TalonFXConfigurationClimber.slot0.kF = 0;
    TalonFXConfigurationClimber.slot0.allowableClosedloopError = 100;
    TalonFXConfigurationClimber.peakOutputForward = 0.9;
    TalonFXConfigurationClimber.peakOutputReverse = -0.9;
    TalonFXConfigurationClimber.reverseSoftLimitEnable = true;
    TalonFXConfigurationClimber.forwardSoftLimitEnable = true;
    TalonFXConfigurationClimber.reverseSoftLimitThreshold = 0;
    TalonFXConfigurationClimber.forwardSoftLimitThreshold = 100000;
    TalonFXConfigurationClimber.initializationStrategy = SensorInitializationStrategy.BootToZero;

    climberMotor.configAllSettings(TalonFXConfigurationClimber);

    //Sets up current limits on variables
    currentLimitConfigclimber .currentLimit = 50;
    currentLimitConfigclimber .enable = true;
    currentLimitConfigclimber .triggerThresholdCurrent = 40;
    currentLimitConfigclimber .triggerThresholdTime = 3;
    //Pushes current limits to motors
    climberMotor.configStatorCurrentLimit(currentLimitConfigclimber, 0);
    //Set up ramp rate
    climberMotor.configClosedloopRamp(0.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(climberDebug){
      //If in debug mode, put the climber speed and temperature on SmartDashboard/Shuffleboard
      SmartDashboard.putNumber("Climber Motor Speed", getClimberSpeed());
      SmartDashboard.putNumber("Climber Motor Temperature", getClimberTemperature());
    }
  }

  /**
   * A method that sets the speed of the climber motor
   * @param speed Sets the speed as a value -1 through 1
   */
  public void setClimberSpeed(double speed){
    //Sets the speed of the climber motor
    climberMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Gets the speed of the climber motor.
   * @return The climber speed.
   */
  public double getClimberSpeed(){
    double spd = climberMotor.getMotorOutputPercent();
    return spd;
  }

  /**
   * A method that stops the climber motor.
   */
  public void stopClimber(){
    climberMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Method that returns the climber motor temperature
   * @return A double of the temperature (in Celsius) of the climber motor.
   */
  public double getClimberTemperature(){
    double temp = climberMotor.getTemperature();
    return temp;
  }

  /**
   * Sets climber activated
   * @param isActivated - Sets the climber activated to run the climber if true
   */
  public void setClimberActivated(boolean isActivated) {
    this.isActivated = isActivated;
  }

  /**
   * Gets the climber activated
   * @return - The climber activated
   */
  public boolean getClimberActivated() {
    return isActivated;
  }

  /**
   * Sets the climber setpoint
   * @param setpoint - The setpoint for the climber
   */
  public void setSetpoint(int setpoint) {
    climberMotor.set(ControlMode.Position, setpoint);
  }

  /**
   * Gets the current position of the climber
   * @return - The current position of the climber
   */
  public int getCurrentPosition() {
    return climberMotor.getSelectedSensorPosition();
  }
}
