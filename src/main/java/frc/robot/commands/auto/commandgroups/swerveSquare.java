/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.commandgroups;

import frc.robot.Robot;
import frc.robot.commands.auto.driveToPosition;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * An example command that uses an example subsystem.
 */
public class swerveSquare extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public swerveSquare() {
    addCommands(
      new driveToPosition(Robot.SwerveDrivetrain, 24, 0, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 90, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 90, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 180, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 180, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 270, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 270, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 0, 0, 1)

    );
  
  }

 
}
