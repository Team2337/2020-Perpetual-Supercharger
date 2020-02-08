package frc.robot.commands.KickerWheel;

import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * A command that decreases the kicker speed using the Kicker subsystem.
 */
public class adjustKickerSpeed extends InstantCommand {
  public double kspeed;
  private KickerWheel subsystem;

  /**
   * Decreases the kicker's speed.
   * @param subsystem The subsystem used by this command.
   */
  public adjustKickerSpeed(KickerWheel kickerWheel, double kickerspeed) {
    subsystem = kickerWheel;
    kspeed = kickerspeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 

    subsystem.adjustKickerSpeed(kspeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
