package frc.robot.commands.auto.commandgroups.sixball;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Serializer.stopSerializer;
import frc.robot.commands.auto.commandgroups.common.*;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Shoot 6 balls with a partner feeding on our right, 
 * intake 3 balls from the trench,
 * then shoot 3 balls
 */
public class CenterFeedRightTRGrab3Score3 extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
 /**
  * Shoot 6 balls with a partner feeding on our right, 
  * intake 3 balls from the trench,
  * then shoot 3 balls
  */
  public CenterFeedRightTRGrab3Score3(double delay) {
    addCommands(
      new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
      new WaitCommand(delay).withTimeout(delay),
      new FireOnePartnerBall(1),
      new stopSerializer(Robot.Serializer),
      new Trench3BallPartnerDoesNotMove()
    );
  
  }

 
}
