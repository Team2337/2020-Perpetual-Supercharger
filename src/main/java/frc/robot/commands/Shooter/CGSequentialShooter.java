package frc.robot.commands.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shooter;

/**
* @author Nicholas Stokes
* This is a command group to run these commands in order.
* For what the commands do, go to their code
*/

public class CGSequentialShooter extends SequentialCommandGroup {

    public CGSequentialShooter(Shooter shooter, double speed) {
          
        addCommands(
        new shootSingleBall(shooter, speed),   
        new shooterDoNothing(shooter).withTimeout(2),
        new shootContinuously(shooter, speed)

        );
    }
}