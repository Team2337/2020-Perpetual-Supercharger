/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import frc.robot.subsystems.SwerveDrivetrain;
import frc.robot.Constants.Swerve;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Drives to a set distance in inches and sets each module angle to be the same
 * @author Madison J.
 * @category AUTON
 */
public class driveToPosition extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  /* --- Intergers --- */
  private int position;
  private int iteration = 0;
  /* --- Booleans --- */
  private boolean finish;
  /* --- Doubles --- */
  private double moduleAngle;
  private double getDriveEncoder;
  private double maxSpeed = 0;
  private double driveP;
  private double angleP;

/**
 * Drives to a set distance in inches and sets each module angle to be the same
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param position - The desired drive distance of the robot in inches
 * @param moduleAngle - The desired angle of the modules in degrees
 * @param timeout - Ends the command after the amount of seconds given
 */
  public driveToPosition(SwerveDrivetrain subsystem, int position, double moduleAngle) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.position = (int) (position* Swerve.TICKSPERINCH);
    this.moduleAngle = moduleAngle + m_subsystem.getYaw();
  }
  
  public driveToPosition(SwerveDrivetrain subsystem, int position, double moduleAngle, double maxSpeed) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.position = (int) (position* Swerve.TICKSPERINCH);
    this.moduleAngle = moduleAngle + m_subsystem.getYaw();
    this.maxSpeed = maxSpeed;
  }

  public driveToPosition(SwerveDrivetrain subsystem, int position, double moduleAngle, double maxSpeed, double driveP, double angleP) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.position = (int) (position* Swerve.TICKSPERINCH);
    this.moduleAngle = moduleAngle + m_subsystem.getYaw();
    this.maxSpeed = maxSpeed;
    this.driveP = driveP;
    this.angleP = angleP;
  }

  @Override
  public void initialize() {
    // Goes through 4 times to set the position and neutral mode to coast mode on each module
    for(int i = 0; i < 4; i++) {
      if (maxSpeed != 0) {
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputForward = maxSpeed;
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputReverse = -maxSpeed;
      } else {
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputForward = m_subsystem.getModule(i).maxSpeed;
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputReverse = -m_subsystem.getModule(i).maxSpeed;
      } 
      if (driveP > 0) {
        m_subsystem.getModule(i).TalonFXConfigurationDrive.slot0.kP = driveP;
      }
      if (angleP > 0) {
        m_subsystem.getModule(i).TalonFXConfigurationAngle.slot0.kP = angleP;
      }
      m_subsystem.getModule(i).TalonFXConfigurationDrive.closedloopRamp = 0.5;
      m_subsystem.getModule(i).driveMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationDrive);
      m_subsystem.getModule(i).angleMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationAngle);
      m_subsystem.getModule(i).setDriveSetpoint(position);
      m_subsystem.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
      SmartDashboard.putNumber("angleSetpoint/" + i, m_subsystem.getModule(i).getAngleEncoderValue());
      m_subsystem.getModule(i).setAngleSetpoint(m_subsystem.getModule(i).getAngleEncoderValue()); //angleSetpoint[i]
    }
    m_subsystem.zeroAllDriveEncoders();
  }

  @Override
  public void execute() {
    // Goes through 4 times and sets the target angle on each module
    for(int i = 0; i < 4; i++) {
      getDriveEncoder = m_subsystem.getModule(i).getDriveEncoderValue();
      // m_subsystem.getModule(i).setModuleAngle(Math.toRadians(moduleAngle));
       if (Math.abs(getDriveEncoder) > Math.abs(position) - 400 && Math.abs(getDriveEncoder) < Math.abs(position) + 400) {
        if (iteration > 10) {
          finish = true;
        } else {
          finish = false;
        }
        iteration++;
      } else {
        iteration = 0;
        finish = false;
      }
     
    }
    // Ends the command if the timer is greater than the timeout

    
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return finish;
  }
}
