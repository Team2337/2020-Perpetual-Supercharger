package frc.robot.commands.auto.commandgroups.common;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.auto.commandgroups.common.movement.InTrench3Ball;
import frc.robot.commands.auto.commandgroups.common.movement.PostTrench;
import frc.robot.commands.auto.commandgroups.common.movement.PreTrenchPartnerOnRight;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class Trench3BallPartnerDoesNotMove extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;
  public double driveDistance = 85;

  /**
   * Drives from the initiation line to the generator command group
   */
  public Trench3BallPartnerDoesNotMove() {

    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new PreTrenchPartnerOnRight(),
      new InTrench3Ball(driveDistance),
      new PostTrench() 
    ); 
  }
}
