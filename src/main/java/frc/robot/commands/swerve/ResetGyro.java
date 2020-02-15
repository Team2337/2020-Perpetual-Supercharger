/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;
import frc.robot.subsystems.Pigeon;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the robot's future angle offset. This should be on the <b>OPERATOR</b> joystick
 * @see OperatorAngleAdjustment
 * @author Bryce G., Madison J.
 * @category SWERVE
 */
public class ResetGyro extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Pigeon m_subsystem;
  
  /**
   * Sets the robot's future angle offset. This should be on the <b>DRIVER</b> joystick
   * @param subsystem - OperatorAngleAdjustment Subsystem Object
   * @param mode - String value signifying the rotation mode the robot is in 
   */
  public ResetGyro(Pigeon subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.Pigeon.resetPidgey();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}