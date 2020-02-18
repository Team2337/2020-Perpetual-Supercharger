package frc.robot.commands.auton;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.runSerializer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Nine ball auto
 * Runs the shooter for 1.5 seconds before intaking balls from our partners
 * 
 * @author Bryce G.
 */
public class autoShooterSystemOn extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public autoShooterSystemOn() {
        addCommands(
            new runKicker(Robot.KickerWheel),
            new autoStartShooter(Robot.Shooter),
            new WaitCommand(0.2).withTimeout(0.2), 
            new autoResetRampRate(Robot.OperatorAngleAdjustment),
            new WaitCommand(1.3).withTimeout(1.3), 
            new runSerializer(Robot.Serializer, Constants.SERIALIZERFORWARDSPEED), 
            new runAgitator(Robot.Agitator, Constants.AGITATORFORWARDSPEED),
            new WaitCommand(1.5).withTimeout(1.5), 
            new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED), 
            new WaitCommand(10).withTimeout(10)
        );
    }
}
