package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.Shooter.*;


public class CGShooterBigBrother1 extends SequentialCommandGroup {


public CGShooterBigBrother1(Shooter shooter) {
    addCommands(
       new shooterShootABall(shooter,Constants.SHOOTSPEEDFAR),
       new shooterDoNothing(shooter).withTimeout(1),
       new shooterShoot5Balls(shooter, Constants.SHOOTSPEEDFAR)
    );

}
}