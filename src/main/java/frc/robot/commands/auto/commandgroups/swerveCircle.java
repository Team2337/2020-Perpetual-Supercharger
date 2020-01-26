/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commandgroups;

import frc.robot.Robot;
import frc.robot.commands.auto.driveToPosition;
import frc.robot.commands.auto.rotateToAngle;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * The chassis rotates in a circle command group
 * @author Madison J. 
 * @category AUTON 
 */
public class swerveCircle extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
  * The chassis rotates in a circle command group
  */
  public swerveCircle() {
    addCommands(
      new driveToPosition(Robot.SwerveDrivetrain, 0, 0, 1),
      new rotateToAngle(Robot.SwerveDrivetrain, 49, 45, 0.5).withTimeout(254)

    );
  
  }

 
}
