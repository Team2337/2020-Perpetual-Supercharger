package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Intake.stopIntake;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Shooter.stopShooter;
import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithVision;
import frc.robot.commands.auto.autoStartShooter;
import frc.robot.commands.auto.driveToPosition;
import frc.robot.commands.auto.resetDriveEncoders;
import frc.robot.commands.auto.turnModulesToDegree;
import frc.robot.commands.auto.zeroAngleEncoders;
import frc.robot.commands.auto.zeroWithAnalog;
import frc.robot.commands.auto.commandgroups.common.systemactions.AutonInit;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class Trench3Ball extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Drives from the initiation line to the generator command group
   */
  public Trench3Ball() {
    boolean practice = true; //TODO: change to be if !compbot
    final class TurnModules {
      public static final double angleP = 1.1, angleMaxSpeed = 0.3, timeout = 2;
    }

    final class FirstDrive {
      public static final double moduleAngle = 90, driveDist = 87, forward = -0.35, strafe = 0.35, driveTimeout = 5;
    }

    final class SecondDrive {
      public static final double moduleAngle = 90, driveDist = 125, forward = -0.4, strafe = 0, driveTimeout = 5;
    }

    final class FirstRotate {
      public static final double moduleAngle = 12;
    }

    if(practice) {
      // addCommands();
    }
    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout),
      new ParallelCommandGroup(
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, SecondDrive.driveDist, SecondDrive.forward, SecondDrive.strafe, SecondDrive.moduleAngle).withTimeout(SecondDrive.driveTimeout),
        new runIntake(Robot.Intake, 1),
        new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
        new WaitCommand(1).andThen(new autoStartShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE).andThen(new runKicker(Robot.KickerWheel, Constants.KICKERSPEED)))
      ),
      new AutoRotateWithJoystickInput(Robot.SwerveDrivetrain, FirstRotate.moduleAngle), 
      new stopIntake(Robot.Intake),
      new stopShooter(Robot.Shooter)
      //new AutoRotateWithVision(Robot.SwerveDrivetrain)
    );
  }
}
