package frc.robot.commands.auto.commandgroups.threeball;

import frc.robot.Robot;
import frc.robot.commands.Serializer.stopSerializer;
import frc.robot.commands.auto.commandgroups.common.*;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Scores 3 power cells then drives to the trench to collect 3 power cells and score them
 */
public class CenterTRGrab3Score3 extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Scores 3 power cells then drives to the trench to collect 3 power cells and score them
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
