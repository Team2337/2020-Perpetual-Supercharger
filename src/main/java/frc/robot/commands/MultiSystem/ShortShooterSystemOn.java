package frc.robot.commands.MultiSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Serializer.readyShooter;
import frc.robot.commands.Shooter.startShooter;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Holds the serializer position
 * 
 * @author
 */
public class ShortShooterSystemOn extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })



public ShortShooterSystemOn() {

    addCommands(
        new readyShooter(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE),
        new startShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE)

                //TODO: Add kicker
    
    
 );
}

}
