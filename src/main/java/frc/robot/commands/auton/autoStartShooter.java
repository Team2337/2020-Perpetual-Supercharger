package frc.robot.commands.auton;

import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Starts the shooter at the designated speed
 * @author Bryce G.
 */
public class autoStartShooter extends InstantCommand {
  
  private final Shooter Shooter;

  /**
   * Starts the shooter at the designated speed
   * @param Shooter - OperatorAngleAdjustment Subsystem object
   */
  public autoStartShooter(Shooter shooter) {
    this.Shooter = shooter;

    addRequirements(Shooter);
  }


  @Override
  public void initialize() {
    // Sets the shooter speed to the future speed
    Shooter.setShooterSpeed(Robot.Shooter.getFutureSpeed());
  }


  @Override
  public void end(boolean interrupted) {
  }
}
