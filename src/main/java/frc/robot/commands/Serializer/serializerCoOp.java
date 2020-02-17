package frc.robot.commands.Serializer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class serializerCoOp extends CommandBase {
    private Serializer serializer;

    /**
     * Sets the kicker's speed.
     * 
     * @param kickerWheel The subsystem used by this command. (Kicker)
     */
    public serializerCoOp(Serializer serializer) {
        this.serializer = serializer;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(serializer);
        SmartDashboard.putBoolean("SERIALIZER COMMAND ACTIVE", true);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // The driver takes priority
        if (serializer.driverIsControlling) {
            if (Robot.Utilities.withinTolerance(Constants.SHOOTSPEEDFAR, Robot.Shooter.getAvgRPM(), 100)) {
                if (Robot.Utilities.withinTolerance(Constants.KICKERSPEED, Robot.KickerWheel.getKickerSpeed(), 100)) {
                    Robot.Serializer.setSerializerSpeed(Constants.SERIALIZERFORWARDSPEED);
                }
            }
            // If the driver isn't attempting to control it and the operator is
        } else if (serializer.operatorIsControlling) {
            // Set the kicker to hold it's position
            serializer.setSerializerSpeed(Constants.SERIALIZERFORWARDSPEED);
        } else {
            // If no-one is trying to control the kicker wheel, stop it
            serializer.stopSerializer();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("SERIALIZER COMMAND ACTIVE", false);
    }
}
