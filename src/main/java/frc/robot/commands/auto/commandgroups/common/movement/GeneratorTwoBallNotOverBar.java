package frc.robot.commands.auto.commandgroups.common.movement;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.commands.auto.resetDriveEncoders;

/**
 * Drives from the initiation line to the left generator command group
 * @author Madison J. 
 * @category AUTON 
 */
public class GeneratorTwoBallNotOverBar extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Drives from the initiation line to the left generator command group
 */
  public GeneratorTwoBallNotOverBar() {
    /* --- Driving to Generator Left --- */
    int firstDriveDist = -140, firstDriveModuleAngles = 47;
    double firstDriveMaxSpeed = 0.7, firstDriveDriveP = 0.04, firstDriveAngleP = 1.1;

    addCommands(
      new resetDriveEncoders(Robot.SwerveDrivetrain),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 155, -0.25, -0.3, 28),
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, 25, 0, 0, 28).withTimeout(1)
        
    );
  
  }

 
}
