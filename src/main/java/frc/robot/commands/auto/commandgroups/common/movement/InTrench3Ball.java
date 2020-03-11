package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.auto.*;

/**
 * Drives in the trench to collect 3 power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class InTrench3Ball extends ParallelCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;
  public double speed;

  /**
   * Drives in the trench to collect 3 power cells
   */
  public InTrench3Ball(double driveDistance, double speed) {
    this.speed = speed;

    final class ThirdDrive {
      public static final double robotAngle = 90, forward = -0.4, strafe = 0, driveTimeout = 5;
    }

    addCommands(
        new AutoStrafeWithPixy(Robot.SwerveDrivetrain, driveDistance, speed, ThirdDrive.robotAngle),
        new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
        new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
        new WaitCommand(1).andThen(new autoStartShooter(Robot.Shooter, Constants.SHOOTFRONTTRENCHSPEED).andThen(new runKicker(Robot.KickerWheel)))
    );
  }
}
