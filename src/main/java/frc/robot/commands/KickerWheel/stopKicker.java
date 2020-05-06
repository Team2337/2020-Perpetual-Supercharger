package frc.robot.commands.KickerWheel;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.KickerWheel;

/**
 * A command that stops the kicker wheel using the kicker subsystem.
 */
public class stopKicker extends InstantCommand {
  public double kspeed;
  private KickerWheel subsystem;

  /**
   * Stops the kicker wheel.
   *
   * @param subsystem The subsystem used by this command.
   */
  public stopKicker(KickerWheel kickerWheel) {
    subsystem = kickerWheel;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    Robot.KickerWheel.kickerWheelMotor.setIdleMode(IdleMode.kBrake);
    subsystem.stopKicker();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
