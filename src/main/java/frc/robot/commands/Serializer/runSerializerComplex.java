package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Runs the serializer forever in a single command
 * @author Nicholas S
 */
public class runSerializerComplex extends CommandBase {

  private final Serializer subsystem;
  private double serializerSpeed;
  public double resetti;
  public boolean bottomSensorValue;
  public boolean topSensorValue;

  /**
   * Runs the serializer forever in a single command 
   * @author Nicholas Stokes
   * @param serializer The subsystem used by this command. (Serializer)
   * @param speed      A double number that sets what speed the motors move at
   */
  public runSerializerComplex(Serializer serializer, double speed) {

    subsystem = serializer;
    serializerSpeed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Sets up the reset value and the sensorValue boolean needed for the execute command
    bottomSensorValue = false;
    topSensorValue = false;
    resetti = 0;

  }

  @Override
  public void execute() {
    /*
     * This command is pretty simple but also complex. If the sensor value is true,
     * the serializer runs until it turns false which then flips a boolean to true,
     * which then locks the command inside a wait, After 0.01 second, the boolean
     * turns false and then the cycle continues.
     */
    if ((bottomSensorValue == true) && (topSensorValue == true)) {
        subsystem.stopSerializer();
      }
     else {
      if (subsystem.bottomSerializerSensor.get() == true) {
        subsystem.setSerializerSpeed(serializerSpeed);
        bottomSensorValue = true;
      } else if (subsystem.bottomSerializerSensor.get() == false) {
        subsystem.stopSerializer();
      }
        else if (subsystem.topSerializerSensor.get() == false) {
          subsystem.stopSerializer();
        }
        else if (subsystem.bottomSerializerSensor.get() == true) {
          topSensorValue = true;
        
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stopSerializer();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
