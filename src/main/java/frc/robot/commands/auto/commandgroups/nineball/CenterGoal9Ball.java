package frc.robot.commands.auto.commandgroups.nineball;

import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.commands.auto.zeroDriveEncoders;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Serializer.stopSerializer;
import frc.robot.commands.auto.commandgroups.common.systemactions.FirePartnerBalls;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

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
    public CenterGoal9Ball(double delay) {
        
    final class FirstDrive {
        public static final double moduleAngle = 0, driveDist = 35, forward = -0.35, strafe = 0, driveTimeout = 5;
      }


      
    addCommands(
      new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
      new WaitCommand(2).withTimeout(2),
      new FirePartnerBalls(2),
      new zeroDriveEncoders(Robot.SwerveDrivetrain).withTimeout(0.04),
      new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout)
    );
  
  }

 
}
