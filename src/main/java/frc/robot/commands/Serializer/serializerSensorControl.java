package frc.robot.commands.Serializer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Serializer;
// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class serializerSensorControl extends CommandBase {
    private Serializer serializer;
    private int i = 0;
    private int iteration = 0;
    private int position = 0;
    private int highestTrippedSensor;
    private boolean serializerActive;

    /**
     * Sets the kicker's speed.
     * 
     * @param kickerWheel The subsystem used by this command. (Kicker)
     */
    public serializerSensorControl(Serializer serializer) {
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
                /* if (i < 10) {
                    if(Robot.KickerWheel.getKickerSpeed() < 3500){
                        Robot.Serializer.setSerializerSpeed(Constants.SERIALIZERREVERSESPEED);
                    }
                } else if (i == 10){
                    Robot.Serializer.stopSerializer();
                } else if (i > 50 * 0.5) { */
                    Robot.Serializer.setSerializerSpeed(Constants.SERIALIZERDRIVERFORWARDSPEED);
                    Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSHOOTSPEED);
                /* }
                i++; */
            }
            // If the driver isn't attempting to control it and the operator is
        } else if (Robot.OI.operatorJoystick.triggerLeft.get()) {
            Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSPEED);
            if(serializer.bottomSerializerSensor.get() && !serializer.topSerializerSensor.get()) {
                //If the iteration count is less than 5, turn off the serializer.
                if(iteration < 5){
                    serializerActive = false;
                } //if the iteration count is equal to 5, check the highest trip sensor and activate serializer
                else if(iteration == 5) {
                    highestTrippedSensor = serializer.checkTopSensor(serializer.sensorsArray);
                    serializerActive = true;
                }

                iteration++;

                /*
                //Otherwise, if the iteration count is between 5 and 9, turn the serializer on
                 else if(iteration > 5 && iteration < 6) { 
                    // position = serializer.getSerializerPosition() + 7400;
                    // serializer.setPosition(position);
                } */

                //If the highest sensor or the one above it is tripped, stop the serializer
                if(highestTrippedSensor == serializer.sensorsArray.length || serializer.sensorsArray[highestTrippedSensor + 1].get()) {
                    serializerActive = false;
                    iteration = 0;
                }

                if(serializerActive) {
                    serializer.setSerializerSpeed(Constants.SERIALIZEROPERATORFORWARDSPEED);
                    Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSPEED);
                } else {
                    serializer.stopSerializer();
                    Robot.Agitator.stopAgitator();
                }

                /* if((Robot.Utilities.withinTolerance(position, serializer.getSerializerPosition(), 100)  && iteration > 39)
                || (iteration > 40) ||( !serializer.bottomSerializerSensor.get())
                ) {
                    iteration = 0;
                }
            } else if(serializer.topSerializerSensor.get() || !serializer.bottomSerializerSensor.get()) {
                serializer.stopSerializer(); 
            }   */
            }
            /* if(serializer.topSerializerSensor.get()){
                Robot.Agitator.stopAgitator();
            } */
            
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
