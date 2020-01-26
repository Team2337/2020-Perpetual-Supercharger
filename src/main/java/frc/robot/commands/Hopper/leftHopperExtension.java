/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Hopper;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

//A command that uses the hopper subsystem to move the left flipper
public class leftHopperExtension extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Hopper subsystem;

  //A command that uses the hopper subsystem to move the left flipper
  public leftHopperExtension(Hopper m_subsystem) {
    subsystem = m_subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Extends left flipper
    subsystem.extendLeftFlipper(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  // Retracts left flipper
  public void end(boolean interrupted) {
    subsystem.extendLeftFlipper(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}