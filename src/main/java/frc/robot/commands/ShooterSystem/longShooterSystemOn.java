package frc.robot.commands.ShooterSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.readyShooter;
import frc.robot.commands.Shooter.startShooter;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Ready the shooter system to fire from long distance
 * 
 * @author Sean Lynch
 */
public class longShooterSystemOn extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public longShooterSystemOn() {
        addCommands(
            //new readyShooter(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE),
            new runKicker(Robot.KickerWheel, Constants.KICKERSPEED),
            new startShooter(Robot.Shooter, Constants.SHOOTSPEEDFAR));
    }
}
