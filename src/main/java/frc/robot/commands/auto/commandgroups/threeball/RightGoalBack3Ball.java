package frc.robot.commands.auto.commandgroups.threeball;

import frc.robot.commands.auto.commandgroups.common.movement.GeneratorThreeBallFromCenterTarget;
import frc.robot.commands.auto.commandgroups.common.ShootNineBall;
import frc.robot.commands.auto.commandgroups.common.systemactions.AutonInit;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class RightGoalBack3Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls  
 */
  public RightGoalBack3Ball() {
    addCommands(
      new AutonInit()
    );
  
  }

 
}
