package frc.robot.commands.auto.commandgroups.common.systemactions;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.auto.zeroAngleEncoders;

/**
 * Sets the angle encoders to zero command group
 * @author Madison J. 
 * @category AUTON 
 */
public class SendModulesToZero extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Sets the angle encoders to zero command group
 */
  public SendModulesToZero() {
    addCommands(
    new zeroAngleEncoders(Robot.SwerveDrivetrain)
    );
  
  }

 
}
