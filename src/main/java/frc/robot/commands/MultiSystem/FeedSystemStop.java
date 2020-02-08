package frc.robot.commands.MultiSystem;

import frc.robot.Robot;
import frc.robot.commands.Agitator.stopAgitator;
import frc.robot.commands.Serializer.stopSerializer;
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
        new stopSerializer(Robot.Serializer),
        new stopAgitator(Robot.Agitator)
 );
}

}
