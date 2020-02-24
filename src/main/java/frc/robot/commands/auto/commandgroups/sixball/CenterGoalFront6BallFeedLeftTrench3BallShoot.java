package frc.robot.commands.auto.commandgroups.sixball;

import frc.robot.commands.auto.commandgroups.common.movement.Trench3Ball;
import frc.robot.Robot;
import frc.robot.commands.Serializer.stopSerializer;
import frc.robot.commands.auto.commandgroups.common.systemactions.FirePartnerBalls;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalFront6BallFeedLeftTrench3BallShoot extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls  
 */
  public CenterGoalFront6BallFeedLeftTrench3BallShoot() {
    addCommands(/* 
      new FirePartnerBalls(0),
      new stopSerializer(Robot.Serializer), */
      new Trench3Ball()
    );
  
  }

 
}