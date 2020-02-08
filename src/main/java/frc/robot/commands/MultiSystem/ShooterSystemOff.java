package frc.robot.commands.MultiSystem;

import frc.robot.Robot;
import frc.robot.commands.KickerWheel.stopKicker;
import frc.robot.commands.Shooter.stopShooter;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Holds the serializer position
 * 
 * @author 
 */
public class ShooterSystemOff extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })



public ShooterSystemOff() {

    addCommands(
        new stopShooter(Robot.Shooter),
        new stopKicker(Robot.KickerWheel)
 );
}

}
