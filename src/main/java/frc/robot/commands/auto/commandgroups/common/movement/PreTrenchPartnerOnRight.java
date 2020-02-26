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
public class PreTrenchPartnerOnRight extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;

  /**
   * Drives from the initiation line to the generator command group
   */
  public PreTrenchPartnerOnRight() {

    final class FirstDrive {
      public static final double robotAngle = 0, driveDist = 36, forward = -0.35, strafe = 0, driveTimeout = 5;
    }

    final class SecondDrive {
      public static final double robotAngle = 90, driveDist = 72, forward = -0.2, strafe = 0.4, driveTimeout = 5;
    }

    final class ThirdDrive {
      public static final double robotAngle = 90, driveDist = 85, forward = -0.4, strafe = 0, driveTimeout = 5;
    }

    final class FirstRotate {
      public static final double moduleAngle = 12;
    }

    if(Robot.isComp) {

    }
    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.robotAngle).withTimeout(FirstDrive.driveTimeout),
       new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, SecondDrive.driveDist, SecondDrive.forward, SecondDrive.strafe, SecondDrive.robotAngle),
       new ParallelCommandGroup(
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, ThirdDrive.driveDist, ThirdDrive.forward, ThirdDrive.strafe, ThirdDrive.robotAngle).withTimeout(ThirdDrive.driveTimeout),
        new runIntake(Robot.Intake, intakeSpeed),
        new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
        new WaitCommand(1).andThen(new autoStartShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE).andThen(new runKicker(Robot.KickerWheel)))
      )
    );
  }
}
