package frc.robot.commands.ShooterSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Serializer.runSerializer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Moves balls downwards, away from the shooter
 * @author Sean Lynch
 */
public class feedSystemReverse extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public feedSystemReverse() {
        addCommands(
            new runSerializer(Robot.Serializer, Constants.SERIALIZERREVERSESPEED),
            new runAgitator(Robot.Agitator, Constants.AGITATORREVERSESPEED));
    }
}
