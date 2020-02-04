package frc.robot.commands.TimeOfFlight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CanbusDistanceSensor;

public class FindSensor extends Command {
  private int myStartId;
  private int foundId;
  private int currentId;
  private int highestId = 32;
  private int[] temp;

  public FindSensor(int startId) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    myStartId = startId;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    currentId = myStartId;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    foundId = CanbusDistanceSensor.findSensor(currentId);

    /*if(foundId != 999) {*/

      temp = CanbusDistanceSensor.getSensorInfo(CanbusDistanceSensor.hwdata);
      SmartDashboard.putNumber("FoundSerial", temp[0]);
      SmartDashboard.putNumber("SensorFound", currentId);
    /*} else {
      currentId++;
    }*/
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return foundId != 999 || currentId > highestId;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
