package frc.robot.commands.auto.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * The chassis goes in a diamond command group
 * @author Madison J.
 * @category AUTON
 */
public class swerveDiamond extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

/**
 * The chassis goes in a diamond command group
 */
  public swerveDiamond() {
    addCommands(
     /*  new driveToPosition(Robot.SwerveDrivetrain, 0, 45, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 45, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, -45, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, -45, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 45, 1),
      new driveToPosition(Robot.SwerveDrivetrain, -24, 45, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, -45, 1),
      new driveToPosition(Robot.SwerveDrivetrain, -24, -45, 1)
 */
    );
  
  }

 
}
