package frc.robot.subsystems;

//Imports
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

/**
 * Simple subsystem for the ControlPanelSpinner
 * @author Michael Francis
 */
public class ControlPanelSpinner extends SubsystemBase {
  /**
   * Specifies whether or not the ControlPanelSpinner will be in debug mode.
   * @see #periodic()
   */
  private final boolean controlPanelSpinnerDebug = false;

  //Motor
  CANSparkMax controlPanelSpinnerMotor;
  CANEncoder cpsEncoder;
  CANPIDController cpsPID;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  
  
  /**
   * Creates a new ControlPanelSpinner subsystem and sets up the motor.
   */
  public ControlPanelSpinner() {
    controlPanelSpinnerMotor = new CANSparkMax(Constants.CONTROLPANELSPINNER, MotorType.kBrushless);
    cpsEncoder = controlPanelSpinnerMotor.getEncoder();
    cpsPID = controlPanelSpinnerMotor.getPIDController();
    controlPanelSpinnerMotor.setInverted(false);

    // PID coefficients
    kP = 0.1; 
    kI = 1e-4;
    kD = 1; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;

    // set PID coefficients
    cpsPID.setP(kP);
    cpsPID.setI(kI);
    cpsPID.setD(kD);
    cpsPID.setIZone(kIz);
    cpsPID.setFF(kFF);
    cpsPID.setOutputRange(kMinOutput, kMaxOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(controlPanelSpinnerDebug){
      //If in debug mode, put the ControlPanelSpinner speed and temperature on SmartDashboard/Shuffleboard
      SmartDashboard.putNumber("ControlPanelSpinner Motor Speed", getControlPanelSpinnerSpeed());
      SmartDashboard.putNumber("ControlPanelSpinner Motor Temperature", getControlPanelSpinnerTemperature());
    }
  }

  /**
   * A method that sets the speed of the ControlPanelSpinner motor
   * @param speed Sets the speed as a value -1 through 1
   */
  public void setControlPanelSpinnerSpeed(double speed){
    //Sets the speed of the ControlPanelSpinner motor
    controlPanelSpinnerMotor.set(speed);
  }

  /**
   * Sets the control pannel spinner to a position
   * @param pos Position to be set to
   */
  public void setControlPanelSpinnerPosition(double pos){
    cpsPID.setReference(pos, ControlType.kPosition);
  }

  /**
   * Gets the speed of the ControlPanelSpinner motor.
   * @return The ControlPanelSpinner speed.
   */
  public double getControlPanelSpinnerSpeed(){
    double spd = cpsEncoder.getVelocity();
    return spd;
  }

  /**
   * Gets the position of the ControlPanelSpinner motor
   * @return The ControlPanelSpinner position
   */
  public double getControlPanelSpinnerPosition(){
    double spd = cpsEncoder.getPosition();
    return spd;
  }

  /**
   * A method that stops the ControlPanelSpinner motor.
   */
  public void stopControlPanelSpinner(){
    controlPanelSpinnerMotor.set(0);
  }

  /**
   * Method that returns the ControlPanelSpinner motor temperature
   * @return A double of the temperature (in Celsius) of the ControlPanelSpinner motor.
   */
  public double getControlPanelSpinnerTemperature(){
    double temp = controlPanelSpinnerMotor.getMotorTemperature();
    return temp;
  }
}
