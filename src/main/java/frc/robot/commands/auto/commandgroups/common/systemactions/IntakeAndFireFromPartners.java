package frc.robot.commands.auto.commandgroups.common.systemactions;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.Serializer.runSerializer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Nine ball auto
 * Waits for 0.5 seconds before running the intakes, serializer, and agitator
 * 
 * @author Bryce G.
 */
public class IntakeAndFireFromPartners extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public IntakeAndFireFromPartners(double timeout) {
        addCommands(
            new WaitCommand(0.5),
            new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED), 
            new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED),
            new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
            new WaitCommand(timeout).withTimeout(timeout)
        );
    }
}