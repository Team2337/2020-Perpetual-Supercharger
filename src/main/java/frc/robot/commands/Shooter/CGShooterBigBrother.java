package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shooter;


/**
 * Command group that runs the shooter to shoot a certain number of balls at a time
 */
public class CGShooterBigBrother extends SequentialCommandGroup {

/**
 * Command group that runs the shooter in a specific order, so that it shoots 1 ball, waits, then proceeds
 * to shoot 5 more unless the shoot trigger is released.
 * @param subsystem The shooter subsystem
 * @param velocity The velocity to be shot at.
 */
public CGShooterBigBrother(Shooter subsystem, double velocity) {
    addCommands(
        new shooterReset(subsystem),
        new shooterShoot1Ball(subsystem,velocity),
        new shooterReset(subsystem),
        new shooterDoNothing(subsystem).withTimeout(1),
        new shooterShoot5Balls(subsystem, velocity)
    );

}
}