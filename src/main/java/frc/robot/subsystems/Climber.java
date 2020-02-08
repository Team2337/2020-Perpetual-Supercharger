package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

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

  //Motor
  TalonFX climberMotor;

  //Sets up current limit config variable
  private StatorCurrentLimitConfiguration currentLimitConfigclimber = new StatorCurrentLimitConfiguration();

  /**
   * Creates a new climber subsystem and sets up the motor.
   */
  public Climber() {
    climberMotor = new TalonFX(Constants.CLIMBER);
    climberMotor.setInverted(false);

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
}
