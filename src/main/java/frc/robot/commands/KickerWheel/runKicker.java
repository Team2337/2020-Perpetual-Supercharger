package frc.robot.commands.KickerWheel;

import frc.robot.Constants;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class runKicker extends InstantCommand {
  public double kSpeed;
  private KickerWheel subsystem;

  /**
   * Sets the kicker's speed.
   * 
   * @param subsystem The subsystem used by this command. (Kicker)
   * @param kickerSpeed The speed (in velocity) the kicker wheel is set to.
   */
  public runKicker(KickerWheel kickerWheel, double kickerSpeed) {
    subsystem = kickerWheel;
    kSpeed = kickerSpeed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    // kSpeed = SmartDashboard.getNumber("Kicker Speed", Constants.KICKERSPEED);
    subsystem.setKickerSpeed(Constants.KICKERSPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
