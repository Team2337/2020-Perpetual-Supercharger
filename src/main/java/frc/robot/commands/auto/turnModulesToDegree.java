package frc.robot.commands.auto;

import frc.robot.Constants.Swerve;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the desired module angle in degrees
 * @author Madison J.
 * @category AUTON
 */
public class turnModulesToDegree extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  /* --- Integers --- */
  private int desiredModuleAngle;
  private int angleSetpoint[] = new int[4];
  /* --- Doubles --- */
  private double moduleAngle;
  private double angleP;
  private double maxSpeed;

/**
 * Sets the desired module angle in degrees
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param moduleAngle - The desired angle of the modules in degrees
 */
  public turnModulesToDegree(SwerveDrivetrain subsystem, double moduleAngle) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.desiredModuleAngle = (int) (-moduleAngle * Swerve.TICKSPERDEGREE);
    this.moduleAngle = moduleAngle;
  }

  /**
   * Sets the desired module angle in degrees with our set P value
   * @param subsystem - SwerveDrivetrain subsystem object
   * @param moduleAngle - The desired angle of the modules in degrees
   * @param angleP - The P value we set to the angle motot
   */
  public turnModulesToDegree(SwerveDrivetrain subsystem, double moduleAngle, double angleP) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.desiredModuleAngle = (int) (-moduleAngle * Swerve.TICKSPERDEGREE);
    this.moduleAngle = moduleAngle;
    this.angleP = angleP;
  }

    /**
   * Sets the desired module angle in degrees with our set P value
   * @param subsystem - SwerveDrivetrain subsystem object
   * @param moduleAngle - The desired angle of the modules in degrees
   * @param angleP - The P value we set to the angle motot
   */
  public turnModulesToDegree(SwerveDrivetrain subsystem, double moduleAngle, double angleP, double maxSpeed) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    this.desiredModuleAngle = (int) (-moduleAngle * Swerve.TICKSPERDEGREE);
    this.moduleAngle = moduleAngle;
    this.angleP = angleP;
    this.maxSpeed = maxSpeed;
  }

  @Override
  public void initialize() {
    for(int i = 0; i < 4; i++) {
    // Goes through 4 times and sets the desired angle setpoint, sets the angle modules to our P value,
    // and configures all our angle modules
    //  this.angleSetpoint[i] = (int) (desiredModuleAngle - m_subsystem.getModule(i).getAngleEncoderValue());
      m_subsystem.getModule(i).setAngleSetpoint(desiredModuleAngle); //angleSetpoint[i]
      if (angleP > 0) {
      m_subsystem.getModule(i).TalonFXConfigurationAngle.slot0.kP = angleP;
      }
      if (maxSpeed != 0) {
      m_subsystem.getModule(i).TalonFXConfigurationAngle.peakOutputForward = maxSpeed;
      m_subsystem.getModule(i).TalonFXConfigurationAngle.peakOutputReverse = -maxSpeed;
      }
      m_subsystem.getModule(i).angleMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationAngle);
    }
  }

  @Override
  public void execute() {
   
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
