package frc.robot.commands.auto.commandgroups.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Shooter.stopShooter;
import frc.robot.commands.auto.checkShooterVelocity;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;

/**
 * Starts the shooter and when when the velocity of the shooter is reached 
 * run the serializer, agitator, and the intake until 3 balls have been shot
 * @author Madison J. 
 * @category AUTON 
 */
public class ShootThreeBall extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Starts the shooter and when when the velocity of the shooter is reached 
 * run the serializer, agitator, and the intake until 3 balls have been shot
 */
  public ShootThreeBall() {
    addCommands(
     new StartShooter(),
     new checkShooterVelocity(Robot.Serializer, Constants.SHOOTSPEEDCLOSE, Constants.KICKERSPEEDCLOSE).withTimeout(3),
     new FeedShooter(3).withTimeout(5),
     new stopShooter(Robot.Shooter)
    );
  
  }

 
}
