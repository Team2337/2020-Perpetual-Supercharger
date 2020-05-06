package frc.robot.commands.auto.commandgroups.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;

/**
 * Starts the shooter and when when the velocity of the shooter is reached run feed shooter until 6 balls have been shot then turn off the shooter command group
 * @author Madison J. 
 * @category AUTON 
 */
public class ShootSixBall extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Starts the shooter and when when the velocity of the shooter is reached run feed shooter until 6 balls have been shot then turn off the shooter command group
 */
  public ShootSixBall() {
    addCommands(
     new StartShooter(),
     new checkShooterVelocity(Robot.Serializer, Constants.SHOOTSPEEDCLOSE, Constants.KICKERSPEEDCLOSE).withTimeout(3),
     new FeedShooter(6).withTimeout(7),
     new stopShooter(Robot.Shooter)
    );
  
  } 
}
