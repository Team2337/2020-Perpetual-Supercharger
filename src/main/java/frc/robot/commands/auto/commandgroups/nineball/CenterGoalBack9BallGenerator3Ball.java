package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.commands.auto.commandgroups.common.movement.GeneratorThreeBallFromCenterTarget;
import frc.robot.commands.auto.commandgroups.common.ShootNineBall;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalBack9BallGenerator3Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls  
 */
  public CenterGoalBack9BallGenerator3Ball(double delay) {
    addCommands(
      new WaitCommand(delay).withTimeout(delay),
      //new FireNineBall(),
      new GeneratorThreeBallFromCenterTarget()
    );
  
  }

 
}
