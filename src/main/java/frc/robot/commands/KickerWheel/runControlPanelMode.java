package frc.robot.commands.KickerWheel;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.KickerWheel;
import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class runControlPanelMode extends CommandBase {
  private KickerWheel subsystem;

  /**
   * Sets the kicker's speed to the control panel speed
   * 
   * @param subsystem The subsystem used by this command. (Kicker)
   */
  public runControlPanelMode(KickerWheel KickerWheel) {
    subsystem = KickerWheel;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    subsystem.setKickerSpeed(Constants.KICKERCONTROLPANELSPEED, Constants.KickerWheel.SHORTVELOCITYP);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setKickerSpeed(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
