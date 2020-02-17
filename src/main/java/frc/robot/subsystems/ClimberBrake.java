package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Controls engaging and disengaging the climber brake
 * @author Madison J.
 */
public class ClimberBrake extends SubsystemBase {

    private Solenoid ClimberBrake;

    public ClimberBrake() {
    ClimberBrake = new Solenoid(Constants.PCM0, Constants.PCM0PORT0);
  }

  public void initDefaultCommand() {
  }

  /**
   * Disengages the brake
   */
  public void disengageBrake() {
    ClimberBrake.set(true);
  }

  /**
   * Engages the brake
   */
  public void engageBrake() {
    ClimberBrake.set(false);
  }

  /**
   * Climber brake status
   */
  public boolean status() {
    return ClimberBrake.get();
  }
}