package frc.robot.commands.KickerWheel;

import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * A command that sets the kicker position using the Kicker subsystem.
 */
public class holdKickerPosition extends InstantCommand {
  private KickerWheel subsystem;

  /**
   * Sets the kicker's position to what it currently is set to.
   * 
   * @param subsystem The subsystem used by this command. (Kicker)
   */
  public holdKickerPosition(KickerWheel kickerWheel) {
    subsystem = kickerWheel;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 

    subsystem.setKickerPosition(subsystem.getKickerPosition());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
