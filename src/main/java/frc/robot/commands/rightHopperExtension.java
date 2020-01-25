/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

/**
 * An example command that uses an example subsystem.
 */
public class rightHopperExtension extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Hopper m_subsystem;

    // Makes a requirement to extend the hopper's right pannel
  public rightHopperExtension(Hopper subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Extends left Panel
    m_subsystem.extendRightFlipper(true);
  }

  /*
   * Called once the command ends or is interrupted. When interupted, retract
   * right hopper
   */
  @Override
  public void end(boolean interrupted) {
    m_subsystem.extendRightFlipper(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}