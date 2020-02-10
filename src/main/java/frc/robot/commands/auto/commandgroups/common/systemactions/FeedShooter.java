package frc.robot.commands.auto.commandgroups.common.systemactions;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.Shooter.startShooter;
import frc.robot.commands.ShooterSystem.shortShooterSystemOn;
import frc.robot.commands.auto.ballCounter;

/**
 * The chassis rotates in a circle command group
 * @author Madison J. 
 * @category AUTON 
 */
public class FeedShooter extends ParallelCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
  * The chassis rotates in a circle command group
  */
  public FeedShooter(int ballsShot) {
    parallel(
    new ballCounter(Robot.OperatorAngleAdjustment, new DigitalInput(0), 10, ballsShot),
    new runSerializer(Robot.Serializer, Constants.SERIALIZERFORWARDSPEED),
    new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
    new runIntake(Robot.Intake, Constants.INTAKESPEED)
    );  
  }

 
}
