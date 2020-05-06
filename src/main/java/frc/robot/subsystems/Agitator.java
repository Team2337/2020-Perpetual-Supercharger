package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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
  public VictorSPX agitatorMotor;

  /**
   * Creates a new Agitator subsystem and sets up the motor.
   */
  public Agitator() {
    agitatorMotor = new VictorSPX(Constants.AGITATOR);
    
    //Reset the motor to its factory settings each boot
    agitatorMotor.configFactoryDefault();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(agitatorDebug){
      //If in debug mode, put the agitator speed and temperature on SmartDashboard/Shuffleboard
    }
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
  public double getAgitatorSpeed() {
    return agitatorMotor.getMotorOutputPercent();
  }

  /**
   * A method that stops the agitator motor.
   */
  public void stopAgitator(){
    agitatorMotor.set(ControlMode.PercentOutput, 0);
  }
}
