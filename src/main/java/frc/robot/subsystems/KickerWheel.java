package frc.robot.subsystems;

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
  public double kspeed;
  /** Kicker wheel motor */
  public CANSparkMax kickerWheelMotor;
  /** PID controller of the Kicker wheel motor */
  private CANPIDController kickerPIDController;

  /* --- PID SETTINGS --- */
  double kP = 0.01;
  double kI = 0;
  double kD = 0;
  double kFF = 0;
  double kMinOutput = -1;
  double kMaxOutput = 1;
   
  /**
   * Creates a new Kicker subsystem and sets up the motors to their corresponding ports.
   */
  public KickerWheel() {
    // Sets up the motor (NEO 550) using the number specified in the Constants file.
    kickerWheelMotor = new CANSparkMax(Constants.KICKERMOTOR, MotorType.kBrushless);
   
    // Sets up the PID controller
    kickerPIDController = kickerWheelMotor.getPIDController();
  
    // Sets up the PIDs
    kickerPIDController.setP(kP);
    kickerPIDController.setI(kI);
    kickerPIDController.setD(kD);
    kickerPIDController.setFF(kFF);
    kickerPIDController.setOutputRange(kMinOutput, kMaxOutput);

  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Debug mode: if on, put numbers on the SmartDashboard
    if(kickerWheelDebug){
      SmartDashboard.putNumber("Kicker wheel velocity", getKickerSpeed());
      
      SmartDashboard.putNumber("Kicker wheel target", kspeed);
    }
  }

  /**
   * Adjusts the speed of the kicker wheel by a certain amount.
   * @param speedChange The amount that the speed should increase or decrease by.
   */
  public void adjustKickerSpeed(double speedChange){
    kspeed = kspeed + speedChange;
    kickerWheelMotor.set(kspeed);
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
    kspeed = speed;
    kickerPIDController.setReference(speed, ControlType.kVelocity);
  }

  /**
   * Gets the kicker speed and returns it.
   * @return The kicker speed in RPM
   */
  public double getKickerSpeed() {
    return kickerWheelMotor.getEncoder().getVelocity();
  }

}