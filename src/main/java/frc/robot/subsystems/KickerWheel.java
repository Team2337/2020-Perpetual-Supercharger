package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


/**
 * This subsystem makes the Kicker wheel in the robot. The kicker wheel is the motor that speeds up
 * the ball when it enters the shooter mechanism.
 * 
 * <p>Methods include go faster, stop, or decrease in speed
 * @author Hunter B, John R
 */
public class KickerWheel extends SubsystemBase {
  /**
   * Specifies whether or not the Feeder will be in debug mode.
   * @see #periodic()
   */
  private boolean kickerWheelDebug = false;

  /** The speed the motors are currently set to. Changed in methods. */
  public double targetSpeed;
  /** Kicker wheel motor */
  public CANSparkMax kickerWheelMotor;

  public CANEncoder neoEncoder;
  /** PID controller of the Kicker wheel motor */
  private CANPIDController kickerPIDController;

  private int futureSpeed = Constants.KICKERSPEEDCLOSE;

  /* --- PID SETTINGS --- */
  double velocityP = 0.0000;
  double velocityI = 0;
  double velocityD = 0;
  double velocityFF = 0.000043; //0.000059
  double kMinOutput = -1;
  double kMaxOutput = 1;
  double positionalP = 0.9;

  // If the driver is currently controlling the kicker wheel, lock out the operators control of it
  public boolean driverIsControlling = false;

  // If the driver is currently controlling the kicker wheel, lock out the operators control of it
  public boolean operatorIsControlling = false;
   
  /**
   * Creates a new Kicker subsystem and sets up the motors to their corresponding ports.
   */
  public KickerWheel() {
    // Sets up the motor (NEO 550) using the number specified in the Constants file.
    kickerWheelMotor = new CANSparkMax(Constants.KICKER, MotorType.kBrushless);
    neoEncoder = (kickerWheelMotor.getEncoder());

    kickerWheelMotor.restoreFactoryDefaults();

    kickerWheelMotor.setInverted(true);
    
    // Sets up the PID controller
    kickerPIDController = kickerWheelMotor.getPIDController();
    
    // Sets up the PIDs
    kickerPIDController.setP(velocityP);
    kickerPIDController.setI(velocityI);
    kickerPIDController.setD(velocityD);
    kickerPIDController.setFF(velocityFF);
    kickerPIDController.setOutputRange(kMinOutput, kMaxOutput);

    kickerWheelMotor.setSmartCurrentLimit(50); //Placeholder value
    
    kickerWheelMotor.setClosedLoopRampRate(0.0);
    
  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Debug mode: if on, put numbers on the SmartDashboard
    if(kickerWheelDebug){
      SmartDashboard.putNumber("Kicker wheel target", targetSpeed);
      SmartDashboard.putNumber("Kicker wheel percent output", kickerWheelMotor.getOutputCurrent());
      SmartDashboard.putNumber("Kicker Temperature", getKickerTemperature());
    }
    SmartDashboard.putNumber("Kicker wheel velocity", getKickerSpeed());
  }

  /**
   * Adjusts the speed of the kicker wheel by a certain amount.
   * @param speedChange The amount that the speed should increase or decrease by.
   */
  public void adjustKickerSpeed(double speedChange){
    targetSpeed = targetSpeed + speedChange;
    kickerPIDController.setReference(targetSpeed, ControlType.kVelocity);
  }

  /**
   * Stops the kicker wheel.
   */
  public void stopKicker(){
    targetSpeed = 0;
    kickerWheelMotor.set(targetSpeed);
  }

  /**
   * Sets the speed of the kicker wheel.
   * @param speed The speed to set the kicker wheel to (in velocity)
   */
  public void setKickerSpeed(double speed, double kP) {
    targetSpeed = speed;
    kickerPIDController.setP(kP);
    kickerPIDController.setReference(speed, ControlType.kVelocity);
  }

  /**
   * Sets the Kicker position to a certain position
   * @param pos The position to set the position to
   */
  public void setKickerPosition(double pos){
    kickerPIDController.setP(positionalP);
    kickerPIDController.setReference(pos, ControlType.kPosition);
  }

  /**
   * Gets the kicker speed and returns it.
   * @return The kicker speed in RPM
   */
  public double getKickerSpeed() {
    return neoEncoder.getVelocity();
  }

    /**
   * Gets the target kicker speed
   * @return The target kicker speed in RPM
   */
  public double getKickerTargetSpeed() {
    return targetSpeed;
  }

  /**
   * Gets the current Kicker position
   * @return The kicker's position
   */
  public double getKickerPosition() {
    return kickerWheelMotor.getEncoder().getPosition();
  }

  /**
   * Method that returns the Kicker motor temperature
   * @return A double of the temperature (in Celsius) of the Kicker motor.
   */
  public double getKickerTemperature(){
    double temp = kickerWheelMotor.getMotorTemperature();
    return temp;
  }

  /**
   * Gets the future speed of the kicker wheel
   * @return - The future speed of the kicker wheel
   */
  public int getFutureSpeed() {
    return futureSpeed;
  }

  /**
   * Sets the future speed of the kicker wheel
   * @param futureSpeed - The future speed of the kicker wheel
   */
  public void setFutureSpeed(int futureSpeed) {
    this.futureSpeed = futureSpeed;
  }
}
