package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.commands.auto.commandgroups.common.ShootNineBall;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Scores 9 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalBack9BallGenerator2Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Scores 9 balls
 */
  public CenterGoalBack9BallGenerator2Ball() {
    addCommands(
      new ShootNineBall()
    );
  
  }

 
}
