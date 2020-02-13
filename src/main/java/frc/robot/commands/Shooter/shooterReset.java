package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;

/**
 * Shoots the ball
 * @author Michael Francis
 */
public class shooterReset extends InstantCommand {
  
  private final Shooter subsystem;

  
  public shooterReset(Shooter m_subsystem) {
    //Puts the parameters in the command's variables to be used around as a shortcut.
    subsystem = m_subsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      subsystem.resetCounter();
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }
}
