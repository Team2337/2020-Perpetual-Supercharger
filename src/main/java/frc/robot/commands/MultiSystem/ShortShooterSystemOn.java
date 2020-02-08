package frc.robot.commands.MultiSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.readyShooter;
import frc.robot.commands.Shooter.startShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Holds the serializer position
 * 
 * @author
 */
public class ShortShooterSystemOn extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })



public ShortShooterSystemOn() {

    addCommands(
        new readyShooter(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE),
        new runKicker(Robot.KickerWheel, Constants.KICKERSPEED),
        new startShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE)
 );
}

}
