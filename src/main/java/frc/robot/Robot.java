package frc.robot;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.auto.commandgroups.nineball.CenterGoalBack9BallGenerator3Ball;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.  
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private double average = 0;
  private double total = 0;
  private double iteration = 0;
  public static boolean isComp = false;
  public static BooleanSupplier isCompRobot = new BooleanSupplier(){
    @Override
    public boolean getAsBoolean() {
      return isComp;
    }
  };

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
  public SendableChooser<String> autonChooser;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    iteration = 0;
    
    // Must go before subsystems
    Constants = new Constants();
    Utilities = new Utilities();

    /* --- Subsystems --- */
    Agitator = new Agitator();
    Climber = new Climber();
    Intake = new Intake();
    KickerWheel = new KickerWheel();
    LEDs = new LEDs();
    OperatorAngleAdjustment = new OperatorAngleAdjustment();
    Pigeon = new Pigeon();
    OperatorAngleAdjustment = new OperatorAngleAdjustment();
    Serializer = new Serializer();
    Shooter = new Shooter();
    SwerveDrivetrain = new SwerveDrivetrain();
    TimeOfFlight = new TimeOfFlight();
    Vision = new Vision();
    
    OI = new OI();
    autonChooser = new SendableChooser<String>();
    SwerveDrivetrain.zeroAllDriveEncoders();
    SwerveDrivetrain.getModule(0).zeroDriveEncoder();

    // Resets the pigeon to 0    
    Pigeon.resetPidgey();

    autonChooser.setDefaultOption("default", "default");
    autonChooser.addOption("CenterGoalBack9BallGenerator3Ball", "CenterGoalBack9BallGenerator3Ball");
    
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
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic.
    CommandScheduler.getInstance().run();
    SmartDashboard.putData("Auton Selector", autonChooser);
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

    SwerveDrivetrain.getAverageAnalogValueInRadians(2);
    }

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    for(int i = 0; i < 4; i++)
    SwerveDrivetrain.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
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
    switch (autonChooser.getSelected()) {
      case "CenterGoalBack9BallGenerator3Ball":
      m_autonomousCommand = new CenterGoalBack9BallGenerator3Ball();
      
    }
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    //SwerveDrivetrain.zeroAllDriveEncoders();
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
