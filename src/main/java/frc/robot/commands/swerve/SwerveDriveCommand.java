package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Command running the swerve calculations with the joystick
 * 
 * @see SwerveDrivetrain
 * @author Bryce G.
 * @category SWERVE
 */
public class SwerveDriveCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

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
  private double lastAngle;
  private double lastRotation;
  private double rotationDeadband = 0.1;
  private double lastError;
  private double rotationVelocity;
  private double velocityDeadband = 1;
  private double lastVelocity;
  
  private double stationaryP = 0.015;
  private double movingP = 0.007;

  /**
   * Command running the swerve calculations with the joystick
   * 
   * @param subsystem - SwerveDrivetrain subsystem object
   */
  public SwerveDriveCommand(SwerveDrivetrain subsystem) {
    this.swerveDrivetrain = subsystem;
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
    rotation = Robot.Utilities.deadband(rotation, 0.05);

    // Smartdashboard prints
    SmartDashboard.putNumber("Forward", forward);
    SmartDashboard.putNumber("Strafe", strafe);
    SmartDashboard.putNumber("Rotation", rotation);

    if (Math.abs(rotation) > rotationDeadband) {
      //lastAngle = Robot.Utilities.getYawMod();
      lastRotation = rotation;
    } else {
      if (Math.abs(lastRotation) > rotationDeadband && Math.abs(rotation) <= rotationDeadband) {
        Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getYawMod());
        rotation = 0;
        lastRotation = rotation;
      }
      rotationVelocity = Robot.Utilities.calculateDerivative(error, lastError, 0.002);
      if (Math.abs(rotationVelocity) > velocityDeadband) {
        lastVelocity = rotationVelocity;
      } else {
        if (Math.abs(lastVelocity) > velocityDeadband && Math.abs(rotationVelocity) <= velocityDeadband) {
          System.out.println("fdijkfdisjiffdsjkhnserdtcyuvbiokoiuuies" + -Robot.Utilities.getYawMod());
          Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getYawMod());
          rotationVelocity = 0;
          lastVelocity = rotationVelocity;
        }
      }
      if (Robot.OperatorAngleAdjustment.getIsChangingGyroAngle()) {
        Robot.OperatorAngleAdjustment.setOffsetAngle(Robot.OperatorAngleAdjustment.getFutureOffsetAngle());
      }
      error = Robot.OperatorAngleAdjustment.getGyroAngleOffset() + Robot.Utilities.getYawMod();
      // System.out.println("error: " + error + " offsetAngle: " + Robot.OperatorAngleAdjustment.getGyroAngleOffset());
      kP = forward == 0 && strafe == 0 ? stationaryP : movingP;
      rotation = Robot.OperatorAngleAdjustment.calculateGyroOffset(error, rotation, kP);
    }
    // Pass on joystick values to be calculated into angles and speeds
    System.out.println("forawrd: " + forward + " strafe: " + strafe+  " rotation: "+ rotation);
    swerveDrivetrain.calculateJoystickInput(forward, strafe, rotation);
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
