package frc.robot.commands.MultiSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Serializer.runSerializer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Holds the serializer position
 * 
 * @author 
 */
public class FeedSystemReverse extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })



public FeedSystemReverse() {

    addCommands(
        new runSerializer(Robot.Serializer, Constants.SERIALIZERREVERSESPEED),
        new runAgitator(Robot.Agitator, Constants.AGITATORREVERSESPEED)
    
 );
}

}
