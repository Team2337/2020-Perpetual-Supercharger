package frc.robot.commands.Serializer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.KickerWheel.runKickerOrphan;

public class CGSerializerReadyOrphan extends ParallelCommandGroup {
    public CGSerializerReadyOrphan(double serializerSpeed, int kickerSpeed) {
        addCommands(
                // Backs up serializer
                new SequentialCommandGroup(
                    new resetSerializerPosition(Robot.Serializer).withTimeout(0.04),
                        new backUpSerializer(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE),
                        new serializerDoNothing(Robot.Serializer).withTimeout(3)
                        ),
                        
                // Runs Kicker Wheel
                new runKickerOrphan(Robot.KickerWheel, kickerSpeed));
              /* new SequentialCommandGroup(
                        // Runs Serializer for 1 ball
                        new resetCounter(Robot.Serializer)));
                       /* new runSerializerFor1Ball(Robot.Serializer, serializerSpeed),
                        new serializerDoNothing(Robot.Serializer).withTimeout(3),
                        // Runs Serializer for 5 balls
                        new resetCounter(Robot.Serializer),
                        new runSerializerFor5Balls(Robot.Serializer, serializerSpeed),
                        new serializerDoNothing(Robot.Serializer).withTimeout(5)));
                        */
                        
    }
}