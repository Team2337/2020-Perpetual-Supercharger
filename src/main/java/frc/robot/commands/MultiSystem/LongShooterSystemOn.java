package frc.robot.commands.MultiSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.readyShooter;
import frc.robot.commands.Shooter.startShooter;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Holds the serializer position
 * 
 * @author 
 */
public class LongShooterSystemOn extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })



public LongShooterSystemOn() {

    addCommands(
        new readyShooter(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE),
        new runKicker(Robot.KickerWheel, Constants.KICKERSPEED),
        new startShooter(Robot.Shooter, Constants.SHOOTSPEEDFAR)
 );
}

}
