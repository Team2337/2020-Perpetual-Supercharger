package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.auto.driveToPosition;
import frc.robot.commands.auto.turnModulesToDegree;
import frc.robot.commands.auto.zeroAngleEncoders;

/**
 * The chassis rotates in a circle command group
 * @author Madison J. 
 * @category AUTON 
 */
public class GeneratorThreeBallFromCenterTarget extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
  * The chassis rotates in a circle command group
  */
  public GeneratorThreeBallFromCenterTarget() {
    addCommands(
      new zeroAngleEncoders(Robot.SwerveDrivetrain).withTimeout(0.04),
      new turnModulesToDegree(Robot.SwerveDrivetrain, 55, 2.00, 0.25).withTimeout(2),
      new driveToPosition(Robot.SwerveDrivetrain, -133, 0, 0.7, 0.05, 2.00).withTimeout(5)
      /*new rotateToAngleWithEncoder(Robot.SwerveDrivetrain, "left", -30, 0.3),
      new turnModulesToDegree(Robot.SwerveDrivetrain, 0, 1.1, 0.25).withTimeout(2)
      /* new driveToPosition(Robot.SwerveDrivetrain, -55, 0, 0.3, 0.05, 0.95).withTimeout(5),
      new turnModulesToDegree(Robot.SwerveDrivetrain, 90, 1.1, 0.25).withTimeout(2),
      new driveToPosition(Robot.SwerveDrivetrain, 50, 90, 0.5),
      new turnModulesToDegree(Robot.SwerveDrivetrain, 0, 1.1, 0.25)  */
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
