package frc.robot.commands.auto.commandgroups.common.systemactions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Shooter.startShooter;

/**
 * Runs the kicker and starts the shooter command group
 * @author Madison J. 
 * @category AUTON 
 */
public class StartShooter extends ParallelCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Runs the kicker and starts the shooter command group
 */
  public StartShooter() {
    addCommands(
      new runKicker(Robot.KickerWheel),
      new startShooter(Robot.Shooter)
    );
  
  }

 
}
