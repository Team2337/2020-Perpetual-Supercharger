package frc.robot.commands.auto.commandgroups.common.systemactions;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Serializer.*;
import frc.robot.commands.auto.*;

/**
 * Runs the serializer, agitator, and the intake command group
 * @author Madison J. 
 * @category AUTON 
 */
public class FeedShooter extends ParallelCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Runs the serializer, agitator, and the intake command group
 */
  public FeedShooter(int ballsShot) {
    parallel(
    new ballCounter(Robot.OperatorAngleAdjustment, new DigitalInput(0), 10, ballsShot),
    new runSerializer(Robot.Serializer, Constants.SERIALIZERFORWARDSPEED),
    new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
    new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED)
    );  
  }

 
}
