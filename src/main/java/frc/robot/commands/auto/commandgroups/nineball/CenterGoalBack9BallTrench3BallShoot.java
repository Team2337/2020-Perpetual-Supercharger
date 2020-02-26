package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.commands.auto.commandgroups.common.movement.*;
import frc.robot.commands.auto.commandgroups.common.*;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;
import frc.robot.commands.auto.commandgroups.common.vision.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalBack9BallTrench3BallShoot extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls  
 */
  public CenterGoalBack9BallTrench3BallShoot() {
    addCommands(
      new ShootNineBall(), 
      new Trench3BallPartnerMoves(),
      new Vision3ShootBall()
    );
  
  }

 
}
