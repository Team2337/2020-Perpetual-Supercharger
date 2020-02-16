package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

/**
 * Simple subsystem for the agitator
 * @author Michael Francis
 */
public class Agitator extends SubsystemBase {
  /**
   * Specifies whether or not the agitator will be in debug mode.
   * @see #periodic()
   */
  private final boolean agitatorDebug = false;

  //Motor
  TalonFX agitatorMotor;

  //Sets up current limit config variable
  private StatorCurrentLimitConfiguration currentLimitConfigIntake = new StatorCurrentLimitConfiguration();

  /**
   * Creates a new Agitator subsystem and sets up the motor.
   */
  public Agitator() {
    agitatorMotor = new TalonFX(Constants.AGITATOR);

    //Reset the motor to its factory settings each boot
    agitatorMotor.configFactoryDefault();

    agitatorMotor.setInverted(true);

    //Sets up current limits on variables
    currentLimitConfigIntake .currentLimit = 50;
    currentLimitConfigIntake .enable = true;
    currentLimitConfigIntake .triggerThresholdCurrent = 40;
    currentLimitConfigIntake .triggerThresholdTime = 3;
    //Pushes current limits to motors
    agitatorMotor.configStatorCurrentLimit(currentLimitConfigIntake, 0);
    //Set up ramp rate
    agitatorMotor.configClosedloopRamp(0.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(agitatorDebug){
      //If in debug mode, put the agitator speed and temperature on SmartDashboard/Shuffleboard
      SmartDashboard.putNumber("Agitator Motor Speed", getAgitatorSpeed());
    }
      SmartDashboard.putNumber("Agitator Motor Temperature", getAgitatorTemperature());
  }

  /**
   * A method that sets the speed of the agitator motor
   * @param speed Sets the speed as a value -1 through 1
   */
  public void setAgitatorSpeed(double speed){
    //Sets the speed of the agitator motor
    agitatorMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Gets the speed of the agitator motor.
   * @return The intake speed of both the agitator motor in an array.
   */
  public double getAgitatorSpeed(){
    double spd = agitatorMotor.getMotorOutputPercent();
    return spd;
  }

  /**
   * A method that stops the agitator motor.
   */
  public void stopAgitator(){
    agitatorMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Method that returns the agitator motor temperature
   * @return A double of the temperature (in Celsius) of the agitator motor.
   */
  public double getAgitatorTemperature(){
    double temp = agitatorMotor.getTemperature();
    return temp;
  }
}
