package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.Vision.limeLightLEDOn;
import frc.robot.commands.Vision.limelightPipeline;
import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithVision;
import frc.robot.commands.auto.resetDriveEncoders;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class PostTrench6Ball extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;

  /**
   * Drives from the initiation line to the generator command group
   */
  public PostTrench6Ball() {

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
      // new stopIntake(Robot.Intake),
      new AutoRotateWithJoystickInput(Robot.SwerveDrivetrain, FirstRotate.robotAngle),
      // new resetDriveEncoders(Robot.SwerveDrivetrain),
      // new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FifthDrive.driveDist, FifthDrive.forward, FifthDrive.strafe, FifthDrive.robotAngle).withTimeout(FifthDrive.driveTimeout), 
       new ParallelCommandGroup(
        new AutoRotateWithVision(Robot.SwerveDrivetrain, 1).withTimeout(2.0),
        new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED)
       )
    ); 
  }
}
