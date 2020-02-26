package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the slow rotation mode and speed for slow rotation. 
 * The rotational changes are applied in SwerveDriveCommand
 * @see SwerveDriveCommand
 * @author Bryce G.
 * @category SWERVE
 */
public class setSlowRotateMode extends InstantCommand {

  private boolean slowRotateMode;
  private double slowRotateSpeed;
  private OperatorAngleAdjustment OperatorAngleAdjustment;

  /**
   * Sets the slow rotation mode and speed for slow rotation. 
   * The rotational changes are applied in SwerveDriveCommand
   * @param subsystem
   * @param slowRotateMode
   * @param slowRotateSpeed
   * @see SwerveDriveCommand
   */
  public setSlowRotateMode(OperatorAngleAdjustment OperatorAngleAdjustment, boolean slowRotateMode, double slowRotateSpeed) {
    this.slowRotateMode = slowRotateMode;
    this.slowRotateSpeed = slowRotateSpeed;
    this.OperatorAngleAdjustment = OperatorAngleAdjustment;
  }

  @Override
  public void initialize() {
    // Sets the slow rotate mode and slow rotate speed
    OperatorAngleAdjustment.setSlowRotateMode(slowRotateMode);
    OperatorAngleAdjustment.setSlowRotateSpeed(slowRotateSpeed);
      if(!slowRotateMode) {
        OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getPigeonYawMod());
      }
  }

  @Override
  public void end(boolean interrupted) {
  }
}
