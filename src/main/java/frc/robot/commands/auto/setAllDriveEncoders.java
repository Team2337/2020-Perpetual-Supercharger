/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import frc.robot.Robot;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * An example command that uses an example subsystem.
 */
public class setAllDriveEncoders extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  public int position;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public setAllDriveEncoders(SwerveDrivetrain subsystem, int position) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    this.position = position;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.SwerveDrivetrain.setAllModuleDriveEncoders(position);

  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}
