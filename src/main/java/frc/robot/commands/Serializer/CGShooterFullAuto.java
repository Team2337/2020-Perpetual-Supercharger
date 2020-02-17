package frc.robot.commands.Serializer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class CGShooterFullAuto extends SequentialCommandGroup {
   
    /**
     * Runs the serializer so that the shooter will shoot balls as fast as is accurate
     */
    public CGShooterFullAuto(double kickerTarget, int shooterTarget) {

        addCommands(
            new resetCounter(Robot.Serializer),
            new checkIfKickerUpToSpeed(Robot.KickerWheel, kickerTarget),
            new checkIfShooterUpToSpeed(Robot.Shooter, shooterTarget),
            new onlyMoveOneBall(Robot.Serializer, -1024),
            
            new commandGroupDoNothing(Robot.Serializer)
        );

    }
}