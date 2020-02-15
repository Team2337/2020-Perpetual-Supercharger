package frc.robot.commands.ShooterSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.backUpSerializer;
import frc.robot.commands.Shooter.startShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Ready the shooter system to fire from short distance
 * 
 * @author Sean Lynch
 */
public class shortShooterSystemOn extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public shortShooterSystemOn() {
        addCommands(
            new backUpSerializer(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE),
            new runKicker(Robot.KickerWheel, Constants.KICKERSPEED),
            new startShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE));
    }
}
