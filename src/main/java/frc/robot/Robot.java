package frc.robot;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.auton.autoShooterSystemOn;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.  
 */
public class Robot extends TimedRobot {
// Variables for finding the Mac Address of the robot
public static boolean isComp = false;  
public String mac;
  private Command m_autonomousCommand;
  public static Constants Constants;
  public static Utilities Utilities;

  public static Agitator Agitator;
  public static Climber Climber;
  public static Intake Intake;
  public static KickerWheel KickerWheel;
  public static LEDs LEDs;
  public static OperatorAngleAdjustment OperatorAngleAdjustment;
  public static Pigeon Pigeon;
  public static Serializer Serializer;
  public static Shooter Shooter;
  public static SwerveDrivetrain SwerveDrivetrain;
  public static TimeOfFlight TimeOfFlight;
  public static Vision Vision;
  public static PowerDistributionPanel PDP;
  public static OI OI;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    mac = "xx:xx:xx:xx:xx:xx";
    // Attempt to get the MAC address of the robot
    try {
      //Gets the raw data for the MAC address
      NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
      byte[] address = network.getHardwareAddress();
     // This parses through the byte array and turns it into a readable MAC Address
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < address.length; i++) {
        sb.append(String.format("%02X%s", address[i], (i < address.length - 1) ? ":" : ""));
      }
      mac = sb.toString();
      // If there are any errors, continue with the code instead of crashing the robot
    } catch (UnknownHostException e) {
      System.out.println("Unknown Host Exception - " + e);
    } catch (SocketException e) {
      System.out.println("Socket Exception - " + e);
    }
    // Determines what robot we are using based on the MAC adress
    // (make sure to change mac address for 2020 season)

    if (mac.equals("00:80:2F:17:89:85")) {
      System.out.println("PracticeBot " + mac);
      isComp = false;
    } else {
      // If we are not using PracticeBot, assume we are using CompBot (this also will
      // cover if there is an error while getting the MAC address)
      System.out.println("CompBot " + mac);
      isComp = true;
    }
    
    // Must go before subsystems
    Constants = new Constants();
    Utilities = new Utilities();

    /* --- Subsystems --- */
    Agitator = new Agitator();
    Climber = new Climber();
    Intake = new Intake();
    KickerWheel = new KickerWheel();
    LEDs = new LEDs();
    Pigeon = new Pigeon();
    OperatorAngleAdjustment = new OperatorAngleAdjustment();
    Serializer = new Serializer();
    Shooter = new Shooter();
    SwerveDrivetrain = new SwerveDrivetrain();
    TimeOfFlight = new TimeOfFlight();
    Vision = new Vision();
    
    OI = new OI();

    // Resets the pigeon to 0    
    Pigeon.resetPidgey();
    Vision.switchPipeLine(1);
    
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
    // and running subsystem periodic() methods.  This must be called from the robot's periodic.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    Robot.Vision.setLEDMode(1);
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = new autoShooterSystemOn();
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
