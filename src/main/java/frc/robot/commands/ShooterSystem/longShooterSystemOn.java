package frc.robot.commands.ShooterSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.adjustSerializer;
import frc.robot.commands.Shooter.startShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Ready the shooter system to fire from long distance
 * 
 * @author Sean Lynch
 */
public class longShooterSystemOn extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public longShooterSystemOn() {
        addCommands(
            new adjustSerializer(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE).withTimeout(1.4),
            new runKicker(Robot.KickerWheel, Constants.KICKERSPEED),
            new startShooter(Robot.Shooter, Constants.SHOOTSPEEDFAR));
    }
}
