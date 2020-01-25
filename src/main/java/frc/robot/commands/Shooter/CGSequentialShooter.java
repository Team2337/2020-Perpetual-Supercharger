import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shooter;


public class CGSequentialShooter extends SequentialCommandGroup {

    public CGSequentialShooter(Shooter shooter) {
          
        addCommands(
        shooterWait
        );
    }
}