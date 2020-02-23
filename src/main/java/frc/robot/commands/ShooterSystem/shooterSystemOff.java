package frc.robot.commands.ShooterSystem;

import frc.robot.Robot;
import frc.robot.commands.KickerWheel.holdKickerPosition;
import frc.robot.commands.KickerWheel.stopKicker;
import frc.robot.commands.Shooter.stopShooter;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Stop the shooter system
 * 
 * @author Sean Lynch
 */
public class shooterSystemOff extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public shooterSystemOff() {
        addCommands(
            new stopKicker(Robot.KickerWheel),
            //new holdKickerPosition(Robot.KickerWheel),
            new stopShooter(Robot.Shooter));
    }
}
