package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
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
  public double kspeed;
  // Motors
public CANSparkMax kickerWheelMotor;
  //Current limit configuration
  
   
  /**
   * Creates a new Feeder subsystem and sets up the motors to their corresponding
   * ports.
   */
  public KickerWheel() {
    // These are the motors, Falcons, and they are set up here. Ports are referenced
    // in the Constants file
    kickerWheelMotor = new CANSparkMax(15, MotorType.kBrushless);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    // If in debug mode, put the feeder speed and temperature on Shuffleboard
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
  public void stopKickerSpeed(double speedChange){
    kspeed = 0;
    kickerWheelMotor.set(kspeed);
  }
}

  /**
   * @return feederMotor.getMotorOutputPercent();
   * This gets the motor output speed as a percent/array
   */