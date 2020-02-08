package frc.robot.commands.MultiSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Serializer.setSerializerSpeed;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Holds the serializer position
 * 
 * @author Nicholas Stokes
 */
public class FeedSystemReverse extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })



public FeedSystemReverse() {

    addCommands(
        new setSerializerSpeed(Robot.Serializer, Constants.SERIALIZERREVERSESPEED)

        
    
    
 );
}

}
