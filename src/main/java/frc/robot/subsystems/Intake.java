package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

/**
 * Simple subsystem for the intake
 * @author Michael Francis
 */
public class Intake extends SubsystemBase {
  /**
   * Specifies whether or not the Intake will be in debug mode.
   * @see #periodic()
   */
  private final boolean intakeDebug = false;

  //Motor
  TalonFX intakeMotor;

  //Sets up current limit config variable
  private StatorCurrentLimitConfiguration currentLimitConfigIntake = new StatorCurrentLimitConfiguration();

  /**
   * Creates a new Intake subsystem and sets up the motor.
   */
  public Intake() {
    intakeMotor = new TalonFX(Constants.INTAKE);

    //Reset the motor to its factory settings each boot
    intakeMotor.configFactoryDefault();

    intakeMotor.setInverted(false);

    //Sets up current limits on variables
    currentLimitConfigIntake .currentLimit = 50;
    currentLimitConfigIntake .enable = true;
    currentLimitConfigIntake .triggerThresholdCurrent = 40;
    currentLimitConfigIntake .triggerThresholdTime = 3;
    //Pushes current limits to motors
    intakeMotor.configStatorCurrentLimit(currentLimitConfigIntake, 0);
    //Set up ramp rate
    intakeMotor.configClosedloopRamp(0.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(intakeDebug){
      //If in debug mode, put the intake speed and temperature on SmartDashboard/Shuffleboard
      SmartDashboard.putNumber("Intake Motor Speed", getIntakeSpeed());
    }
      SmartDashboard.putNumber("Intake Motor Temperature", getIntakeTemperature());
  }

  /**
   * A method that sets the speed of the intake motor
   * @param speed Sets the speed as a value -1 through 1
   */
  public void setIntakeSpeed(double speed){
    //Sets the speed of the intake motor
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Gets the speed of the intake motor.
   * @return The intake speed.
   */
  public double getIntakeSpeed(){
    double spd = intakeMotor.getMotorOutputPercent();
    return spd;
  }

  /**
   * A method that stops the intake motor.
   */
  public void stopIntake(){
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Method that returns the intake motor temperature
   * @return A double of the temperature (in Celsius) of the intake motor.
   */
  public double getIntakeTemperature(){
    double temp = intakeMotor.getTemperature();
    return temp;
  }
}
