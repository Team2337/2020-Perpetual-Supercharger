package frc.robot.commands.auto.commandgroups.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.auto.resetDriveEncoders;
import frc.robot.commands.auto.commandgroups.common.movement.InTrench3Ball;
import frc.robot.commands.auto.commandgroups.common.movement.PostTrench6Ball;
import frc.robot.commands.auto.commandgroups.common.movement.PreTrenchNoPartner;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class Trench6BallPartnerMoves extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double driveDistance = 111;
  private double speed = -0.4;

  /**
   * Drives from the initiation line to the generator command group
   */
  public Trench6BallPartnerMoves() {

    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new PreTrenchNoPartner(),
      new InTrench3Ball(driveDistance, speed),
      new PostTrench6Ball()
    );
  }
}
