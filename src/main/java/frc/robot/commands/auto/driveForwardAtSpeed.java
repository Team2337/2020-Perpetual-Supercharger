package frc.robot.commands.auto;

import frc.robot.subsystems.SwerveDrivetrain;
import frc.robot.Constants.Swerve;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Drives to a set distance in inches at the set speed and sets each module angle to be the same
 * @author Madison J.
 * @category AUTON
 */
public class driveForwardAtSpeed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  /* --- Intergers --- */
  private int position;
  private int iteration = 0;
  /* --- Booleans --- */
  private boolean finish;
  /* --- Doubles --- */
  private double getDriveEncoder;
  private double speed;

/**
 * Drives to a set distance in inches at the set speed and sets each module angle to be the same
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param position - Desired position of the robot
 * @param speed - The speed of the robot
 */
  public driveForwardAtSpeed(SwerveDrivetrain subsystem, int position, double speed) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.position = (int) (position* Swerve.TICKSPERINCH);
    this.speed = speed;
  }

  @Override
  public void initialize() {
    // Goes through 4 times to configure the drive modules to positive speed (forward), negative speed (backward), 
    //configure all drive modules, set the setpoint, and set the drive modules to coast mode
    for(int i = 0; i < 4; i++) {
      m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputForward = speed;
      m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputReverse = -speed;
      m_subsystem.getModule(i).driveMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationDrive);
      m_subsystem.getModule(i).setDriveSetpoint(position);
      m_subsystem.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
    }
    // Zeros all drive encoders
    m_subsystem.zeroAllDriveEncoders();
  }

  @Override
  public void execute() {
    // Goes through 4 times and sets the drive encoder equal to the drive encoder value, sets the module angle to the yaw in radians, 
    // gets the drive encoder and if the drive encoder is greater than position - 400 and less than position + 400 then finish gets set accordingly
    for(int i = 0; i < 4; i++) {
      getDriveEncoder = m_subsystem.getModule(i).getDriveEncoderValue();
       m_subsystem.getModule(i).setModuleAngle(Math.toRadians(0 + m_subsystem.getYaw()));
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
