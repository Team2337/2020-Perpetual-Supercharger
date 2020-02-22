package frc.robot.commands.auto.commandgroups.common.systemactions;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.auto.zeroAngleEncoders;
import frc.robot.commands.auto.zeroDriveEncoders;
import frc.robot.commands.auto.zeroWithAnalog;

/**
 * In the beginning of auton the drive encoders and the angle encoders are reset to zero command group
 * @author Madison J. 
 * @category AUTON 
 */
public class AutonInit extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * In the beginning of auton the drive encoders and the angle encoders are reset to zero command group
 */
  public AutonInit() {
    addCommands(
      // new ParallelCommandGroup( 
        new zeroDriveEncoders(Robot.SwerveDrivetrain),
        /* new ConditionalCommand(
          new zeroWithAnalog(Robot.SwerveDrivetrain), 
          new zeroAngleEncoders(Robot.SwerveDrivetrain), 
          Robot.isCompRobot
          ) */
        //),
        new zeroAngleEncoders(Robot.SwerveDrivetrain)
        
    );
  }

 
}
