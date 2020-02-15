package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.commands.auto.commandgroups.common.ShootNineBall;
import frc.robot.commands.auto.commandgroups.common.systemactions.AutonInit;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Runs auton initialize and then shoot nine balls command group
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalBack9BallGenerator2Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Runs auton initialize then shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls 
 */
  public CenterGoalBack9BallGenerator2Ball() {
    addCommands(
      new AutonInit(),
      new ShootNineBall()
    );
  
  }

 
}
