package frc.robot.commands.KickerWheel;


import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class increaseKickerSpeed extends CommandBase {
  public double kspeed;
  private KickerWheel subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public increaseKickerSpeed(KickerWheel kickerWheel, double kickerspeed) {
    subsystem = kickerWheel;
    kspeed = kickerspeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 

    subsystem.decreaseKickerSpeed(kspeed);
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
