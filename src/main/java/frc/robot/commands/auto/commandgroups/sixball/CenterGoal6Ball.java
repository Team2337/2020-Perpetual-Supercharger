package frc.robot.commands.auto.commandgroups.sixball;

import frc.robot.commands.auto.AutoDriveWithJoystickInput;
import frc.robot.commands.auto.zeroDriveEncoders;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.auto.commandgroups.common.systemactions.FireOnePartnerBall;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Shoots 6 power cells and then drives backwards off the initation line
 * @author Madison J. 
 * @category AUTON 
 */
public class CenterGoal6Ball extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

    /**
     * Shoots 6 power cells and then drives backwards off the initation line
     */
    public CenterGoal6Ball(double delay) {
        
    final class FirstDrive {
        public static final double moduleAngle = 0, driveDist = 35, forward = -0.35, strafe = 0, driveTimeout = 5;
      }
  
    addCommands(
      new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
      new WaitCommand(2).withTimeout(2),
      new FireOnePartnerBall(1),
      new zeroDriveEncoders(Robot.SwerveDrivetrain).withTimeout(0.04),
      new ParallelRaceGroup(
        new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED),
        new AutoDriveWithJoystickInput(Robot.SwerveDrivetrain, FirstDrive.driveDist, FirstDrive.forward, FirstDrive.strafe, FirstDrive.moduleAngle).withTimeout(FirstDrive.driveTimeout)
      )
    );
  
  }

 
}
