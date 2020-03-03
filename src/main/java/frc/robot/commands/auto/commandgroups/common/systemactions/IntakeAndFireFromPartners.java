package frc.robot.commands.auto.commandgroups.common.systemactions;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Serializer.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Nine ball auto
 * Runs the shooter for 1.5 seconds before intaking balls from our partners
 * 
 * @author Bryce G.
 */
public class IntakeAndFireFromPartners extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public IntakeAndFireFromPartners(double timeout) {
        addCommands(
            new WaitCommand(0.5),
            new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED), 
            new runSerializer(Robot.Serializer, Constants.SERIALIZERFORWARDSPEED),
            new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
            new WaitCommand(timeout).withTimeout(timeout)
        );
    }
}