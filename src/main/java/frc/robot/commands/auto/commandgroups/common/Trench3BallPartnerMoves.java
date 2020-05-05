package frc.robot.commands.auto.commandgroups.common;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Robot;
import frc.robot.commands.auto.*;
import frc.robot.commands.auto.commandgroups.common.movement.*;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class Trench3BallPartnerMoves extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double driveDistance = 107;
  private double speed = -0.4;

  /**
   * Drives from the initiation line to the generator command group
   */
  public Trench3BallPartnerMoves() {

    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new PreTrenchNoPartner(),
      new InTrench3Ball(driveDistance, speed),
      new PostTrench3Ball()
    );
  }
}
