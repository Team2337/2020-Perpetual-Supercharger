package frc.robot.commands.auto.commandgroups.sixball;

import frc.robot.commands.auto.commandgroups.common.movement.GeneratorThreeBallFromCenterTarget;
import frc.robot.commands.auto.commandgroups.common.systemactions.FireOnePartnerBall;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Scores 6 power cells then drives to the generator to collect 3 power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class CG6BallGenerator3Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
  * Scores 6 power cells then drives to the generator to collect 3 power cells
  */
  public CG6BallGenerator3Ball(double delay) {
    addCommands(
      new WaitCommand(delay).withTimeout(delay),
      new FireOnePartnerBall(1),
      new GeneratorThreeBallFromCenterTarget()
    );
  
  }

 
}
