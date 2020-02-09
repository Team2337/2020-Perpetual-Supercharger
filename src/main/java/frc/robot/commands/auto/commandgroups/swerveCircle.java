package frc.robot.commands.auto.commandgroups;

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
     // new driveToPosition(Robot.SwerveDrivetrain, 0, 0, 1)
      //new rotateToAngle(Robot.SwerveDrivetrain, 49, 45, 0.5).withTimeout(254)

    );
  
  }

 
}
