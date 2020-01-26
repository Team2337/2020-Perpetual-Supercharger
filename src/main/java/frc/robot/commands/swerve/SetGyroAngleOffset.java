/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swerve;

import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the mode of the robot
 * @author Madison J.
 * @category AUTON
 */
public class SetGyroAngleOffset extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final OperatorAngleAdjustment m_subsystem;
  /* --- Strings --- */
  private String mode;

/**
 * Sets the mode of the robot
 * @param subsystem - OperatorAngleAdjustment subsystem object
 * @param mode - Changes the angle of the robot depending on the mode selected (modes may do more than change robot angles)
 * @see OperatorAngleAdjustment 
 */
  public SetGyroAngleOffset(OperatorAngleAdjustment subsystem, String mode) {
    m_subsystem = subsystem;
    /* --- Parameters Being Set to Global Variables --- */
    this.mode = mode;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    m_subsystem.setOffsetAngle(mode);
  }

  @Override
  public void end(boolean interrupted) {
  }

}
