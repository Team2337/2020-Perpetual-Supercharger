package frc.robot.commands.KickerWheel;

import frc.robot.Constants;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class runKicker extends InstantCommand {
  public double kspeed;
  private KickerWheel subsystem;

  /**
   * Sets the kicker's speed.
   * 
   * @param subsystem The subsystem used by this command. (Kicker)
   * @param kickerspeed The speed (in velocity) the kicker wheel is set to.
   */
  public runKicker(KickerWheel kickerWheel, double kickerspeed) {
    subsystem = kickerWheel;
    kspeed = kickerspeed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    kspeed = SmartDashboard.getNumber("Kicker Speed", Constants.KICKERSPEED);
    subsystem.setKickerSpeed(kspeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
