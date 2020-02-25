/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.ColorWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class colorWheel extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  // private final ColorWheel m_subsystem; 

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   * @return
   */
  public void colorWheel(ColorWheel subsystem) {
    // m_subsystems = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

      if (Robot.ColorWheel.getColor().equals("Blue")) {
        Robot.ColorWheel.ColorWheelMotor.set(0);
      } else {
        Robot.ColorWheel.ColorWheelMotor.set(0.3);
      }
      if (Robot.ColorWheel.getColor().equals("Green")) {
        Robot.ColorWheel.ColorWheelMotor.set(0);
      } else {
        Robot.ColorWheel.ColorWheelMotor.set(0.3);
      }
      if (Robot.ColorWheel.getColor().equals("Red")) {
        Robot.ColorWheel.ColorWheelMotor.set(0);
      } else {
        Robot.ColorWheel.ColorWheelMotor.set(0.3);
      }
      if (Robot.ColorWheel.getColor().equals("Yellow")) {
        Robot.ColorWheel.ColorWheelMotor.set(0);
      } else {
        Robot.ColorWheel.ColorWheelMotor.set(0.3);
      }
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
