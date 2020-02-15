package frc.robot.commands.Serializer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;

public class CGSerializerReadyOrphan extends SequentialCommandGroup {
    public CGSerializerReadyOrphan(double speed) {
        addCommands(
            new SequentialCommandGroup(
            new resetSerializerPosition(Robot.Serializer).withTimeout(0.04),
            new backUpSerializer(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE),
            new runKicker(Robot.KickerWheel, speed)
            ),
            new resetCounter(Robot.Serializer),
            new runSerializerFor1Ball(Robot.Serializer,speed),
            new serializerDoNothing(Robot.Serializer).withTimeout(3),
            new resetCounter(Robot.Serializer),
            new runSerializerFor5Balls(Robot.Serializer,speed),
            new serializerDoNothing(Robot.Serializer).withTimeout(5)
        );
    }
}