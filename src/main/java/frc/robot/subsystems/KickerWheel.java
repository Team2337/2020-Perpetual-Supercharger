package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/**
 *This subsystem makes the Kicker wheel in the robot which is the motor that speeds up the ball when it enters the shooter mechanism
 *Go faster stop or decrease in spead
 *@author Hunter B. 
 */
public class KickerWheel extends SubsystemBase {
  /**
   * Specifies whether or not the Feeder will be in debug mode.
   * @see #periodic()
   */
  private boolean kickerWheelDebug = true;


  public double kspeed;
  // Motors
public CANSparkMax kickerWheelMotor;
  //Current limit configuration
  private CANPIDController m_pidController;

  double kP = 0.01;
  double kI = 0;
  double kD = 0;
  double kFF = 0;
  double kMinOutput = -1;
  double kMaxOutput = 1;
   
  /**
   * Creates a new Feeder subsystem and sets up the motors to their corresponding
   * ports.
   */
  public KickerWheel() {
    // These are the motors, Falcons, and they are set up here. Ports are referenced
    // in the Constants file
    kickerWheelMotor = new CANSparkMax(15, MotorType.kBrushless);
   
    m_pidController = kickerWheelMotor.getPIDController();
  
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if(kickerWheelDebug){
      SmartDashboard.putNumber("Kicker wheel velocity", getKickerSpeed());
      
      SmartDashboard.putNumber("Kicker wheel target", kspeed);
    }
  }

  public void increaseKickerSpeed(double speedChange) {
    // Sets the speed of the feeder motors
   
  kspeed = kspeed + speedChange;
   kickerWheelMotor.set(kspeed);
  }

  public void decreaseKickerSpeed(double speedChange){
    kspeed = kspeed - speedChange;
    kickerWheelMotor.set(kspeed);
  }

  public void stopKicker(){
    kspeed = 0;
    kickerWheelMotor.set(kspeed);
  }

  /**
   * Method description
   * @param speed The speed to set the kicker wheel to
   */
  public void setKickerSpeed(double speed) {
    kspeed = speed;
    m_pidController.setReference(speed, ControlType.kVelocity);
  }

  public double getKickerSpeed() {
    return kickerWheelMotor.getEncoder().getVelocity();
  }

}