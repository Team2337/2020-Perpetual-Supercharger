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
  private StatorCurrentLimitConfiguration currentLimitConfigAgitator = new StatorCurrentLimitConfiguration();

  // Whether or not the speed has been set, as to not set it every 20ms in
  // periodic
  private boolean speedSet = true;

  // Whether or not any jams are detected
  private boolean jamDetected = false;

  // The number of 20ms iterations that have passed since a jam was detected
  private int i = 0;

  // The number of 20ms iterations that the agitator motor has been above the current threshold
  private int jammedIterations = 0;

  /**
   * Creates a new Agitator subsystem and sets up the motor.
   */
  public Agitator() {
    agitatorMotor = new TalonFX(Constants.AGITATOR);

    //Reset the motor to its factory settings each boot
    agitatorMotor.configFactoryDefault();

    agitatorMotor.setInverted(true);

    //Sets up current limits on variables
    currentLimitConfigAgitator .currentLimit = 50;
    currentLimitConfigAgitator .enable = true;
    currentLimitConfigAgitator .triggerThresholdCurrent = 40;
    currentLimitConfigAgitator .triggerThresholdTime = 3;
    //Pushes current limits to motors
    agitatorMotor.configStatorCurrentLimit(currentLimitConfigAgitator, 0);
    //Set up ramp rate
    agitatorMotor.configClosedloopRamp(0.1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // If we want to use anti-jam code in the first place
    if (Constants.DETECTAGITATORJAMS) {
      // If there is currently no jams detected, then just run the agitator normally
      if (!jamDetected) {
        if (!speedSet) {
          setAgitatorSpeed(Constants.AGITATORFORWARDSPEED);
          speedSet = true;
        }

        //If the current is above the threshold, make sure it does so for 10 iterations
        if(agitatorMotor.getStatorCurrent() > Constants.AGITATORCURRENTTOLERENCE){
          jammedIterations++;
        } else {
          // This variable is set every iteration. Does this need a lockout?
          jammedIterations = 0;
        }

        // If the current ever spikes above a threshold for 5 iterations (100ms)
        if (jammedIterations >= 5) {
          // There's now a jam detected, so we flip this boolean and set the counter to 0
          jamDetected = true;
          i = 0;
          // Also, we'll need to re-set the speed
          speedSet = false;
        }
        // This will occur on the next loop through of periodic when a jam is detected
      } else {
        if (!speedSet) {
          // Run the motor in reverse
          setAgitatorSpeed(Constants.AGITATORREVERSESPEED);
          speedSet = true;
        }
        
        // This uses the loop time of 20ms of periodic to track how long the motors are reversed
        if (i > (50 * Constants.AGITATORREVERSALDURATION)) {
          // Once the timeout is finished, jams should be freed.
          // If they're not, this whole block will restart
          jamDetected = false;
          // We need to make the motors go forward in the next iteration
          speedSet = false;
        }
        i++;
      }
    }

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
   * @return The speed of the agitator motor
   */
  public double getAgitatorSpeed(){
    return agitatorMotor.getMotorOutputPercent();
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
