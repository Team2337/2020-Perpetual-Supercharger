package frc.robot.commands.KickerWheel;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class runKicker extends InstantCommand {
  private KickerWheel subsystem;

  /**
   * Sets the kicker's speed.
   * 
   * @param subsystem The subsystem used by this command. (Kicker)
   */
  public runKicker(KickerWheel kickerWheel) {
    subsystem = kickerWheel;
    

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    subsystem.setKickerSpeed(Robot.KickerWheel.getFutureSpeed(), Constants.KickerWheel.SHORTVELOCITYP);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
