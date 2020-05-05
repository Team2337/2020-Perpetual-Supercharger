package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.auto.*;

/**
 * Drives from the front of the trench to the back, 
 * while intaking power cells 
 * @author Madison J. 
 * @category AUTON 
 */
public class InTrench3Ball extends ParallelCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;
  public double speed;

  /**
   * Drives from the front of the trench to the back, 
   * while intaking power cells 
   */
  public InTrench3Ball(double driveDistance, double speed) {
    this.speed = speed;

    /**
     * Constants for the third drive
     * (Must be sequentially added after any second drive autons)
     */
    final class ThirdDrive {
      public static final double robotAngle = 90;
    }

    addCommands(
        new AutoStrafeWithPixy(Robot.SwerveDrivetrain, driveDistance, speed, ThirdDrive.robotAngle),
        new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
        new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
        new WaitCommand(1).andThen(new autoStartShooter(Robot.Shooter, Constants.SHOOTFRONTTRENCHSPEED).andThen(new runKicker(Robot.KickerWheel)))
    );
  }
}
