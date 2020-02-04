/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import frc.robot.Robot;
import frc.robot.Constants.Swerve;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class turnModulesToDegree extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  private int desiredModuleAngle;
  private int angleSetpoint[] = new int[4];
  private double moduleAngle;
  private double angleP;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public turnModulesToDegree(SwerveDrivetrain subsystem, double moduleAngle) {
    m_subsystem = subsystem;
    this.desiredModuleAngle = (int) (-moduleAngle * Swerve.TICKSPERDEGREE);
    this.moduleAngle = moduleAngle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  public turnModulesToDegree(SwerveDrivetrain subsystem, double moduleAngle, double angleP) {
    m_subsystem = subsystem;
    this.desiredModuleAngle = (int) (-moduleAngle * Swerve.TICKSPERDEGREE);
    this.moduleAngle = moduleAngle;
    this.angleP = angleP;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    for(int i = 0; i < 4; i++) {
    //  this.angleSetpoint[i] = (int) (desiredModuleAngle - m_subsystem.getModule(i).getAngleEncoderValue());
      m_subsystem.getModule(i).setAngleSetpoint(desiredModuleAngle); //angleSetpoint[i]
      if (angleP > 0) {
      m_subsystem.getModule(i).TalonFXConfigurationAngle.slot0.kP = angleP;
      }
      m_subsystem.getModule(i).angleMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationAngle);
    }
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
    return false;
  }
}
