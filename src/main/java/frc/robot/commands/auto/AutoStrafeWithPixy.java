package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrivetrain;

/**
 * Sets the forwards value to a set a mock joystick value
 * @see SwerveDrivetrain
 * @author Bryce G.
 * @category SWERVE
 */
public class AutoStrafeWithPixy extends CommandBase {
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
  private double offset;

  /**
   * Sets the forwards value to a set a mock joystick value
   * 
   * @param subsystem - SwerveDrivetrain Subsystem object
   * @param forward   - mock forward joystick value
   */
  public AutoStrafeWithPixy(SwerveDrivetrain SwerveDrivetrain, double encoderDist, double forwardDist,
     double endAngleDegree) {
    this.SwerveDrivetrain = SwerveDrivetrain;
    this.encoderDist = encoderDist;
    this.forwardDist = forwardDist;
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
    
    if(Robot.Vision.getPixyRightTarget()) {
      strafe = -(Math.toRadians(Robot.Vision.getPixyRightValue() - 2) * Constants.Auton.AUTOSTRAFEP);
    } else {
      strafe = 0;
    }

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
    return (Math.abs(SwerveDrivetrain.getModule(3).getDriveEncoderValue()) > encoderDist * Constants.Swerve.TICKSPERINCH) 
    ||( ((Math.abs(SwerveDrivetrain.getModule(3).getDriveEncoderValue()) > (encoderDist * Constants.Swerve.TICKSPERINCH) * 0.95)) 
    && Robot.Vision.getPixyRightTarget());
  }
}
