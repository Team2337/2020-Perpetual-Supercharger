package frc.robot.commands.ShooterSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.backUpSerializer;
import frc.robot.commands.Shooter.startShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Ready the shooter system to fire from long distance
 * 
 * @author Team2337
 */
public class shooterSystemOn extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public shooterSystemOn() {
        addCommands(
            new runKicker(Robot.KickerWheel),
            new startShooter(Robot.Shooter));
    }
}
