/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Adjusts the intake speed
 * @author Michael Francis
 */
public class adjustIntakeSpeed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake m_subsystem;
  private double m_modifier;

  /**
   * Increases the intake speed by a given amount. The motors do not stop after.
   * @param subsystem The subsystem used by this command. (Intake)
   * @param modifier A double that the robot changes the speed of the motors by.
   */
  public adjustIntakeSpeed(Intake subsystem, double modifier) {
    m_subsystem = subsystem;
    m_modifier = modifier;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will change the intake speed by a set amount
    m_subsystem.setIntakeSpeed(m_subsystem.getIntakeSpeed()+m_modifier);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
