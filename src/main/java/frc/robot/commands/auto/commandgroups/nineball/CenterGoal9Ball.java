package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.Robot;
import frc.robot.commands.Serializer.stopSerializer;
import frc.robot.commands.auto.commandgroups.common.systemactions.FirePartnerBalls;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Shoots 9 balls centered on the goal then the chassis drives to the generator and we intake 3 balls
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoal9Ball extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

    /**
     * Shoots 9 balls centered on the goal then the chassis drives to the generator
     * and we intake 3 balls
     */
    public CenterGoal9Ball() {
        
    final class FirstDrive {
        public static final double moduleAngle = 0, driveDist = 35, forward = -0.35, strafe = 0, driveTimeout = 5;
      }
      
    addCommands(
      new FirePartnerBalls(2),
      new stopSerializer(Robot.Serializer),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout)
    );
  
  }

 
}
