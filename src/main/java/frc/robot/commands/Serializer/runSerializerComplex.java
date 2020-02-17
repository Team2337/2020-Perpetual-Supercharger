package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the serializer speed
 * @author Nicholas S
 */
public class runSerializerComplex extends CommandBase {

  private final Serializer subsystem;
  private double serializerSpeed;
  public double resetti;
  public boolean sensorValue;

  /**
   * Sets the serializer speed
   * 
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

    // This will set the serializer to run at a set speed
    // serializerSpeed = SmartDashboard.getNumber("Serializer Speed", Constants.SERIALIZERPEAKSPEED);
    sensorValue = false;
    resetti = 0;

    
  }

  @Override
  public void execute() {
    if (sensorValue = true){

      if (resetti >= 50*0.25) {
        resetti = 0;
        sensorValue = false;
      } 
      else {
        resetti++;
        subsystem.stopSerializer();
      } 
    }
    if (subsystem.sensor.get() == false){
      subsystem.setSerializerSpeed(serializerSpeed);
    }
    if (subsystem.sensor.get() == true){
      sensorValue = true;
        }
      SmartDashboard.putNumber("Resetti", resetti);
      SmartDashboard.putBoolean("SensorValue", sensorValue);
      }

      
      
      

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
  
}
