package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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

  //Time of Flight Sensor Code
  private double a;
  private int b;

  public static int distanceSensorLoad = 0;
  public static double loadSensorSerial;
  public static double loadSensorPart;
  public static double loadSensorFirmware;
  public static byte[] hwdataLoad = new byte[8];

  public static int distanceSensorRocket = 0;
  public static double rocketSensorSerial;
  public static double rocketSensorPart;
  public static double rocketSensorFirmware;
  public static byte[] hwdataRocket = new byte[8];
  //End TOF Code


  private Command m_autonomousCommand;

  public static Constants Constants;
  public static Utilities Utilities;

  public static Climber Climber;
  public static ClimberExtender ClimberExtender;
  public static ControlPanelSpinner ControlPanelSpinner;
  public static Serializer Serializer;
  public static Intake Intake;
  public static LEDs LEDs;
  public static Pigeon Pigeon;
  public static Shooter Shooter;
  public static ShooterHood ShooterHood;
  public static SwerveDrivetrain SwerveDrivetrain;
  public static Vision Vision;
  public static PowerDistributionPanel PDP;
  public static Feeder Feeder;
  public static OI OI;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    //Time Of Flight sensor code
    hwdataLoad = CanbusDistanceSensor.readHeartbeat(distanceSensorLoad);
    int[] temp = CanbusDistanceSensor.getSensorInfo(hwdataLoad);
    loadSensorSerial = temp[0];
    loadSensorPart = temp[1];
    loadSensorFirmware = temp[2];
    SmartDashboard.putNumber("LoadSerial", loadSensorSerial);
    SmartDashboard.putNumber("LoadPart", loadSensorPart);
    SmartDashboard.putNumber("LoadFirmware", loadSensorFirmware);
    int temp1[] = CanbusDistanceSensor.readCalibrationState(distanceSensorLoad);
    SD.putN("X", temp1[0]);
    SD.putN("Y", temp1[1]);
    SD.putN("Offset", temp1[2]);
    //End TOF Code

    
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
    Pigeon = new Pigeon();
    Shooter = new Shooter();
    ShooterHood = new ShooterHood();
    SwerveDrivetrain = new SwerveDrivetrain();
    Vision = new Vision();
    Feeder = new Feeder();
    OI = new OI();

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
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
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

    //////////////////////////////////////////////////////////////
    // -------------------------------------------------------- //
    // --- PUT TIMEOFFLIGHT SENSOR VALUES ON SMARTDASHBOARD --- //
    // -------------------------------------------------------- //
    //////////////////////////////////////////////////////////////
    
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("TTT", Timer.getFPGATimestamp() - a);
    a = Timer.getFPGATimestamp();
    b++;
    double[] temp = { 0, 0 };
    if (b >= 10) {

      temp = CanbusDistanceSensor.getDistanceMM(distanceSensorLoad);
      if (temp[0] < 0) {
        SmartDashboard.putNumber("Read Error", temp[0]);
        // temp[0] = 0;
      }
      SD.putN0("SigRate", temp[1]);

      SD.putN0("DistMM", temp[0]);
      SD.putN2("DistFt", temp[0] / 304.8);
      temp = CanbusDistanceSensor.readQuality(distanceSensorLoad);
      SD.putN0("AmbLight", temp[0]);
      SD.putN0("StdDev", temp[1]);
      double distR = CanbusDistanceSensor.getDistanceMM(distanceSensorRocket)[0];
      if (distR < 0) {
        SmartDashboard.putNumber("Read Error", distR);
      distR = 0;
      }
      SD.putN0("DistMM23", distR);
      SD.putN2("DistFt23", distR / 304.8);
      double tempR[] = CanbusDistanceSensor.readQuality(distanceSensorRocket);
      SD.putN0("AmbLight23", tempR[0]);
      SD.putN0("StdDev23", tempR[1]);

      b = 0;
    }
      //End TOF Code
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
