package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.Vision.limeLightLEDOn;
import frc.robot.commands.Vision.limelightPipeline;
import frc.robot.commands.auto.*;

/**
 * Drives from the trench to the goal and rotates using vision to score 3 power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class PostTrench3Ball extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;

  /**
   * Drives from the trench to the goal and rotates using vision to score 3 power cells
   */
  public PostTrench3Ball() {

    /* --- Drives --- */
    final class FourthDrive {
      public static final double robotAngle = 90, driveDist = 10, forward = 0.4, strafe = -0.4, driveTimeout = 5;
    }

    final class FifthDrive {
      public static final double robotAngle = 12, driveDist = 5, forward = 0.325, strafe = -0.075, driveTimeout = 5;
    }

    /* --- Rotate --- */
    final class FirstRotate {
      public static final double robotAngle = 20; //12
    }

    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new limeLightLEDOn(Robot.Vision),
      new limelightPipeline(Robot.Vision, 1),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FourthDrive.driveDist, FourthDrive.forward, FourthDrive.strafe, FourthDrive.robotAngle).withTimeout(FourthDrive.driveTimeout),
      new AutoRotateWithJoystickInput(Robot.SwerveDrivetrain, FirstRotate.robotAngle),
        new AutoRotateWithVision(Robot.SwerveDrivetrain, 1).withTimeout(2.0),
        new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED)
    ); 
  }
}
