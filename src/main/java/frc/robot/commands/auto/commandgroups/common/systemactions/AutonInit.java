package frc.robot.commands.auto.commandgroups.common.systemactions;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.Shooter.startShooter;
import frc.robot.commands.ShooterSystem.shortShooterSystemOn;
import frc.robot.commands.auto.ballCounter;
import frc.robot.commands.auto.zeroAngleEncoders;
import frc.robot.commands.auto.zeroDriveEncoders;
import frc.robot.commands.auto.zeroWithAnalog;

/**
 * The chassis rotates in a circle command group
 * @author Madison J. 
 * @category AUTON 
 */
public class AutonInit extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
  * The chassis rotates in a circle command group
  */
  public AutonInit() {
    addCommands(
      new ParallelCommandGroup( 
        new zeroDriveEncoders(Robot.SwerveDrivetrain),
        new ConditionalCommand(
          new zeroWithAnalog(Robot.SwerveDrivetrain), 
          new zeroAngleEncoders(Robot.SwerveDrivetrain), 
          Robot.isCompRobot
          )
        ),
        new zeroAngleEncoders(Robot.SwerveDrivetrain)
        
    );
  }

 
}
