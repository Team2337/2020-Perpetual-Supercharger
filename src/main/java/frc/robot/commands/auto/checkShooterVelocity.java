package frc.robot.commands.auto;

import frc.robot.Robot;
import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the module angles to the desired rotation angle and rotates the robot a specified direction, either left or right
 * @author Madison J.
 * @category AUTON
 */
public class checkShooterVelocity extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private double shooterVelocity;
    private double kickerVelocity;
    private boolean finished;
    private boolean isAtVelocity;

  public checkShooterVelocity(Serializer subsystem, double shooterVelocity, double kickerVelocity) {
    addRequirements(subsystem);
    this.shooterVelocity = shooterVelocity;
    this.kickerVelocity = kickerVelocity;
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    if ((Robot.Shooter.getAvgRPM() > (shooterVelocity * 0.75)) && (Robot.KickerWheel.getKickerSpeed() > (kickerVelocity * 0.75))) {
      isAtVelocity = true;
    } else {
      isAtVelocity = false;
    }
    finished = isAtVelocity ? true: false;
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}