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
 * The command that corresponds to the subsystem Intake
 * @author
 * <a href="mailto:mfrancis48439@gmail.com?subject=Intake%20Programming">Michael Francis</a>
 */
public class intakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake m_subsystem;
  private double intakeSpeed;

  /**
   * Creates a new intakeCommand.
   * @param subsystem The subsystem used by this command. (Intake)
   * @param speed A double number that sets what speed the motors move at
   * @author Michael Francis
   */
  public intakeCommand(Intake subsystem, double speed) {
    m_subsystem = subsystem;
    intakeSpeed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the intake to run at full speed
    m_subsystem.setIntakeSpeed(intakeSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the intake
    m_subsystem.setIntakeSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
