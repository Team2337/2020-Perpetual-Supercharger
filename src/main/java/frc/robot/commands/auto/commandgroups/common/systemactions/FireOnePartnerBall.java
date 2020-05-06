package frc.robot.commands.auto.commandgroups.common.systemactions;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.auto.autoBallCounter;;


/**
 * Nine ball auto
 * Runs the shooter for 1.5 seconds before intaking balls from our partners
 * 
 * @author Bryce G.
 */
public class FireOnePartnerBall extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public FireOnePartnerBall(int numOfPartners) {
        addCommands(
                new FirePreloads().withTimeout(2.75), 
                new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
                new ParallelRaceGroup(
                    new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED).withTimeout(6), //3.5 TODO: Fix MEEEEEEEEEEEEEEEEEEEEEEEEEEE
                    new autoBallCounter(Robot.OperatorAngleAdjustment, Robot.Serializer.middleSerializerSensor, 5, 3)
                ),
                new WaitCommand(0.4).withTimeout(0.4)
            );
        
    }
}