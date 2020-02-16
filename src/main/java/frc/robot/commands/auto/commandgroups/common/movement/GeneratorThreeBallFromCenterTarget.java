package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.auto.driveToPosition;
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
      new zeroWithAnalog(Robot.SwerveDrivetrain).withTimeout(1),
      new zeroAngleEncoders(Robot.SwerveDrivetrain).withTimeout(0.04),
      new turnModulesToDegree(Robot.SwerveDrivetrain, 47, 1.1, 0.3).withTimeout(2), // 0.9
      new driveToPosition(Robot.SwerveDrivetrain, firstDriveDist, firstDriveModuleAngles, firstDriveMaxSpeed, firstDriveDriveP, firstDriveAngleP).withTimeout(3)
      /* new rotateToAngleWithEncoder(Robot.SwerveDrivetrain, "left", -30, 0.3),
      new turnModulesToDegree(Robot.SwerveDrivetrain, 0, 0.75, 0.3).withTimeout(2),
      new driveToPosition(Robot.SwerveDrivetrain, -27, 0, 0.3, 0.05, 1.1).withTimeout(3),
      new turnModulesToDegree(Robot.SwerveDrivetrain, 90, 1.1, 0.3).withTimeout(2),
      new driveToPosition(Robot.SwerveDrivetrain, 50, 90, 0.5, 0.05, 1.1) */
    /* new turnModulesToDegree(Robot.SwerveDrivetrain, 55).withTimeout(0.5),
    new driveToPosition(Robot.SwerveDrivetrain, -140, 55) */
    /*new turnModulesToDegree(Robot.SwerveDrivetrain, 55).withTimeout(0.5),
      new driveToPosition(Robot.SwerveDrivetrain, -145, 55).withTimeout(5),
     new driveToPosition(Robot.SwerveDrivetrain, -38, 0, 0.25), 
     new turnModulesToDegree(Robot.SwerveDrivetrain, 90).withTimeout(0.5),
     //new driveToPosition(Robot.SwerveDrivetrain, 48, 0, 5),
*/
    );
  
  }

 
}
