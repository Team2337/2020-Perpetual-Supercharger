package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Intake.stopIntake;
import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithJoystickInput;
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
      public static final int driveDist = -140;
      public static final double moduleAngle = 90, driveP = 0.04, angleP = 1.1, driveMaxSpeed = 0.7, driveTimeout = 5;
    }

    if(practice) {
      // addCommands();
    }
    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 95, -0.3, 0.3, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout),
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new ParallelCommandGroup(
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 120, -0.3, 0, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout),
        new runIntake(Robot.Intake, 0.7)
      ),
      new AutoRotateWithJoystickInput(Robot.SwerveDrivetrain, 12), 
      new stopIntake(Robot.Intake)
    );
  }
}
