package frc.robot.commands.ShooterSystem;

import frc.robot.Robot;
import frc.robot.commands.Agitator.stopAgitator;
import frc.robot.commands.Serializer.stopSerializer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * Stops the movement of balls in the robot
 * 
 * @author Nicholas Stokes
 */
public class feedSystemStop extends ParallelCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public feedSystemStop() {
        addCommands(
            new stopSerializer(Robot.Serializer),
            new stopAgitator(Robot.Agitator));
    }
}
