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

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Swerve;

/**
 * An example command that uses an example subsystem.
 */
public class driveToPosition extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  public int position;
  public boolean withinTolerance;
  public double moduleAngle;
  public int timer;
  public double timeout;
  public double rotation;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public driveToPosition(SwerveDrivetrain subsystem, int position, double moduleAngle, double rotation, double timeout) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    this.position = (int) (position* Swerve.TICKSPERINCH);
    this.moduleAngle = moduleAngle + m_subsystem.getYaw();
    this.timeout = timeout;
    this.rotation = rotation;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    for(int i = 0; i < 4; i++) {
      if (Math.abs(rotation) > 0) {
        if (i > 0 && i < 3) {
          m_subsystem.getModule(i).setSetpoint(-position);
        } else {
          m_subsystem.getModule(i).setSetpoint(position);
        }
      } else {
        m_subsystem.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
      }
    }
m_subsystem.zeroAllDriveEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    for(int i = 0; i < 4; i++) {
      if (Math.abs(rotation) > 0) {
        if (i % 2 == 0) {
          m_subsystem.getModule(i).setModuleAngle(Math.toRadians(-rotation));
        } else {
          m_subsystem.getModule(i).setModuleAngle(Math.toRadians(rotation));
        }
      } else {
      //  m_subsystem.getModule(i).setModuleAngle(Math.toRadians(moduleAngle));
    }
    }
    if (m_subsystem.getModule(0).getDriveEncoder() > 195000 && m_subsystem.getModule(0).getDriveEncoder() < 505000) {
      withinTolerance = false;
    } else {
      withinTolerance = false;
    }
    if (timer > timeout * 50) {
      withinTolerance = true;
    }
    timer++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return withinTolerance;
  }
}
