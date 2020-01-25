/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import frc.robot.subsystems.Shooter;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Shoots up to 5 balls as accurate and quickly as possible.
 * @author Michael Francis
 */
public class shootContinuously extends CommandBase {
  //These variables are used to take a parameter through the rest of the command in methods
  private final Shooter m_subsystem;
  private double m_modifier;

  /**
   * Shoots the ball at a specified speed.
   * @param subsystem
   * The subsystem that the command uses (Shooter)
   * @param modifier
   * The velocity in which the shooter will shoot at.
   * <ul>
   * <li>From the initiation line, this number will be around 20000-22000.</li>
   * <li>There is commented out code for this being controlled on SmartDashboard instead.</li>
   * </ul>
   */
  public shootContinuously(Shooter subsystem, double modifier) {
    //Puts the parameters in the command's variables to be used around as a shortcut.
    m_subsystem = subsystem;
    m_modifier = modifier;
    // SmartDashboard.putNumber("Shooter Speed", 17000);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Configures a closed-loop ramp of 0.5. This is necessary because of code in execute.
    //The code in execute allows the shooter to coast after turning off.
    m_subsystem.shootMotor1.configClosedloopRamp(0.5);
    m_subsystem.shootMotor2.configClosedloopRamp(0.5);
    // m_modifier = SmartDashboard.getNumber("Shooter Speed", 17000);
    m_subsystem.setShooterSpeed(m_modifier);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Once the velocity reaches a certain speed, the closed-loop ramp is turned off.
    //This is to ensure that the motors when turned off will coast their way down.
    if(m_subsystem.shootMotor1.getSelectedSensorVelocity() > 5000){
      m_subsystem.shootMotor1.configClosedloopRamp(0);
      m_subsystem.shootMotor2.configClosedloopRamp(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Code that when the command ends, stop the shooter.
    //Comment the code out if the ramp rate code in execute does not work or
    //if you need to test.
    // m_subsystem.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
