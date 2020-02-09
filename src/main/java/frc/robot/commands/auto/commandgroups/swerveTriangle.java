package frc.robot.commands.auto.commandgroups;

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
