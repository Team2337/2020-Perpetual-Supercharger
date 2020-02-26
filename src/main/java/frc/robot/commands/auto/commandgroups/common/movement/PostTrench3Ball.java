package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.auto.commandgroups.common.movement.PreTrenchPartnerOnRight;

/**
 * Drives from the initiation line to the Trench to gather power cells
 * @author Madison J. 
 * @category AUTON 
 */
public class PostTrench3Ball extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public double intakeSpeed = 0.5;

  /**
   * Drives from the initiation line to the generator command group
   */
  public PostTrench3Ball() {

    /* --- Drives --- */
    final class FourthDrive {
      public static final double robotAngle = 90, driveDist = 20, forward = 0.15, strafe = -0.15, driveTimeout = 5;
    }

    /* --- Rotate --- */
    final class FirstRotate {
      public static final double robotAngle = 12;
    }

    addCommands(
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FourthDrive.driveDist, FourthDrive.forward, FourthDrive.strafe, FourthDrive.robotAngle).withTimeout(FourthDrive.driveTimeout),
      // new stopIntake(Robot.Intake),
      new AutoRotateWithJoystickInput(Robot.SwerveDrivetrain, FirstRotate.robotAngle), 
      new AutoRotateWithVision(Robot.SwerveDrivetrain).withTimeout(2),
      new runSerializer(Robot.Serializer, Constants.SERIALIZERFORWARDSPEED)
    ); 
  }
}
