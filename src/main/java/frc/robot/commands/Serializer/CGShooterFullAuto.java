package frc.robot.commands.Serializer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class CGShooterFullAuto extends SequentialCommandGroup {
   
    public CGShooterFullAuto() {
        addCommands(
            new resetCounter(Robot.Serializer),
            new checkKickerIfUpToSpeed(Robot.Serializer),
            new checkShooterIfUpToSpeed(Robot.Serializer)

    );
      

    }
}