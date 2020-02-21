package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.Shooter.startShooter;
import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithJoystickInput;
import frc.robot.commands.auto.AutoRotateWithVision;
import frc.robot.commands.auto.autoStartShooter;
import frc.robot.commands.auto.driveToPosition;
import frc.robot.commands.auto.resetDriveEncoders;
import frc.robot.commands.auto.turnModulesToDegree;
import frc.robot.commands.auto.zeroAngleEncoders;
import frc.robot.commands.auto.zeroWithAnalog;

/**
 * Drives from the initiation line to the generator command group
 * @author Madison J. 
 * @category AUTON 
 */
public class GeneratorThreeBallFromCenterTarget extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Drives from the initiation line to the generator command group
 */
  public GeneratorThreeBallFromCenterTarget() {
    /* --- Driving to Generator Left --- */
    int firstDriveDist = -140, firstDriveModuleAngles = 47;
    double firstDriveMaxSpeed = 0.7, firstDriveDriveP = 0.04, firstDriveAngleP = 1.1;
    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 155, -0.25, -0.3, 28),
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 25, 0, 0, 28).withTimeout(1),
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 33, -0.2, 0.1, 28).withTimeout(5),
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 25, 0, 0, 28).withTimeout(1),
      new ParallelCommandGroup(
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 53, 0.15, 0.3, 28).withTimeout(5),
        new runIntake(Robot.Intake, 0.5),
        new autoStartShooter(Robot.Shooter, 8000),
        new runKicker(Robot.KickerWheel)
        ),
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 25, 0, 0, 28).withTimeout(1),
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 50, 0.3, -0.15, 28).withTimeout(5),
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 25, 0, 0, 28).withTimeout(1),
        new AutoRotateWithJoystickInput(Robot.SwerveDrivetrain, -15),
        new ParallelCommandGroup(
          new AutoRotateWithVision(Robot.SwerveDrivetrain),
          new runSerializer(Robot.Serializer, 0.3)
        )
    );
  
  }

 
}
