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
  private boolean intakeDebug = false;

  // Intake motor
  TalonFX intakeMotor;

  // Sets up current limit config variable
  private StatorCurrentLimitConfiguration currentLimitConfigIntake = new StatorCurrentLimitConfiguration();

  // Whether or not the speed has been set, as to not set it every 20ms in periodic
  private boolean speedSet = true;

  // Whether or not any jams are detected
  private boolean jamDetected = false;

  // The number of 20ms iterations that have passed since a jam was detected
  private int i = 0;

  // The number of 20ms iterations that the intake motor has been above the current threshold
  private int jammedIterations = 0;

  /**
   * Creates a new Intake subsystem and sets up the motor.
   */
  public Intake() {
    intakeMotor = new TalonFX(Constants.INTAKE);

    // Reset the motor to its factory settings each boot
    intakeMotor.configFactoryDefault();

    intakeMotor.setInverted(false);

    // Sets up current limits on variables
    currentLimitConfigIntake.currentLimit = 50;
    currentLimitConfigIntake.enable = true;
    currentLimitConfigIntake.triggerThresholdCurrent = 40;
    currentLimitConfigIntake.triggerThresholdTime = 3;
    // Pushes current limits to motors
    intakeMotor.configStatorCurrentLimit(currentLimitConfigIntake, 0);
    // Set up ramp rate
    intakeMotor.configClosedloopRamp(0.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // If we want to use anti-jam code in the first place
    if (Constants.DETECTINTAKEJAMS) {
      // If there is currently no jams detected, then just run the intake normally
      if (!jamDetected) {
        if (!speedSet) {
          setIntakeSpeed(Constants.INTAKEFORWARDSPEED);
          speedSet = true;
        }

        //If the current is above the threshold, make sure it does so for 10 iterations
        if(intakeMotor.getStatorCurrent() > Constants.INTAKECURRENTTOLERENCE){
          jammedIterations++;
        } else {
          // This variable is set every iteration? Does this need a lockout?
          jammedIterations = 0;
        }

        // If the current ever spikes above the threshold for 5 iterations (100ms)
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
          setIntakeSpeed(Constants.INTAKEREVERSESPEED);
          speedSet = true;
        }
        
        // This uses the loop time of 20ms of periodic to track how long the motors are
        // reversed
        if (i > (50 * Constants.INTAKEREVERSALDURATION)) {
          // Once the timeout is finished, jams should be freed.
          // If they're not, this whole block will restart
          jamDetected = false;
          // We need to make the motors go forward in the next iteration
          speedSet = false;
        }
        i++;
      }
    }

    if (intakeDebug) {
      // If in debug mode, put the intake speed and temperature on
      // SmartDashboard/Shuffleboard
      SmartDashboard.putNumber("Intake Motor Speed", getIntakeSpeed());
      SmartDashboard.putBoolean("Jam detected", jamDetected);
      SmartDashboard.putBoolean("speedSet", speedSet);
      SmartDashboard.putNumber("i", i);
      SmartDashboard.putNumber("Intake current", intakeMotor.getStatorCurrent());
      SmartDashboard.putNumber("Jammed iterations", jammedIterations);
      SmartDashboard.putNumber("Intake Motor Temperature", getIntakeTemperature());
    }
  }

  /**
   * A method that sets the speed of the intake motor
   * @param speed Sets the speed as a value -1 through 1
   */
  public void setIntakeSpeed(double speed) {
    // Sets the speed of the intake motor
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Gets the speed of the intake motor.
   * @return The intake speed.
   */
  public double getIntakeSpeed() {
    return intakeMotor.getMotorOutputPercent();
  }

  /**
   * A method that stops the intake motor.
   */
  public void stopIntake() {
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Method that returns the intake motor temperature
   * @return A double of the temperature (in Celsius) of the intake motor.
   */
  public double getIntakeTemperature() {
    return intakeMotor.getTemperature();
  }
}
