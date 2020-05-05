package frc.robot.commands.Serializer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Serializer;
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
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // The driver takes priority
        if (Robot.OI.driverJoystick.triggerRight.get()) {
            if (Robot.Shooter.shooterAtVelocity) { 
                    Robot.Serializer.setSerializerSpeed(Constants.SERIALIZERDRIVERFORWARDSPEED);
                    Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSHOOTSPEED);
            }
            // If the driver isn't attempting to control it and the operator is
        } else if (Robot.OI.operatorJoystick.triggerLeft.get()) {
            Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSPEED);

            if(Robot.Serializer.topSerializerSensor.get() && Robot.Serializer.bottomSerializerSensor.get()) {
                Robot.Agitator.stopAgitator();
            }
            // Set the kicker to hold it's position (done in the kicker subsystem)
            if (serializer.topSerializerSensor.get()) {
                serializer.stopSerializer();
                Robot.Agitator.stopAgitator();
            } else if (serializer.bottomSerializerSensor.get()) {
                serializer.setSerializerSpeed(Constants.SERIALIZEROPERATORFORWARDSPEED);
            } else {
                serializer.stopSerializer();
            }
        } else {
            // If no-one is trying to control the kicker wheel, stop it
            serializer.stopSerializer();
            Robot.Agitator.stopAgitator();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
}
