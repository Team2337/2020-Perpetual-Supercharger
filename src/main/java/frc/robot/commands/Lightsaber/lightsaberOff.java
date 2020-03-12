package frc.robot.commands.Lightsaber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Shooter.stopShooter;
import frc.robot.commands.auto.checkShooterVelocity;
import frc.robot.commands.auto.commandgroups.common.systemactions.*;

/**
 * Turns the lightsaber on and off 3 times so that when it turns on it turns on in the same mode
 * @author Madison J. 
 * @category Lightsaber 
 */
public class lightsaberOff extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

 /**
 * Turns the lightsaber on and off 3 times so that when it turns on it turns on in the same mode
 */
  public lightsaberOff() {
    addCommands(
    new turnOffLightsaber(Robot.Lightsaber),
    new WaitCommand(0.05).withTimeout(0.05),
    new turnOnLightsaber(Robot.Lightsaber),
    new WaitCommand(0.05).withTimeout(0.05),
    new turnOffLightsaber(Robot.Lightsaber),
    new WaitCommand(0.05).withTimeout(0.05),
    new turnOnLightsaber(Robot.Lightsaber),
    new WaitCommand(0.05).withTimeout(0.05),
    new turnOffLightsaber(Robot.Lightsaber)
    );
  
  }

 
}
