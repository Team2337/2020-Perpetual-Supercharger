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

  /**
   * Sets the forwards value to a set a mock joystick value
   * 
   * @param subsystem - SwerveDrivetrain Subsystem object
   * @param forward   - mock forward joystick value
   */
  public AutoRotateWithVision(SwerveDrivetrain SwerveDrivetrain) {
    this.SwerveDrivetrain = SwerveDrivetrain;
    addRequirements(SwerveDrivetrain);
  }
  
  @Override
  public void initialize() {
  Robot.Vision.setLEDMode(3);
  }

  @Override
  public void execute() {
    rotation = -(Math.toRadians(Robot.Vision.getDoubleValue("tx")) * Constants.VISIONROTATIONP);
   
   // Pass on joystick values to be calculated into angles and speeds
   Robot.SwerveDrivetrain.calculateJoystickInput(0, 0, rotation);
  }

  @Override
  public void end(boolean interrupted) {
    Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getPigeonYawMod());

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}