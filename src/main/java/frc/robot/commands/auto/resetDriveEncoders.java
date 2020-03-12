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
 * Zeros each drive encoder
 * @author Madison J.
 * @category AUTON
 */
public class resetDriveEncoders extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;

  /**
   * Zeros each drive encoder
   * @param subsystem - SwerveDrivetrain subsystem object
   */
  public resetDriveEncoders(SwerveDrivetrain subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    m_subsystem.zeroAllDriveEncoders();
  }
  
  @Override
  public void end(boolean interrupted) {
  }

}
