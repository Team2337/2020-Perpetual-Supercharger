package frc.robot.commands.ShooterSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Serializer.runSerializer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Move balls towards the shooter
 * @author Michael F., Bryce G.
 */
public class feedSystemForward extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public feedSystemForward() {
        addCommands(
            new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
            new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED));
    }
}
