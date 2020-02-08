package frc.robot.commands.auto.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * The chassis goes in a square command group
 * @author Madison J.
 * @category AUTON
 */
public class swerveSquare extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

/**
 * The chassis goes in a square command group
 */
  public swerveSquare() {
    addCommands(
     /*  new driveToPosition(Robot.SwerveDrivetrain, 24, 0, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 90, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 90, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 180, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 180, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 270, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 24, 270, 1),
      new driveToPosition(Robot.SwerveDrivetrain, 0, 0, 1)
 */
    );
  
  }

 
}
