package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.Robot;
import frc.robot.commands.auto.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalBack9BallGenerator2Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls  
 */
  public CenterGoalBack9BallGenerator2Ball() {
    addCommands(
      new zeroWithAnalog(Robot.SwerveDrivetrain).withTimeout(1),
      new zeroAngleEncoders(Robot.SwerveDrivetrain).withTimeout(0.04),
      new turnModulesToDegree(Robot.SwerveDrivetrain, 47, 1.1, 0.3).withTimeout(2), // 0.9
      new driveToPosition(Robot.SwerveDrivetrain, -140, 0, 0.7, 0.04, 1.1).withTimeout(3)
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
