package frc.robot.commands.auto;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.hal.sim.ConstBufferCallback;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the forwards value to a set a mock joystick value
 * @see SwerveDrivetrain
 * @author Bryce G.
 * @category SWERVE
 */
public class AutoDriveWithJoystickInput extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final SwerveDrivetrain SwerveDrivetrain;

  private double forward;
  private double strafe;
  private double rotation;

  private double forwardDist;

  private double endAngleDegree;
  private double currentGyro;
  private double rotationError;

  private double rotationP = 0.01; 
  private double maxRotationSpeed = 0.2;
  private double encoderDist = 0;

  /**
   * Sets the forwards value to a set a mock joystick value
   * 
   * @param subsystem - SwerveDrivetrain Subsystem object
   * @param forward   - mock forward joystick value
   */
  public AutoDriveWithJoystickInput(SwerveDrivetrain SwerveDrivetrain, double encoderDist, double forwardDist, double horizontalDist, double endAngleDegree) {
    this.SwerveDrivetrain = SwerveDrivetrain;
    this.encoderDist = encoderDist;
    this.forwardDist = forwardDist;
    this.strafe = horizontalDist * Constants.Auton.INCHESTOJOYSTICKVALUE;
    this.forward = forwardDist * Constants.Auton.INCHESTOJOYSTICKVALUE;
    this.endAngleDegree = endAngleDegree;
    addRequirements(SwerveDrivetrain);
  }
  
  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
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

  }

  @Override
  public boolean isFinished() {
    return Math.abs(SwerveDrivetrain.getModule(3).getDriveEncoderValue()) > encoderDist * Constants.Swerve.TICKSPERINCH;
  }
}
