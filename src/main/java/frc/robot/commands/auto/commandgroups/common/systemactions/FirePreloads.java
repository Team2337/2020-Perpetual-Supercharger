package frc.robot.commands.auto.commandgroups.common.systemactions;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Agitator.runAgitator;
import frc.robot.commands.Intake.runIntake;
import frc.robot.commands.KickerWheel.runKicker;
import frc.robot.commands.Serializer.runSerializer;
import frc.robot.commands.auto.AutoResetRampRate;
import frc.robot.commands.auto.autoShooterAtSpeed;
import frc.robot.commands.auto.autoStartShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Shoots the preloaded power cells into the inner/outer ports
 * 
 * @author Bryce G.
 * @category AUTON
 */
public class FirePreloads extends SequentialCommandGroup {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public FirePreloads() {
        addCommands(
            new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED),
            new runKicker(Robot.KickerWheel),
            new autoStartShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE),
            new WaitCommand(0.2).withTimeout(0.2), 
            new AutoResetRampRate(Robot.OperatorAngleAdjustment).withTimeout(0.1),
            new autoShooterAtSpeed(Robot.OperatorAngleAdjustment),
            new runAgitator(Robot.Agitator, Constants.AGITATORSPEED),
            new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED).withTimeout(3)
        );
    }
}