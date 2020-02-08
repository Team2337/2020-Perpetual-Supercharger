package frc.robot.commands.MultiSystem;

import frc.robot.Robot;
import frc.robot.commands.Serializer.stopSerializerMotor;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Holds the serializer position
 * 
 * @author Nicholas Stokes
 */
public class FeedSystemStop extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })



public FeedSystemStop() {

    addCommands(
        new stopSerializerMotor(Robot.Serializer)

        
    
    
 );
}

}
