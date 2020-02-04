/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Takes the preset angle offset and the robot goes to that angle
 * @author Madison J.
 * @category AUTON
 */
public class ChangeGyroAngleOffset extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final OperatorAngleAdjustment m_subsystem;
  /* --- Booleans --- */
  private boolean isRotating;

/**
 * Takes the preset angle offset and the robot goes to that angle
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param isRotating - Tells whether or not the robot is rotating
 */
  public ChangeGyroAngleOffset(OperatorAngleAdjustment subsystem, boolean isRotating) {
    m_subsystem = subsystem;
    /* --- Parameters Being Set to Global Variables --- */
    this.isRotating = isRotating;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    Robot.OperatorAngleAdjustment.setOffsetAngle(Robot.OperatorAngleAdjustment.getGyroAngleOffset());
    Robot.OperatorAngleAdjustment.setIsChangingGyroAngle(isRotating);
  }

  @Override
  public void end(boolean interrupted) {
  }

}
