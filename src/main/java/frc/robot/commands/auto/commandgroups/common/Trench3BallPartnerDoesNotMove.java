package frc.robot.commands.auto.commandgroups.common;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.auto.*;
import frc.robot.commands.auto.commandgroups.common.movement.InTrench3Ball;
import frc.robot.commands.auto.commandgroups.common.movement.PostTrench6Ball;
import frc.robot.commands.auto.commandgroups.common.movement.PreTrenchPartnerOnRight;

/**
 * Drives from the initiation line to the trench to gather power cells when our partner does not move out of our way
 * @author Madison J. 
 * @category AUTON 
 */
public class Trench3BallPartnerDoesNotMove extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.6;
  public double driveDistance = 110;
  public double speed = -0.45;

  /**
   * Drives from the initiation line to the trench to gather power cells when our partner does not move out of our way
   */
  public Trench3BallPartnerDoesNotMove() {

    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new PreTrenchPartnerOnRight(),
      new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
      new InTrench3Ball(driveDistance, speed),
      new PostTrench6Ball()
    ); 
  }
}
