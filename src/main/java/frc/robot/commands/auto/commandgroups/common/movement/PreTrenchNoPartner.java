package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Vision.limelightPipeline;
import frc.robot.commands.auto.*;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class PreTrenchNoPartner extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Drives from the initiation line to the generator command group
   */
  public PreTrenchNoPartner() {

    final class FirstDrive {
      public static final double moduleAngle = 90, driveDist = 74/* 82 */, forward = -0.35, strafe = 0.35, driveTimeout = 5;
    }

    // There is no second drive
    addCommands(
      new limelightPipeline(Robot.Vision, 1),
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout),
      new limelightPipeline(Robot.Vision, 0)
    );
  }
}
