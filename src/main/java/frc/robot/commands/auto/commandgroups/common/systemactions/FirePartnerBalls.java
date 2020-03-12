package frc.robot.commands.auto.commandgroups.common.systemactions;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Serializer.runSerializer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Nine ball auto
 * Runs the shooter for 3.2 seconds before intaking balls from our partners
 * 
 * @author Bryce G.
 */
public class FirePartnerBalls extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public FirePartnerBalls(int numOfPartners) {
        addCommands(
                new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
                new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
                new FirePreloads().withTimeout(3.2),
                new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED).withTimeout(9.5) //10
                );
        
    }
}