package frc.robot.commands.Serializer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;

public class CGreadyShooter extends SequentialCommandGroup{
    public CGreadyShooter() {
        addCommands(
            new resetSerializerPosition(Robot.Serializer).withTimeout(0.04),
            new backUpSerializer(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE)
        );
    }
}