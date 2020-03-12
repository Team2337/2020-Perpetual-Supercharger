package frc.robot.commands.auto.commandgroups.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Shooter.stopShooter;
import frc.robot.commands.auto.checkShooterVelocity;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;

/**
 * Starts the shooter and when when the velocity of the shooter is reached 
 * run the serializer, agitator, and the intake until 9 balls have been shot
 * @author Madison J. 
 * @category AUTON 
 */
public class ShootNineBall extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Starts the shooter and when when the velocity of the shooter is reached 
 * run the serializer, agitator, and the intake until 9 balls have been shot
 */
  public ShootNineBall() {
    addCommands(
     new StartShooter(),
     new checkShooterVelocity(Robot.Serializer, Constants.SHOOTSPEEDCLOSE, Constants.KICKERSPEEDCLOSE).withTimeout(3),
     new FeedShooter(9).withTimeout(10),
     new stopShooter(Robot.Shooter)
    );
  
  }

 
}
