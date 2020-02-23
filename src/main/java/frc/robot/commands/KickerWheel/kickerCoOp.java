package frc.robot.commands.KickerWheel;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class kickerCoOp extends CommandBase {
    private KickerWheel kickerWheel;

    private boolean positionSet = false;
    private boolean speedSet = false;

    /**
     * Sets the kicker's speed.
     * 
     * @param kickerWheel The subsystem used by this command. (Kicker)
     */
    public kickerCoOp(KickerWheel kickerWheel) {
        this.kickerWheel = kickerWheel;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(kickerWheel);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // The driver takes priority
        /*  if (Robot.OI.driverJoystick.triggerRight.get()) {
            if(!speedSet){
                // Turn the kicker on for when the driver wants to shoot
                 kickerWheel.setKickerSpeed(kickerWheel.getFutureSpeed(), Constants.KickerWheel.SHORTVELOCITYP);
                speedSet = true;
                positionSet = false;
            }
        } else {
            // If the driver isn't trying to control the kicker wheel, stop it and hold position
            if(!positionSet){
                 kickerWheel.setKickerPosition(kickerWheel.getKickerPosition());
                positionSet = true;
                speedSet = false;
            }
        }  */
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
}
