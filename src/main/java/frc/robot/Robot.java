package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
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

  public static Agitator Agitator;
  public static Climber Climber;
  public static Hopper Hopper;
  public static Intake Intake;
  public static KickerWheel KickerWheel;
  public static LEDs LEDs;
  public static Pigeon Pigeon;
  public static Serializer Serializer;
  public static Shooter Shooter;
  public static SwerveDrivetrain SwerveDrivetrain;
  public static Vision Vision;
  public static PowerDistributionPanel PDP;
  public static OI OI;
  
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    
    // Must go before subsystems
    Constants = new Constants();
    Utilities = new Utilities();

    /* --- Subsystems --- */
    Agitator = new Agitator();
    Climber = new Climber();
    Hopper = new Hopper();
    Intake = new Intake();
    KickerWheel = new KickerWheel();
    LEDs = new LEDs();
    Serializer = new Serializer();
    Pigeon = new Pigeon();
    Serializer = new Serializer();
    Shooter = new Shooter();
    SwerveDrivetrain = new SwerveDrivetrain();
    Vision = new Vision();
    
    OI = new OI();

    // Resets the pigeon to 0    
    Pigeon.resetPidgey();
    
    //** --- Allows the speed of these subsystems to be changed on SmarDashboard --- */
    SmartDashboard.putNumber("Intake Speed", Constants.INTAKESPEED);
    SmartDashboard.putNumber("Agitator Speed", Constants.AGITATORSPEED);
    SmartDashboard.putNumber("Climber Speed", Constants.CLIMBERSPEED);
    SmartDashboard.putNumber("Serializer Speed", Constants.SERIALIZERFORWARDSPEED);
    SmartDashboard.putNumber("Kicker Speed", Constants.KICKERSPEED);
  }
  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p> This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
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
