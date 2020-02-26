/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the drive encoders to a specified tick value
 * @author Madison J.
 * @category AUTON
 */
public class setAllDriveEncoders extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  /* --- Integers --- */
  private int position;

/**
 * Sets the drive encoders to a specified tick value
 * @param subsystem - SwerveDrivetrarin subsystem object
 * @param position - Encoder value in ticks
 */
  public setAllDriveEncoders(SwerveDrivetrain subsystem, int position) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.position = position;
  }

  @Override
  public void initialize() {
    m_subsystem.setAllModuleDriveEncoders(position);

  }
  

  @Override
  public void end(boolean interrupted) {
  }

}
