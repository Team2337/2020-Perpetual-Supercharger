package frc.robot.commands.Servo;

import frc.robot.subsystems.Servo66;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class deployHyperLoop extends InstantCommand {
    private Servo66 servo66;

    /**
     * Sets the kicker's speed.
     * 
     * @param subsystem The subsystem used by this command. (Kicker)
     */
    public deployHyperLoop(Servo66 servo66) {
    this.servo66 = servo66;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(servo66);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    servo66.deployHyperLoop();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
