package frc.robot.commands.auto.commandgroups.threeball;

import frc.robot.Robot;
import frc.robot.commands.Serializer.stopSerializer;
import frc.robot.commands.auto.commandgroups.common.*;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Shoot 3 power cells,
 * intake 3 power cells from the trench, 
 * then shoot 3 power cells
 * 
 * @author Bryce G. Madison J.
 * @category AUTON
 */
public class CenterTRGrab3Score3 extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Shoot 3 power cells,
   * intake 3 power cells from the trench, 
   * then shoot 3 power cells
   */
  public CenterTRGrab3Score3(double delay) {
    addCommands(
      new WaitCommand(delay).withTimeout(delay),
      new FirePreloads(),
      new stopSerializer(Robot.Serializer),
      new Trench3BallPartnerMoves()
    );
  } 
}
