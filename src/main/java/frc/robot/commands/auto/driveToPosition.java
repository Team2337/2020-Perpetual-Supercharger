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
 */
  public driveToPosition(SwerveDrivetrain subsystem, int position, double moduleAngle) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.position = (int) (position* Swerve.TICKSPERINCH);
    this.moduleAngle = moduleAngle + m_subsystem.getYaw();
  }
  
/**
 * Drives to a set distance in inches, sets each module angle to be the same, and sets a maximum speed
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param position - The desired drive distance of the robot in inches
 * @param moduleAngle - The desired angle of the modules in degrees
 * @param maxSpeed - The maximum speed we set to the robot
 */
  public driveToPosition(SwerveDrivetrain subsystem, int position, double moduleAngle, double maxSpeed) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.position = (int) (position* Swerve.TICKSPERINCH);
    this.moduleAngle = moduleAngle + m_subsystem.getYaw();
    this.maxSpeed = maxSpeed;
  }
/**
 * Drives to a set distance in inches, sets each module angle to be the same, sets a maximum speed, 
 * sets a P value for the drive motors, and sets a P value for the angle motors
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param position - The desired drive distance of the robot in inches
 * @param moduleAngle - The desired angle of the modules in degrees
 * @param maxSpeed - The maximum speed we set to the robot
 * @param driveP - The P value we set to the drive motors
 * @param angleP - The P value we set to the angle motors
 */
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
    // Goes through 4 times to configure drive modules to a max speed, set our drive P value, set our angle P value, 
    // and configure our drive modules and angle modules
    for(int i = 0; i < 4; i++) {
      // Configures the drive modules to a positive max speed (forward) and a negative max speed (backward)
      if (maxSpeed != 0) {
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputForward = maxSpeed;
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputReverse = -maxSpeed;
      } else {
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputForward = m_subsystem.getModule(i).maxSpeed;
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputReverse = -m_subsystem.getModule(i).maxSpeed;
      } 
      // Sets the drive module configurations to our P value for drive motors
      if (driveP > 0) {
        m_subsystem.getModule(i).TalonFXConfigurationDrive.slot0.kP = driveP;
      }
      // Sets the angle module configurations to our P value for angle motors
      if (angleP > 0) {
        m_subsystem.getModule(i).TalonFXConfigurationAngle.slot0.kP = angleP;
      }
      // Sets a ramp rate of 0.5 for our drive modules
      m_subsystem.getModule(i).TalonFXConfigurationDrive.closedloopRamp = 0.5;
      // Configures all drive modules
      m_subsystem.getModule(i).driveMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationDrive);
      // Configures all angle modules
      m_subsystem.getModule(i).angleMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationAngle);
      // Sets the setpoint
      m_subsystem.getModule(i).setDriveSetpoint(position);
      // Sets the drive modules to coast mode
      m_subsystem.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
      SmartDashboard.putNumber("angleSetpoint/" + i, m_subsystem.getModule(i).getAngleEncoderValue());
      // Sets the angle setpoint to the angle encoder value
      m_subsystem.getModule(i).setAngleSetpoint(m_subsystem.getModule(i).getAngleEncoderValue()); //angleSetpoint[i]
    }
    // Zeros all drive encoders
    m_subsystem.zeroAllDriveEncoders();
  }

  @Override
  public void execute() {
    // Goes through 4 times and when the drive encoder is greater or less than our position it changes finish accordingly 
    for(int i = 0; i < 4; i++) {
      // Sets the drive encoder equal to the drive encoder value
      getDriveEncoder = m_subsystem.getModule(i).getDriveEncoderValue();
      // If the drive encoder is greater than position - 400 and less than position + 400 then finish gets set accordingly
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
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return finish;
  }
}
