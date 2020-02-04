/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commandgroups;

import frc.robot.Robot;
import frc.robot.commands.auto.driveToPosition;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * The chassis goes in a triangle command group
 * @author Madison J.
 * @category AUTON
 */
public class swerveTriangle extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

/**
 * The chassis goes in a trianlge command group
 */
  public swerveTriangle() {
    addCommands(
    /*   new driveToPosition(Robot.SwerveDrivetrain, 0, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 90, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 90, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 45, 1),
      new driveToPosition(Robot.SwerveDrivetrain, -34, 45, 1)
 */
    );
  
  }

 
}
