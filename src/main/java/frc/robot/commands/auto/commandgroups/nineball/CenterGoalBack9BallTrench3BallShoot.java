package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.commands.auto.commandgroups.common.*;
import frc.robot.commands.auto.commandgroups.common.vision.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Shoots 9 balls and then collects 3 power cells from the trench and scores them using vision when our partner is not in our way
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalBack9BallTrench3BallShoot extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 9 balls and then collects 3 power cells from the trench and scores them using vision when our partner is not in our way
 */
  public CenterGoalBack9BallTrench3BallShoot() {
    addCommands(
      new ShootNineBall(), 
      new Trench6BallPartnerMoves(),
      new Vision3ShootBall()
    );
  
  }

 
}
