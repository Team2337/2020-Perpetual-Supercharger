package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Robot;


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
  private boolean kickerWheelDebug = true;

  /** The speed the motors are currently set to. Changed in methods. */
  public double kspeed;
  /** Kicker wheel motor */
  public CANSparkMax kickerWheelMotor;
  /** PID controller of the Kicker wheel motor */
  private CANPIDController kickerPIDController;
  /** This is the variable taht determines weather or not to turn on the LEDs */
  public boolean kickerUpToSpeed = false;

  /* --- PID SETTINGS --- */
  double velocityP = 0.0001;
  double velocityI = 0;
  double velocityD = 0;
  double velocityFF = 0;
  double kMinOutput = -1;
  double kMaxOutput = 1;
  double positionalP = 0.9;
   
  /**
   * Creates a new Kicker subsystem and sets up the motors to their corresponding ports.
   */
  public KickerWheel() {
  
    // Sets up the motor (NEO 550) using the number specified in the Constants file.
    kickerWheelMotor = new CANSparkMax(Constants.KICKER, MotorType.kBrushless);

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

    kickerWheelMotor.setClosedLoopRampRate(0.0);

  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Debug mode: if on, put numbers on the SmartDashboard
    if(kickerWheelDebug){
      SmartDashboard.putNumber("Kicker wheel velocity", getKickerSpeed());
      SmartDashboard.putNumber("Kicker wheel target", Robot.Constants.KICKERSPEED);
    }

      SmartDashboard.putNumber("Kicker Temperature", getKickerTemperature());
      SmartDashboard.putBoolean("Kicker LED Test", Robot.KickerWheel.atPosition(Robot.Constants.KICKERSPEED, getKickerSpeed(), 10));
  }

  /**
   * Adjusts the speed of the kicker wheel by a certain amount.
   * @param speedChange The amount that the speed should increase or decrease by.
   */
  public void adjustKickerSpeed(double speedChange){
    kspeed = kspeed + speedChange;
    kickerPIDController.setReference(kspeed, ControlType.kVelocity);
  }

  /**
   * Stops the kicker wheel.
   */
  public void stopKicker(){
    kspeed = 0;
    kickerWheelMotor.set(kspeed);
  }

  /**
   * Sets the speed of the kicker wheel.
   * @param speed The speed to set the kicker wheel to (in velocity)
   */
  public void setKickerSpeed(double speed) {
  speed = speed * 2.65;   
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
    return kickerWheelMotor.getEncoder().getVelocity();
  }

  /**
   * Gets the current Kicker position
   * @return The kicker's position
   */
  public double getKickerPosition() {
    return kickerWheelMotor.getEncoder().getPosition();
  }

  public boolean atPosition(double target , double current, double tolerance) {
    if((target- current) <= tolerance){
      return true;
    }
    else{
      return false;
    }
   
    
  }

  /**
   * Method that returns the Kicker motor temperature
   * @return A double of the temperature (in Celsius) of the Kicker motor.
   */
  public double getKickerTemperature(){
    double temp = kickerWheelMotor.getMotorTemperature();
    return temp;
  }

}
