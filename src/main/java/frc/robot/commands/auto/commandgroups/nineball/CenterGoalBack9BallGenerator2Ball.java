/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.Robot;
import frc.robot.commands.auto.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * The chassis rotates in a circle command group
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoalBack9BallGenerator2Ball extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
  * The chassis rotates in a circle command group
  */
  public CenterGoalBack9BallGenerator2Ball() {
    addCommands(
    /* new turnModulesToDegree(Robot.SwerveDrivetrain, 55).withTimeout(0.5),
    new driveToPosition(Robot.SwerveDrivetrain, -140, 55) */
    //new turnModulesToDegree(Robot.SwerveDrivetrain, 55).withTimeout(0.5),
     new driveToPosition(Robot.SwerveDrivetrain, -140, 55).withTimeout(5),
     new rotateToAngle(Robot.SwerveDrivetrain, "left", -27, 0),
     new driveToPosition(Robot.SwerveDrivetrain, -30, 0),
     //new driveToPosition(Robot.SwerveDrivetrain, 50, 90, 5),
     //new driveToPosition(Robot.SwerveDrivetrain, 48, 0, 5),

     new turnModulesToDegree(Robot.SwerveDrivetrain, 0).withTimeout(0.5)
    );
  
  }

 
}
