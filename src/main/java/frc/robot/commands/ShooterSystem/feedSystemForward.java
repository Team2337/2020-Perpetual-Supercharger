package frc.robot.commands.ShooterSystem;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.KickerWheel.holdKickerPosition;
import frc.robot.commands.Serializer.runSerializer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Move balls towards the shooter
 * 
 * @author Sean Lynch
 */
public class feedSystemForward extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public feedSystemForward() {
        addCommands(
            //new holdKickerPosition(Robot.KickerWheel),
            new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
            new runSerializer(Robot.Serializer, Constants.SERIALIZERFORWARDSPEED));
    }
}
