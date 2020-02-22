package frc.robot.commands.Serializer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Agitator;
import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class serializerCoOp extends CommandBase {
    private Serializer serializer;
    private int i = 0;

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
            if (Robot.Shooter.getAvgRPM() > Robot.Shooter.getTargetSpeed() * 0.9) {
                if (i < 10) {
                    if(Robot.KickerWheel.getKickerSpeed() < 3500){
                        Robot.Serializer.setSerializerSpeed(Constants.SERIALIZERREVERSESPEED);
                    }
                } else if (i == 10){
                    Robot.Serializer.stopSerializer();
                } else if (i > 50 * 0.5) {
                    Robot.Serializer.setSerializerSpeed(Constants.SERIALIZERFORWARDSPEED);
                    Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSPEED);
                }
                i++;
            }
            // If the driver isn't attempting to control it and the operator is
        } else if (Robot.OI.operatorJoystick.triggerLeft.get()) {
            // Set the kicker to hold it's position (done in the kicker subsystem)
            if (serializer.topSerializerSensor.get() && serializer.middleSerializerSensor.get()) {
                serializer.stopSerializer();
                Robot.Agitator.stopAgitator();
            } else if (serializer.bottomSerializerSensor.get()) {
                serializer.setSerializerSpeed(Constants.SERIALIZERFORWARDSPEED);
                Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSPEED);
            } else {
                serializer.stopSerializer();
                Robot.Agitator.stopAgitator();
            }
        } else {
            // If no-one is trying to control the kicker wheel, stop it
            serializer.stopSerializer();
            Robot.Agitator.stopAgitator();
            //Also, the speed checking iterations would need to be reset
            i = 0;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
}
