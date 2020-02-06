package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

/**
 * Simple subsystem for the Serializer
 * @author Michael Francis
 */
public class Serializer extends SubsystemBase {
  /**
   * Specifies whether or not the Serializer will be in debug mode.
   * @see #periodic()
   */
  private final boolean serializerDebug = false;

  //Motor
  TalonFX serializerMotor;
  
  //Sets up current limit config variable
  private StatorCurrentLimitConfiguration currentLimitConfigIntake = new StatorCurrentLimitConfiguration();
  
  
  /**
   * Creates a new Serializer subsystem and sets up the motor.
   */
  public Serializer() {
    serializerMotor = new TalonFX(Constants.CONTROLPANELSPINNER);
    serializerMotor.setInverted(false);

    //Sets up current limits on variables
    currentLimitConfigIntake .currentLimit = 50;
    currentLimitConfigIntake .enable = true;
    currentLimitConfigIntake .triggerThresholdCurrent = 40;
    currentLimitConfigIntake .triggerThresholdTime = 3;
    //Pushes current limits to motors
    serializerMotor.configStatorCurrentLimit(currentLimitConfigIntake, 0);
    //Set up ramp rate
    serializerMotor.configClosedloopRamp(0.5);
    //Set up sensor feedback
    serializerMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(serializerDebug){
      //If in debug mode, put the Serializer speed and temperature on SmartDashboard/Shuffleboard
      SmartDashboard.putNumber("Serializer Motor Speed", getSerializerSpeed());
      SmartDashboard.putNumber("Serializer Position", getSerializerPosition());
      SmartDashboard.putNumber("Serializer Motor Temperature", getSerializerTemperature());
    }
  }

  /**
   * A method that sets the speed of the Serializer motor
   * @param speed Sets the speed as a value -1 through 1
   */
  public void setSerializerSpeed(double speed){
    //Sets the speed of the Serializer motor
    serializerMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Sets the serializer to a position
   * @param pos Position to be set to
   */
  public void setSerializerPosition(int pos){
    serializerMotor.setSelectedSensorPosition(pos);
  }

  /**
   * Gets the speed of the Serializer motor.
   * @return The Serializer speed.
   */
  public double getSerializerSpeed(){
    double spd = serializerMotor.getMotorOutputPercent();
    return spd;
  }

  /**
   * Gets the position of the Serializer motor
   * @return The Serializer position
   */
  public int getSerializerPosition(){
    // double spd = serializerEncoder.getPosition();
    int spd = serializerMotor.getSelectedSensorPosition();
    return spd;
  }

  /**
   * A method that stops the Serializer motor.
   */
  public void stopSerializer(){
    serializerMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Method that returns the Serializer motor temperature
   * @return A double of the temperature (in Celsius) of the Serializer motor.
   */
  public double getSerializerTemperature(){
    double temp = serializerMotor.getTemperature();
    return temp;
  }
}
