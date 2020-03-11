package frc.robot.commands.auto;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the forwards value to a set a mock joystick value
 * @see SwerveDrivetrain
 * @author Bryce G.
 * @category SWERVE
 */
public class AutoDriveWithAccelorometer extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final SwerveDrivetrain SwerveDrivetrain;

  private double forward;
  private double strafe;
  private double rotation;

  private double forwardDist;

  private double endAngleDegree;
  private double currentGyro;
  private double rotationError;

  private double rotationP = 0.009;
  private double maxRotationSpeed = 0.15;
  private double encoderDist = 0;

  /**
   * Sets the forwards value to a set a mock joystick value
   * 
   * @param subsystem - SwerveDrivetrain Subsystem object
   * @param forward   - mock forward joystick value
   */
  public AutoDriveWithAccelorometer(SwerveDrivetrain SwerveDrivetrain, double encoderDist, double forwardDist,
      double horizontalDist, double endAngleDegree) {
    this.SwerveDrivetrain = SwerveDrivetrain;
    this.encoderDist = encoderDist;
    this.forwardDist = forwardDist;
    this.strafe = horizontalDist * Constants.Auton.INCHESTOJOYSTICKVALUE;
    this.forward = forwardDist * Constants.Auton.INCHESTOJOYSTICKVALUE;
    this.endAngleDegree = endAngleDegree;

    /* last_world_linear_accel_x = RobotMap.accel.getX();
    last_world_linear_accel_y = RobotMap.accel.getY(); */
    
    addRequirements(SwerveDrivetrain);
  }
  
  @Override
  public void initialize() {
    /* collisionDetected = false;
		last_world_linear_accel_x = RobotMap.accel.getX();
		last_world_linear_accel_y = RobotMap.accel.getY(); */
  }

  @Override
  public void execute() {
    /* curr_world_linear_accel_x = RobotMap.accel.getX();
        currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
        last_world_linear_accel_x = curr_world_linear_accel_x;
        curr_world_linear_accel_y = RobotMap.accel.getY();
        currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
        last_world_linear_accel_y = curr_world_linear_accel_y;
        
        if ( ( Math.abs(currentJerkX) > kCollisionThreshold_DeltaG ) ||
             ( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) ) {
        	Robot.claw.open();
        	 collisionDetected = true;
        } */
    currentGyro = -Robot.Utilities.getPigeonYawMod();
    rotationError = (endAngleDegree - currentGyro);
    rotation = rotationError * rotationP;
    rotation = rotation > maxRotationSpeed ? maxRotationSpeed : rotation;
   
   // Pass on joystick values to be calculated into angles and speeds
   Robot.SwerveDrivetrain.calculateJoystickInput(forward, strafe, rotation);
  }

  @Override
  public void end(boolean interrupted) {
    Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getPigeonYawMod());
    System.out.println("Encoder Ticks: " + SwerveDrivetrain.getModule(3).getDriveEncoderValue());
    SwerveDrivetrain.zeroAllDriveEncoders();


  }

  @Override
  public boolean isFinished() {
    return false; //collisionDetected;
  }
}
