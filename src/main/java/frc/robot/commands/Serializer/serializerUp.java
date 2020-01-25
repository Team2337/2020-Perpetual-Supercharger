package frc.robot.commands.Serializer;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 *  @author Hunter B. & John R. 
**/
public class serializerUp extends CommandBase {

    // Feeds the cargo intake to the shooter
	public serializerUp() {
    // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Robot.SerializerElevator);
	}
  
    // Called when the command is initially scheduled.
	@Override
    public void initialize(){
        Robot.SerializerElevator.setSerializerSpeed(1);

    }
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute(){

    }
    // Called once the command ends or is interrupted.
    @Override 
    public void end(boolean interrupted){
        Robot.SerializerElevator.setSerializerSpeed(0);
    }
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }

}
