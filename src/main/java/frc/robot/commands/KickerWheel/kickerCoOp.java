package frc.robot.commands.KickerWheel;

import frc.robot.Constants;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class kickerCoOp extends CommandBase {
    private KickerWheel kickerWheel;

    /**
     * Sets the kicker's speed.
     * 
     * @param kickerWheel The subsystem used by this command. (Kicker)
     */
    public kickerCoOp(KickerWheel kickerWheel) {
        this.kickerWheel = kickerWheel;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(kickerWheel);
        SmartDashboard.putBoolean("KICKER COMMAND ACTIVE", true);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if (kickerWheel.operatorIsControlling && kickerWheel.driverIsControlling) {
            // Set the kicker to hold it's position
            kickerWheel.setKickerPosition(kickerWheel.getKickerPosition());
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // The driver takes priority
        if (kickerWheel.driverIsControlling) {
            // Turn the kicker on for when the operator wants to use it
            kickerWheel.setKickerSpeed(Constants.KICKERSPEED);
            // If the driver isn't attempting to control it and the operator is
        } else if (!kickerWheel.operatorIsControlling) {
            // If no-one is trying to control the kicker wheel, stop it
            kickerWheel.stopKicker();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("KICKER COMMAND ACTIVE", false);
    }
}
