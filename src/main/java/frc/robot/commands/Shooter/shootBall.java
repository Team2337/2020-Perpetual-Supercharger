/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Shoots the ball
 * @author Michael Francis
 */
public class shootBall extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_subsystem;
  private double m_modifier;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public shootBall(Shooter subsystem, double modifier) {
    m_subsystem = subsystem;
    m_modifier = modifier;
    // SmartDashboard.putNumber("Shooter Speed", 17000);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.shootMotor1.configClosedloopRamp(0.5);
    m_subsystem.shootMotor2.configClosedloopRamp(0.5);
    // m_modifier = SmartDashboard.getNumber("Shooter Speed", 17000);
    m_subsystem.setShooterSpeed(m_modifier);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_subsystem.shootMotor1.getSelectedSensorVelocity() > 5000){
      m_subsystem.shootMotor1.configClosedloopRamp(0);
      m_subsystem.shootMotor2.configClosedloopRamp(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // m_subsystem.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
