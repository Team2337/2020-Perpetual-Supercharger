package frc.robot.commands.auto.commandgroups.common.systemactions;

import frc.robot.Robot;
import frc.robot.commands.Shooter.stopShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Nine ball auto
 * Runs the shooter for 1.5 seconds before intaking balls from our partners
 * 
 * @author Bryce G.
 */
public class FirePartnerBalls extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public FirePartnerBalls(int numOfPartners) {
        addCommands(new FirePreloads().withTimeout(7),
                new stopShooter(Robot.Shooter));
        
    }
}