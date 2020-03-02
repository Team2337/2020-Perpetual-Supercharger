package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithVision;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class PostTrench3Ball extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;

  /**
   * Drives from the initiation line to the generator command group
   */
  public PostTrench3Ball() {

    /* --- Drives --- */
    final class FourthDrive {
      public static final double robotAngle = 90, driveDist = 20, forward = 0.15, strafe = -0.15, driveTimeout = 5;
    }

    /* --- Rotate --- */
    final class FirstRotate {
      public static final double robotAngle = 12;
    }

    addCommands(
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FourthDrive.driveDist, FourthDrive.forward, FourthDrive.strafe, FourthDrive.robotAngle).withTimeout(FourthDrive.driveTimeout),
      // new stopIntake(Robot.Intake),
      new AutoRotateWithJoystickInput(Robot.SwerveDrivetrain, FirstRotate.robotAngle), 
      new AutoRotateWithVision(Robot.SwerveDrivetrain, 1).withTimeout(2.0),
      new runSerializer(Robot.Serializer, Constants.SERIALIZERFORWARDSPEED)
    ); 
  }
}
