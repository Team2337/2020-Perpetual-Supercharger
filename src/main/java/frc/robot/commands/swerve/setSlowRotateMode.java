package frc.robot.commands.swerve;

import frc.robot.subsystems.OperatorAngleAdjustment;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private OperatorAngleAdjustment subsystem;

  /**
   * Sets the slow rotation mode and speed for slow rotation. 
   * The rotational changes are applied in SwerveDriveCommand
   * @param subsystem
   * @param slowRotateMode
   * @param slowRotateSpeed
   * @see SwerveDriveCommand
   */
  public setSlowRotateMode(OperatorAngleAdjustment subsystem, boolean slowRotateMode, double slowRotateSpeed) {
    this.slowRotateMode = slowRotateMode;
    this.slowRotateSpeed = slowRotateSpeed;
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
      subsystem.setSlowRotateMode(slowRotateMode);
      subsystem.setSlowRotateSpeed(slowRotateSpeed);
  }

  @Override
  public void end(boolean interrupted) {
  }
}
