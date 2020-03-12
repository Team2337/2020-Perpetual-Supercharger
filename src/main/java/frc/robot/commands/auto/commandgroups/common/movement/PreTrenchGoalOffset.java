package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Robot;
import frc.robot.commands.auto.*;

/**
 * Drives from the initation line to the trench when offset from the goal
 * @author Madison J. 
 * @category AUTON 
 */
public class PreTrenchGoalOffset extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;

  /**
   * Drives from the initation line to the trench when offset from the goal
   */
  public PreTrenchGoalOffset() {

    final class FirstDrive {
      public static final double robotAngle = 90, driveDist = 77, forward = 0.45, strafe = -0.85, driveTimeout = 5;
    }

    if(Robot.isComp) {

    }
    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.robotAngle).withTimeout(FirstDrive.driveTimeout)
    );
  }
}
