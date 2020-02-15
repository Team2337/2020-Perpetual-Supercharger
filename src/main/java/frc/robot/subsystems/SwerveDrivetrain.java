package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.swerve.*;
import frc.robot.nerdyfiles.swerve.*;

/**
 * Subsystem where swerve modules are configured, 
 * and the calculations from the joystick inputs is handled. 
 * Field orientation is set here as well
 * 
 * @author Bryce G.
 * @category SWERVE
 */
public class SwerveDrivetrain extends SubsystemBase {

  // Sets the distances from module to module 
  public static final double WHEELBASE = 22.5;  
  public static final double TRACKWIDTH = 23.5; 

  // Length and width of the robot
  public static final double WIDTH = 29;  
  public static final double LENGTH = 30; 

  /* --- Private Double Values --- */
  private double deadband = 0.1;
  private double lastAngle;
  private double total;
  private double average;
  private double iteration;

  /**
   * Offsets the current gyro position to allow for 
   * rotational adjustments during the match
   */
  private double gyroOffset = 0; 

  /**
   * Array for module angle offsets
   * 0 is Front Right,
   * 1 is Front Left, 
   * 2 is Back Left, 
   * 3 is Back Right
   */
  public double angleOffsets[]; //TODO: change to private 
  
  /* --- Private Boolean Values --- */
  private boolean isFieldOriented = true;
  private boolean swerveDebug = false;

  public boolean fineRotateOn = false;
  public BooleanSupplier fineRotation = new BooleanSupplier(){
  
    @Override
    public boolean getAsBoolean() {
      // TODO Auto-generated method stub
      return fineRotateOn;
    }
  };

  /**
   * Array for swerve module Analog sensors, sorted by AnalogInput ports
	 * 0 is Front Right,
	 * 1 is Front Left, 
	 * 2 is Back Left, 
	 * 3 is Back Right
	 */
  private AnalogInput analogAngleSensors[];

  /**
   * Array for swerve module objects, sorted by ID
	 * 0 is Front Right,
	 * 1 is Front Left, 
	 * 2 is Back Left, 
	 * 3 is Back Right
	 */
  private FXSwerveModule[] swerveModules;
  
  /* --- Public Double Values --- */
  public double lastRotation;
  public double fieldOrientedAngle;

  /**
   * Subsystem where swerve modules are configured, 
   * and the calculations from the joystick inputs is handled. 
   * Field orientation is set here as well
   */
  public SwerveDrivetrain() {
    setDefaultCommand(new SwerveDriveCommand(this));

    angleOffsets = new double[] {
      4.5611,  // Module 0 //4.57
      1.278353,   // Module 1 //1.3
      -0.666697, // Module 2 //-0.678327
      -5.90436  // Module 3 -5.95
    };

    analogAngleSensors = new AnalogInput[] {
      new AnalogInput(0), // Module 0 
      new AnalogInput(1), // Module 1 
      new AnalogInput(2), // Module 2 
      new AnalogInput(3)  // Module 3
    };

    /* --- Array for modules --- */
    swerveModules = new FXSwerveModule[] {
      new FXSwerveModule(0, new TalonFX(Constants.MODULE0DRIVEMOTORID), new TalonFX(Constants.MODULE0ANGLEMOTORID), angleOffsets[0], analogAngleSensors[0]), // Module 0
      new FXSwerveModule(1, new TalonFX(Constants.MODULE1DRIVEMOTORID), new TalonFX(Constants.MODULE1ANGLEMOTORID), angleOffsets[1], analogAngleSensors[1]), // Module 1
      new FXSwerveModule(2, new TalonFX(Constants.MODULE2DRIVEMOTORID), new TalonFX(Constants.MODULE2ANGLEMOTORID), angleOffsets[2], analogAngleSensors[2]), // Module 2
      new FXSwerveModule(3, new TalonFX(Constants.MODULE3DRIVEMOTORID), new TalonFX(Constants.MODULE3ANGLEMOTORID), angleOffsets[3], analogAngleSensors[3])  // Module 3
    };

    // Setup for drive motor inversion (They may not need to be inverted)
    // (True: invered | False: not inverted)
    swerveModules[0].setDriveInverted(false);
    swerveModules[1].setDriveInverted(false);
    swerveModules[2].setDriveInverted(false);
    swerveModules[3].setDriveInverted(false);

  }

  /**
   * Calculates the desired angle of each module, 
   * and the speed and direction of the drive motors based on
   * joystick inputs
   * @param forward - double joystick value from the Y axis on the left hand stick
   * @param strafe - double joystick value from the X axis on the left hand stick
   * @param rotation - double joystick value from the X axis on the right hand stick
   */
  public void calculateJoystickInput(double forward, double strafe, double rotation) {
    
    // Adjusts forward and strafe based on the gyro if set in field oriented mode
    if (getFieldOriented()) {
      double angleRad = Math.toRadians(-Robot.Pigeon.getYaw()) % (2*Math.PI);
      double temp = forward * Math.cos(angleRad) + strafe * Math.sin(angleRad);
      strafe = -forward * Math.sin(angleRad) + strafe * Math.cos(angleRad);
      forward = temp;
    } 

    /*
     * a -> d adds the rotational value to the robot, then adjusts for the dimensions of the robot
     */
    double a = strafe - rotation * (WHEELBASE / TRACKWIDTH);
    double b = strafe + rotation * (WHEELBASE / TRACKWIDTH);
    double c = forward - rotation * (TRACKWIDTH / WHEELBASE);
    double d = forward + rotation * (TRACKWIDTH / WHEELBASE);

    /*
     * Calculations to decide the angle value of each module in RADIANS
     * Takes the arctangent of the values to find the angle that the joystick is
     * currently facing, compensating for the dimensions of the robot and rotation 
     * as given in the previous step
     */
    double[] angles = new double[]{
      Math.atan2(b, c),
      Math.atan2(b, d),
      Math.atan2(a, d),
      Math.atan2(a, c)
    };

    /*
     * Calculations to decide the drive motor speed value to set to each module
     */
    double[] speeds = new double[]{
      Math.sqrt(b * b + c * c),
      Math.sqrt(b * b + d * d),
      Math.sqrt(a * a + d * d),
      Math.sqrt(a * a + c * c)
    };

    /**
     * Max speed of the drive motor
     */
    double max = speeds[0];
    // sets the max speed
    for (double speed : speeds) {
        if (speed > max) {
            max = speed;
        }
        
    }

    // Goes through and sets the desired angle and drive speed of each module
    for(int i=0; i<4; i++) {
      if(max > 1) {
        speeds[i] = speeds[i]/max;
      }

      // Sets the angles and speeds if a joystick is beyond zero,
      // otherwise drive stops and the modules are sent to their last angle
      if(Math.abs(forward) > deadband || Math.abs(strafe) > deadband || Math.abs(rotation) > deadband) {
      //  SmartDashboard.putNumberArray("Angles", angles);
        lastAngle = angles[i];
        getModule(i).setModuleAngle(angles[i]);
        getModule(i).setDriveSpeed(speeds[i]);
      } else {
        getModule(i).setModuleAngle(lastAngle);
        getModule(i).setDriveSpeed(0);
      }
      //Sets the drive speed for each drive motor
     //SmartDashboard.putNumberArray("Drive Speeds", speeds);
    }
  }

  /**
   * Stops all of the drive motors on each module
   */
  public void stopDriveMotors() {
    for(int i=0; i<4; i++) {
      getModule(i).setDriveSpeed(0);
    }
  }

  /**
   * Stops all of the angle motors on each module
   */
  public void stopAngleMotors() {
    for(int i=0; i<4; i++) {
      getModule(i).setAngleMotorSpeed(0);
    }
  }

  /**
   * Gets the yaw of the gyro in RADIANS, moding it to get in terms of (-2PI -> 2PI)
   * @return - double value in RADIANS (-2PI -> 2PI)
   */
  public double getYaw() {
    return Math.toRadians(Robot.Utilities.getYaw("pigeon")) % (2 * Math.PI);
  }

  /**
   * Gets the module number specified (0 - 3)
   * @param module - int value specifying the module number (0 - 3). 
   * <b>NOTE:</b> The module number is not the CAN IDs of the motors. 
   * It is the actual module object's ID
   * @return - FXSwerveModule object
   */
  public FXSwerveModule getModule(int module) {
    return swerveModules[module];
  }

  /**
   * Sets the field orientation mode of the robot
   * (True: robot is field oriented | False: robot is robot oriented)
   * @param isFieldOriented - boolean value to set fieldOrientation mode
   */
  public void setFieldOriented(boolean isFieldOriented) {
    this.isFieldOriented = isFieldOriented;
  }

  /**
   * Sets the offset angle for each of the modules
   * @param angle - The angle we want to offset in radians
   */
  public void setGyroOffsetAngle(double angle) {
    this.gyroOffset = angle;
  }

  /**
   * Gets the offset angle for each of the modules
   * @return - Gyro angle offset in radians
   */
  public double getGyroOffsetAngle() {
    return gyroOffset;
  }

  public double getAverageAnalogValueInRadians(int module) {
    if(iteration < 200) {
      total += getModule(module).getNormalizedAnalogVoltageRadians();
      iteration++;
    } 
    average = total / iteration;
    return average;
  }

  /**
   * Gets the current field orientation mode
   * (True: robot is field oriented | False: robot is robot oriented)
   * @return - boolean value of current field orientation mode
   */
  public boolean getFieldOriented() {
      return this.isFieldOriented;
  }

  @Override
  public void periodic() {
    if (swerveDebug) {
      for(int i = 0; i < 4; i++) {
      SmartDashboard.putNumber("ModuleAngle/" + i, 
      ((getModule(i).getNormalizedAnalogVoltageRadians() - angleOffsets[i]) %(2 * Math.PI)) * 180 / Math.PI);
      }
    }
  }
}
