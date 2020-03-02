package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;

/**
 * Resets the ramp rate on the shooter to be zero
 * @author Bryce G.
 */
public class AutoResetRampRate extends InstantCommand {

  private final OperatorAngleAdjustment subsystem;

  /**
   * Resets the ramp rate on the shooter to be zero
   * 
   * @param m_subsystem OperatorAngleAdjustment Subsystem object
   */
  public AutoResetRampRate(OperatorAngleAdjustment m_subsystem) {
    //Puts the parameters in the command's variables to be used around as a shortcut.
    subsystem = m_subsystem;

    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {
    //Sets the ramp rate on each motor to zero
    Robot.Shooter.leftShootMotor.configClosedloopRamp(0);
    Robot.Shooter.rightShootMotor.configClosedloopRamp(0);
  }

  @Override
  public void end(boolean interrupted) {
  }
}