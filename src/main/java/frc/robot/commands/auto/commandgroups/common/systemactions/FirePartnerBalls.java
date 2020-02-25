package frc.robot.commands.auto.commandgroups.common.systemactions;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.Shooter.stopShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Nine ball auto
 * Runs the shooter for 1.5 seconds before intaking balls from our partners
 * 
 * @author Bryce G.
 */
public class FirePartnerBalls extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public FirePartnerBalls(int numOfPartners) {
        addCommands(new FirePreloads().withTimeout(3.2),
                new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
                new runSerializer(Robot.Serializer, Constants.SERIALIZERFORWARDSPEED),
                new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
                new WaitCommand(numOfPartners * 3).withTimeout(numOfPartners * 3),
                new stopShooter(Robot.Shooter)
                );
        
    }
}