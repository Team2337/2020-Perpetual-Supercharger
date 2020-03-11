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
public class AutoRotateWithVision extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final SwerveDrivetrain SwerveDrivetrain;

  private double rotation;
  private double endAngleDegree;

  private int pipeline;

  /**
   * Sets the forwards value to a set a mock joystick value
   * 
   * @param subsystem - SwerveDrivetrain Subsystem object
   * @param forward   - mock forward joystick value
   */
  public AutoRotateWithVision(SwerveDrivetrain SwerveDrivetrain, int pipeline) {
    this.SwerveDrivetrain = SwerveDrivetrain;
    this.pipeline = pipeline;
    addRequirements(SwerveDrivetrain);
  }
  
  @Override
  public void initialize() {
    Robot.OperatorAngleAdjustment.setLimelightRotationMode(true);
  }

  @Override
  public void execute() {
    double tx = 0;
      if(Robot.Vision.getPipeline() == 0 && Robot.Shooter.getAvgRPM() > 250) {
        tx = -(Math.toRadians(Robot.Vision.getDoubleValue("tx") - 3));
      } else if (Robot.Vision.getPipeline() == 1) {
        tx = -(Math.toRadians(Robot.Vision.getDoubleValue("tx")));
      }
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
    
    // Pass on joystick values to be calculated into angles and speeds
    Robot.SwerveDrivetrain.calculateJoystickInput(0, 0, rotation);
  }
  
  @Override
  public void end(boolean interrupted) {
    Robot.OperatorAngleAdjustment.setLimelightRotationMode(false);
    Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getPigeonYawMod());
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
