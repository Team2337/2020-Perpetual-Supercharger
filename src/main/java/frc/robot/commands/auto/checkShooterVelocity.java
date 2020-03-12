package frc.robot.commands.auto;

import frc.robot.Robot;
import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Checks to see if both the shooter and kicker wheel are at velocity and if so sets isAtVelocity to true
 * @author Madison J.
 * @category AUTON
 */
public class checkShooterVelocity extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Serializer m_subsystem;
    private double shooterVelocity;
    private double kickerVelocity;
    private boolean finished;
    private boolean isAtVelocity;

  /**
   * Checks to see if both the shooter and kicker wheel are at velocity and if so sets isAtVelocity to true
   * @param subsystem - The required subsystem
   * @param shooterVelocity - The velocity of the shooter
   * @param kickerVelocity - The velocity of the kicker wheel
   */
  public checkShooterVelocity(Serializer subsystem, double shooterVelocity, double kickerVelocity) {
    m_subsystem = subsystem;
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