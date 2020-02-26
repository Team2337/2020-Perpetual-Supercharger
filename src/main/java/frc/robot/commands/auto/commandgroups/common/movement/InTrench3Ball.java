package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.auto.*;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class InTrench3Ball extends ParallelCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;

  /**
   * Drives from the initiation line to the generator command group
   */
  public InTrench3Ball(double driveDistance) {

    final class ThirdDrive {
      public static final double robotAngle = 90, forward = -0.4, strafe = 0, driveTimeout = 5;
    }

    addCommands(
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, driveDistance, ThirdDrive.forward, ThirdDrive.strafe, ThirdDrive.robotAngle).withTimeout(ThirdDrive.driveTimeout),
        new runIntake(Robot.Intake, intakeSpeed),
        new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
        new WaitCommand(1).andThen(new autoStartShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE).andThen(new runKicker(Robot.KickerWheel)))
    );
  }
}
