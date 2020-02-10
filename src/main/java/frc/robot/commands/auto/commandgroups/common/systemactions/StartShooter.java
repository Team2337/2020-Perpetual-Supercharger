package frc.robot.commands.auto.commandgroups.common.systemactions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Shooter.startShooter;
import frc.robot.commands.ShooterSystem.shortShooterSystemOn;

/**
 * The chassis rotates in a circle command group
 * @author Madison J. 
 * @category AUTON 
 */
public class StartShooter extends ParallelCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
  * The chassis rotates in a circle command group
  */
  public StartShooter() {
    addCommands(
      new runKicker(Robot.KickerWheel, Constants.KICKERSPEED),
      new startShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE)
    );
  
  }

 
}
