package frc.robot.commands.swerve;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * Command running the swerve calculations with the joystick
 * 
 * @see SwerveDrivetrain
 * @author Bryce G.
 * @category SWERVE
 */
public class SwerveDriveCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  private boolean swerveDebug = false;

  private final SwerveDrivetrain swerveDrivetrain;

  /**
   * Value from the Y axis on the left joystick, on the driver controller
   */
  private double forward = 0;

  /**
   * Value from the X axis on the left joystick, on the driver controller
   */
  private double strafe = 0;

  /**
   * Value from the X axis on the right joystick, on the driver controller
   */
  private double rotation = 0;
  private double error;
  private double kP;
  
  /** Rotation value of the previous iteration */
  private double lastRotation;
  /** Deadband for the rotational input  */
  private double rotationDeadband = 0.1;
  /** Rotational P while not rotating */
  private double stationaryP = 0.015;
  /** Rotational P while rotating */
  private double movingP = 0.01; //0.007

  /**
   * Command running the swerve calculations with the joystick
   * 
   * @param subsystem - SwerveDrivetrain subsystem object
   */
  public SwerveDriveCommand(SwerveDrivetrain subsystem) {
    this.swerveDrivetrain = subsystem;
    if(Robot.isComp) {
      stationaryP = 0.007;
      movingP = 0.002;
    } else {
      stationaryP = 0.015;
      movingP = 0.007;
    }
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    /* --- Joystick Values --- */
    forward = -Robot.OI.driverJoystick.getRawAxis(1);
    strafe = Robot.OI.driverJoystick.getRawAxis(0);
    rotation = -Robot.OI.driverJoystick.getRawAxis(4);

    // Set Deadband
    forward = Robot.Utilities.deadband(forward, 0.1);
    strafe = Robot.Utilities.deadband(strafe, 0.1);
    rotation = Robot.Utilities.deadband(rotation, 0.1);

    /* --- Rotation Angle Adjustments --- */
    if (Math.abs(rotation) > rotationDeadband) {
      lastRotation = rotation;
    } else {
      // Checks to see if we were rotating in the previous iteration, but are not currently rotating 
      if (Math.abs(lastRotation) > rotationDeadband && Math.abs(rotation) <= rotationDeadband) {
        Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getPigeonYawMod());
        rotation = 0;
        lastRotation = rotation;
      }
      // Checks to see if the Driver's button is being pressed, and sets the current offset angle
      if (Robot.OperatorAngleAdjustment.getIsChangingGyroAngle()) {
        Robot.OperatorAngleAdjustment.setOffsetAngle(Robot.OperatorAngleAdjustment.getFutureOffsetAngle());
      }
      // Sets the error of the robot's angle offset & current gyro angle 
      error = Robot.OperatorAngleAdjustment.getGyroAngleOffset() + Robot.Utilities.getPigeonYawMod();
      kP = forward == 0 && strafe == 0 ? stationaryP : movingP;
      if(error > 180) {
        error -= 360;
      } else if(error < -180) {
        error += 360;
      }
      rotation = Robot.OperatorAngleAdjustment.calculateGyroOffset(error, kP);
    }
    
    // Checks the limelight mode to rotate towards the target
    if(Robot.OperatorAngleAdjustment.getLimelightRotationMode()) {
      double tx = -(Math.toRadians(Robot.Vision.getDoubleValue("tx") + 2));
      if(Robot.Vision.getPipeline() == 1) {
        if(Math.abs(tx) <  Math.toRadians(2)) {
          rotation = (tx * Constants.VISIONCLOSEROTATIONP);
        } else if(Math.abs(tx) < Math.toRadians(5)) {
          rotation = (tx * Constants.VISIONMIDDLEROTATIONP);
        } else {
          rotation = (tx * Constants.VISIONOFFROTATIONP);
        }
      } else {
        if(Math.abs(tx) <  Math.toRadians(2)) {
          rotation = (tx * Constants.VISIONCLOSEROTATIONP);
        } else if(Math.abs(tx) < Math.toRadians(5)) {
          rotation = (tx * Constants.VISIONMIDDLEROTATIONP);
        } else {
          rotation = (tx * Constants.VISIONOFFROTATIONP);
        }
      }
    }

    if(Robot.OperatorAngleAdjustment.getBallTrackingEnabled()){
      if(Robot.Vision.pixyRightDigital.get()) {
        rotation = -(Math.toRadians(Robot.Vision.getPixyRightValue() - 2) * Constants.BALLTRACKINGP);
      } else {
        rotation = 0;
      }
    }
    
    // Checks to see if we are in slow rotate mode, then directly sets the rotation to the given speed
    if(Robot.OperatorAngleAdjustment.getSlowRotateMode()) {
      rotation = Robot.OperatorAngleAdjustment.getSlowRotateSpeed();
      if(Math.abs(error) >= 25) {
        Robot.OperatorAngleAdjustment.setSlowRotateMode(false);
        Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getPigeonYawMod());
      }
    }

    // Pass on joystick values to be calculated into angles and speeds
    swerveDrivetrain.calculateJoystickInput(forward, strafe, rotation);
    
    if(swerveDebug) {
      SmartDashboard.putNumber("Forward", forward);
      SmartDashboard.putNumber("Strafe", strafe);
      SmartDashboard.putNumber("Rotation", rotation);
    }

  }

  @Override
  public void end(boolean interrupted) {
    // In the event this command stops, we don't want the motors to move
    swerveDrivetrain.stopAngleMotors();
    swerveDrivetrain.stopDriveMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
