package frc.robot.commands.auto.commandgroups.sixball;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class RightGoalBack6BallFeedLeftTrench3BallShootGenerator2Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls  
 */
  public RightGoalBack6BallFeedLeftTrench3BallShootGenerator2Ball() {
    addCommands(
      // new AutonInit()
    );
  
  }

 
}
