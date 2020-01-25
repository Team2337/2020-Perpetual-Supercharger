package frc.robot.commands.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shooter;

/**
* @author Nicholas Stokes
* This is a command group to run these commands in order.
* For what the commands do, go to their code
*/

public class CGSequentialShooter extends SequentialCommandGroup {

    public CGSequentialShooter(Shooter shooter) {
          
        addCommands(
        new shootSingleBall(shooter, 21000),   
        new shooterDoNothing(shooter).withTimeout(2),
        new shootContinuously(shooter, 21000)

        );
    }
}