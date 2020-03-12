package frc.robot.commands.auto.commandgroups.threeball;

import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.Robot;
import frc.robot.commands.Serializer.stopSerializer;
import frc.robot.commands.auto.commandgroups.common.systemactions.FirePartnerBalls;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Scores 3 power cells then drives backwards off the initation line
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoal3Ball extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

    /**
     * Scores 3 power cells then drives backwards off the initation line
     */
    public CenterGoal3Ball(double delay) {
        
    final class FirstDrive {
        public static final double moduleAngle = 0, driveDist = 35, forward = -0.35, strafe = 0, driveTimeout = 5;
      }
      
    addCommands(
      new WaitCommand(delay).withTimeout(delay),
      new FirePartnerBalls(0),
      new stopSerializer(Robot.Serializer),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout)
    );
  
  }

 
}
