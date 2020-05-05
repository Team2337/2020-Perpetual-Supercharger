package frc.robot.commands.Serializer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class serializerBallControl extends CommandBase {
    private Serializer serializer;
    private int iteration = 0;
    private int position = 0;

    /**
     * Sets the kicker's speed.
     * 
     * @param kickerWheel The subsystem used by this command. (Kicker)
     */
    public serializerBallControl(Serializer serializer) {
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
            if(serializer.bottomSerializerSensor.get() && !serializer.topSerializerSensor.get()) {
                if(iteration > 5 && iteration < 9) { 
                    position = serializer.getSerializerPosition() + 7400;
                    serializer.setPosition(position);
                }
                iteration++;

                if((Robot.Utilities.withinTolerance(position, serializer.getSerializerPosition(), 100)  && iteration > 39)
                || (iteration > 40) ||( !serializer.bottomSerializerSensor.get())
                ) {
                    iteration = 0;
                }
            } else if(serializer.topSerializerSensor.get() || !serializer.bottomSerializerSensor.get()) {
                serializer.stopSerializer(); 
            }  
            if(serializer.topSerializerSensor.get()){
                Robot.Agitator.stopAgitator();
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
