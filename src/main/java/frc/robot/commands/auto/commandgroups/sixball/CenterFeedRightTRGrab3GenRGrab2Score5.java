package frc.robot.commands.auto.commandgroups.sixball;

import frc.robot.Robot;
import frc.robot.commands.Serializer.stopSerializer;
import frc.robot.commands.auto.commandgroups.common.Trench3BallPartnerDoesNotMove;
import frc.robot.commands.auto.commandgroups.common.movement.PostTrench;
import frc.robot.commands.auto.commandgroups.common.systemactions.FirePartnerBalls;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Shoot 6 balls with a partner feeding on our right, intake 3 balls from the trench and 2 from the generator, then shoot 5 balls
 */
public class CenterFeedRightTRGrab3GenRGrab2Score5 extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
 /**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls  
 */
  public CenterFeedRightTRGrab3GenRGrab2Score5(double delay) {
    addCommands(
      new WaitCommand(delay).withTimeout(delay),
      new FirePartnerBalls(1),
      new stopSerializer(Robot.Serializer),
      new Trench3BallPartnerDoesNotMove(), 
      new PostTrench()
    );
  
  }

 
}
