package frc.robot.commands.auto.commandgroups.common;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Shooter.startShooter;
import frc.robot.commands.Shooter.stopShooter;
import frc.robot.commands.ShooterSystem.shortShooterSystemOn;
import frc.robot.commands.auto.checkShooterVelocity;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;

/**
 * The chassis rotates in a circle command group
 * @author Madison J. 
 * @category AUTON 
 */
public class ShootSixBall extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
  * The chassis rotates in a circle command group
  */
  public ShootSixBall() {
    addCommands(
     new StartShooter(),
     new checkShooterVelocity(Robot.Serializer, Constants.SHOOTSPEEDCLOSE, Constants.KICKERSPEED).withTimeout(3),
     new FeedShooter(6).withTimeout(7),
     new stopShooter(Robot.Shooter)
    );
  
  }

 
}
