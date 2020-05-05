package frc.robot.commands.auto.commandgroups.common.systemactions;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Serializer.runSerializer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Runs the shooter for 3.2 seconds before intaking power cells from our partners
 * The power cells are then shot after all three of ours have been shot, 
 * preventing the possibility of shooting more than 5 at once
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