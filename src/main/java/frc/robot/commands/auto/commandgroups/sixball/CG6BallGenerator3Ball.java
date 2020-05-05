package frc.robot.commands.auto.commandgroups.sixball;

import frc.robot.commands.auto.commandgroups.common.movement.GeneratorThreeBallFromCenterTarget;
import frc.robot.commands.auto.commandgroups.common.systemactions.FireOnePartnerBall;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Shoots 6 power cells centered on the goal then the chassis drives to the generator and we intake 3 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class CG6BallGenerator3Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 6 power cells centered on the goal then the chassis drives to the generator and we intake 3 balls  
 */
  public CG6BallGenerator3Ball(double delay) {
    addCommands(
      new WaitCommand(delay).withTimeout(delay),
      new FireOnePartnerBall(1),
      new GeneratorThreeBallFromCenterTarget()
    );
  
  }

 
}
