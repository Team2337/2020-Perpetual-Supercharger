package frc.robot.commands.ShooterSystem;

import frc.robot.Robot;
import frc.robot.commands.Shooter.startShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Turns the shooter and kicker on to their designated velocities, as decided by
 * the mode that is queued by the operator
 * @see OperatorAngleAdjustment
 * 
 * @author Michael F., Bryce G. 
 */
public class shooterSystemOn extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public shooterSystemOn() {
        addCommands(
            new startShooter(Robot.Shooter));
    }
}
