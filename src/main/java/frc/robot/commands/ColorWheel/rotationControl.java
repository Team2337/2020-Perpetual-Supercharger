package frc.robot.commands.ColorWheel;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;


/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class rotationControl extends CommandBase {
  private KickerWheel subsystem;
  public String colorValue;
  public int counter;

  /**
   * Sets the kicker's speed.
   * 
   * @param subsystem The subsystem used by this command. (Kicker)
   */
  public rotationControl(KickerWheel kickerWheel) {
    subsystem = kickerWheel;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    subsystem.setKickerSpeed(Robot.KickerWheel.getFutureSpeed(), Constants.KickerWheel.SHORTVELOCITYP);
  }

  @Override
  public void execute() {
    colorValue = Robot.ColorWheel.getColor();
    if (colorValue == "Yellow"){
        counter++;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return counter <= 6;
  }
}
