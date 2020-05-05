package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.TimeOfFlight;

/**
 * Finds the sensor and returns the ID
 */
public class findSensor extends CommandBase {
  private int foundId;
  private int currentId;
  private int highestId = 32;
  private int[] temp;

  /**
   * Finds the sensor and returns the ID. It then proceeds to put this on the SmartDashboard.
   * Possibly useful if we have more than one sensor, but otherwise can be ignored.
   */
  public findSensor() {
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    //Uses the findSensor method to find if the sensor is there
    foundId = TimeOfFlight.findSensor(currentId);
    //Checks if the sensor was found
    if(foundId != 999) {
      //If the sensor was found
      temp = TimeOfFlight.getSensorInfo(TimeOfFlight.hwdata);
      if(TimeOfFlight.tofDebug){
        SmartDashboard.putNumber("FoundSerial", temp[0]);
        SmartDashboard.putNumber("SensorFound", currentId);
      }
      
    } else {
      //Raise the id being checked
      currentId++;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return foundId != 999 || currentId > highestId;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {

  }
}
