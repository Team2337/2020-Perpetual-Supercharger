package frc.robot.commands.Serializer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private boolean runMode = false;
    private boolean backSensorState = false;

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
        SmartDashboard.putNumber("Check Top Sensor", serializer.checkTopSensor());
        SmartDashboard.putNumber("Highest tripped sensor", highestTrippedSensor);
        SmartDashboard.putNumber("*Serializer iterations", iteration);
        SmartDashboard.putBoolean("*Serializer active", serializerActive);
        // The driver takes priority
        if (Robot.OI.driverJoystick.triggerRight.get()) {
            if (Robot.Shooter.shooterAtVelocity) { 
                    Robot.Serializer.setSerializerSpeed(Constants.SERIALIZERDRIVERFORWARDSPEED);
                    Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSHOOTSPEED);

            }
            // If the driver isn't attempting to control it and the operator is
        } else if (Robot.OI.operatorJoystick.triggerLeft.get()) {
            Robot.Agitator.setAgitatorSpeed(Constants.AGITATORSPEED);
            if(serializer.getTopTopSensor()) {
                runMode = false;
                iteration = 0;
                Robot.Agitator.stopAgitator();
                serializer.stopSerializer();
            } else {
                if(serializer.getBottomSensor() && !runMode) {
                    if(iteration > 1) {
                        if(serializer.noSensorsTripped()) {
                            highestTrippedSensor = 0;
                        } else {
                            backSensorState = serializer.getBottomBackSensor();
                            runMode = true;
                        }
                    }
                    iteration++;
                }
                if(runMode) {
                    serializer.setSerializerSpeed(Constants.SERIALIZEROPERATORFORWARDSPEED);
                    if(serializer.getBottomBackSensor() != backSensorState) {
                        runMode = false;
                        iteration = 0;
                        backSensorState = false;
                    }
                } else {
                    serializer.stopSerializer();
                    Robot.Agitator.stopAgitator();
                }
            }/* else if(!serializer.bottomSerializerSensor.get()) {
                iteration = 0;
            } */
                

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
