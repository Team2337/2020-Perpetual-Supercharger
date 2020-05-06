package frc.robot.commands.auto.commandgroups.nineball;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.commandgroups.common.ShootNineBall;
import frc.robot.commands.auto.commandgroups.common.Trench6BallPartnerMoves;
import frc.robot.commands.auto.commandgroups.common.vision.Vision3ShootBall;

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
      new Trench6BallPartnerMoves(),
      new Vision3ShootBall()
    );
  
  }

 
}
