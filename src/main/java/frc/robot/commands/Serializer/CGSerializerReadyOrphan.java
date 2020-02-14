package frc.robot.commands.Serializer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class CGSerializerReadyOrphan extends SequentialCommandGroup {
    public CGSerializerReadyOrphan(double speed) {
        addCommands(
            new resetCounter(Robot.Serializer),
            new runSerializerFor1Ball(Robot.Serializer,speed),
            new serializerDoNothing(Robot.Serializer).withTimeout(3),
            new resetCounter(Robot.Serializer),
            new runSerializerFor5Balls(Robot.Serializer,speed),
            new serializerDoNothing(Robot.Serializer).withTimeout(5)
        );
    }
}