/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  public static Constants Constants;
  public static Utilities Utilities;

  public static Climber Climber;
  public static ClimberExtender ClimberExtender;
  public static ControlPanelSpinner ControlPanelSpinner;
  public static Serializer Serializer;
  public static Intake Intake;
  public static LEDs LEDs;
  public static OperatorAngleAdjustment OperatorAngleAdjustment;
  public static Pigeon Pigeon;
  public static Shooter Shooter;
  public static ShooterHood ShooterHood;
  public static SwerveDrivetrain SwerveDrivetrain;
  public static Vision Vision;
  public static PowerDistributionPanel PDP;
  public static OI OI;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Must go before subsystems
    Constants = new Constants();
    Utilities = new Utilities();
    /* --- Subsystems --- */
    Climber = new Climber();
    ClimberExtender = new ClimberExtender();
    ControlPanelSpinner = new ControlPanelSpinner();
    Serializer = new Serializer();
    Intake = new Intake();
    LEDs = new LEDs();
    OperatorAngleAdjustment = new OperatorAngleAdjustment();
    Pigeon = new Pigeon();
    Shooter = new Shooter();
    ShooterHood = new ShooterHood();
    SwerveDrivetrain = new SwerveDrivetrain();
    Vision = new Vision();
    OI = new OI();
    SwerveDrivetrain.zeroAllDriveEncoders();
    SwerveDrivetrain.getModule(0).zeroDriveEncoder();

    // Resets the pigeon to 0    
    Pigeon.resetPidgey();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Yaw", -Pigeon.getYaw());
    SmartDashboard.putNumber("getAverageEncoderDistance", SwerveDrivetrain.getAverageDriveEncoderDistance());
    SmartDashboard.putNumber("getModuleDriveEncoder0", SwerveDrivetrain.getModule(0).getDriveEncoderValue());
    SmartDashboard.putNumber("getModuleDriveEncoder1", SwerveDrivetrain.getModule(1).getDriveEncoderValue());
    SmartDashboard.putNumber("getModuleDriveEncoder2", SwerveDrivetrain.getModule(2).getDriveEncoderValue());
    SmartDashboard.putNumber("getModuleDriveEncoder3", SwerveDrivetrain.getModule(3).getDriveEncoderValue());
    for(int i = 0; i < 4; i++) {
    SmartDashboard.putNumber("ModuleAngle/" + i, 
    ((SwerveDrivetrain.getModule(i).getNormalizedAnalogVoltageRadians() - SwerveDrivetrain.angleOffsets[i]) %(2 * Math.PI)) * 180 / Math.PI);
    SmartDashboard.putNumber("angleEncoder/" + i, SwerveDrivetrain.getModule(i).getAngleEncoderValue());
    SmartDashboard.putNumber("angleP/" + i, SwerveDrivetrain.getModule(i).TalonFXConfigurationAngle.slot0.kP);
    }

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    for(int i = 0; i < 4; i++)
    SwerveDrivetrain.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    SwerveDrivetrain.zeroAllDriveEncoders();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    for(int i = 0; i < 4; i++)
    SwerveDrivetrain.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
    Pigeon.resetPidgey();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
