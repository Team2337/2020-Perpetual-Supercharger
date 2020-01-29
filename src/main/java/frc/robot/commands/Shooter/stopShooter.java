package frc.robot.commands.Shooter;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Stops the shooter by slowing it down until it can be stopped without destroying itself.
 * @author Michael Francis
 */
public class stopShooter extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_subsystem;
  private int iterations;

  /**
   * Stops the shooter
   * @param subsystem The subsystem used by this command (Shooter)
   */
  public stopShooter(Shooter subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    iterations = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if((m_subsystem.getShooterSpeed()[0]+m_subsystem.getShooterSpeed()[1])/2 > 5000){
      if(iterations == 0){
        m_subsystem.slowShooter();
      }
      iterations = (iterations+1) % 5;
    }else{
      m_subsystem.stopShooter();
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if((m_subsystem.getShooterSpeed()[0]+m_subsystem.getShooterSpeed()[1])/2 < 2000){
      return true;
    }else{
      return false;
    } 
  }
}
