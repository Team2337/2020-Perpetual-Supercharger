package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.auto.*;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class RightTrench3Ball extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Drives from the initiation line to the generator command group
   */
  public RightTrench3Ball() {

    final class FirstDrive {
      public static final double moduleAngle = 90, driveDist = 57, forward = -0.35, strafe = 0.35, driveTimeout = 5;
    }

    final class SecondDrive {
      public static final double moduleAngle = 90, driveDist = 125, forward = -0.4, strafe = 0, driveTimeout = 5;
    }

    final class ThirdDrive {
      public static final double moduleAngle = 45, driveDist = 20, forward = 0.3, strafe = -0.3, driveTimeout = 5;
    }

    final class FirstRotate {
      public static final double moduleAngle = 12;
    }

    if(Robot.isComp) {

    }
    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout),
      new ParallelCommandGroup(
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, SecondDrive.driveDist, SecondDrive.forward, SecondDrive.strafe, SecondDrive.moduleAngle).withTimeout(SecondDrive.driveTimeout),
        new runIntake(Robot.Intake, 1),
        new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
        new WaitCommand(1).andThen(new autoStartShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE).andThen(new runKicker(Robot.KickerWheel)))
      ),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, ThirdDrive.driveDist, ThirdDrive.forward, ThirdDrive.strafe, ThirdDrive.moduleAngle).withTimeout(ThirdDrive.driveTimeout),
      new AutoRotateWithJoystickInput(Robot.SwerveDrivetrain, FirstRotate.moduleAngle), 
      new stopIntake(Robot.Intake),
      new AutoRotateWithVision(Robot.SwerveDrivetrain),
      new stopShooter(Robot.Shooter)
    );
  }
}
