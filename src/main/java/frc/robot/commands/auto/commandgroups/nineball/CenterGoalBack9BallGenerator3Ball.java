package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.commands.auto.commandgroups.common.movement.GeneratorThreeBallFromCenterTarget;
import frc.robot.commands.auto.commandgroups.common.systemactions.FirePartnerBalls;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Shoots 9 balls then drives to the generator to collect 3 power cells and score them
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalBack9BallGenerator3Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 9 balls then drives to the generator to collect 3 power cells and score them
 */
  public CenterGoalBack9BallGenerator3Ball(double delay) {
    addCommands(
      new WaitCommand(delay).withTimeout(delay),
      new FirePartnerBalls(2),
      new GeneratorThreeBallFromCenterTarget()
    );
  
  }

 
}
