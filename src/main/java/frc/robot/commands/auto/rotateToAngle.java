package frc.robot.commands.auto;

import frc.robot.Constants.Swerve;
import frc.robot.subsystems.SwerveDrivetrain;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the module angles to the desired rotation angle and rotates the robot a specified direction, either left or right
 * @author Madison J.
 * @category AUTON
 */
public class rotateToAngle extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  /* --- Integers --- */
  private int position;
  private int iteration = 0;
  /* --- Doubles --- */
  private double rotationDegree = 45;
  private double kP = 0.25;
  private double getDriveEncoder;
  private double maxSpeed;
  /* --- Booleans --- */
  private boolean finish;

/**
 * Sets the module angles to the desired rotation angle and rotates the robot a specified direction, either left or right
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param direction - The direction, left or right, the chassis will go
 * @param angle - The angle we want the robot to rotate to
 */
  public rotateToAngle(SwerveDrivetrain subsystem, String direction, double angle) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    this.position = (int) ((angle * Swerve.INCHESPERDEGREE) * Swerve.TICKSPERINCH);
    /* --- Parameters Being Set to Global Variables --- */
    switch (direction) {
      case "right":
      position = -position;
      break;
      case "left":
      position = position;
    }
  }

  /**
   * Sets the module angles to the desired rotation angle and rotates the robot a specified direction, 
   * either left or right at a set max speed
   * @param subsystem - SwerveDrivetrain subsystem object
   * @param direction - The direction, left or right, the chassis will go
   * @param angle - The angle we want the robot to rotate to
   * @param maxSpeed - The maximum speed we set to the robot
   */
  public rotateToAngle(SwerveDrivetrain subsystem, String direction, double angle, double maxSpeed) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    this.position = (int) ((angle * Swerve.INCHESPERDEGREE) * Swerve.TICKSPERINCH);
    this.maxSpeed = maxSpeed;
    /* --- Parameters Being Set to Global Variables --- */
    switch (direction) {
      case "right":
      position = -position;
      break;
      case "left":
      position = position;
    }
  }

  @Override
  public void initialize() {
    // Goes through 4 times and sets the max speed, positive or negative, sets the drive configuration to our P value,
    // sets the drive modules to our P value, configures all our drive modules, and inverts modules 1 and 2
    for(int i = 0; i < 4; i++) {
      // Sets the positive max speed (forward) and the negative max speed (backward)
      if (maxSpeed != 0) {
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputForward = maxSpeed;
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputReverse = -maxSpeed;
      } else {
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputForward = m_subsystem.getModule(i).maxSpeed;
        m_subsystem.getModule(i).TalonFXConfigurationDrive.peakOutputReverse = -m_subsystem.getModule(i).maxSpeed;
      }
      //m_subsystem.getModule(i).TalonFXConfiguration.peakOutputForward = maxSpeed;
      //m_subsystem.getModule(i).TalonFXConfiguration.peakOutputReverse = -maxSpeed;
      // Resests the drive configuration 
      m_subsystem.getModule(i).TalonFXConfigurationDrive.slot0.kP = kP;
      m_subsystem.getModule(i).TalonFXConfigurationDrive.slot0.allowableClosedloopError = 50;
      m_subsystem.getModule(i).driveMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationDrive, 0);
      // Checks to see if the modules are rotating
      if (Math.abs(rotationDegree) > 0) {
        // Checks to see if it is module 1 or 2 and inverts their position so they will go in the opposite direction
        if (i > 0 && i < 3) {
          m_subsystem.getModule(i).setDriveSetpoint(-position);
        } else {
          m_subsystem.getModule(i).setDriveSetpoint(position);
        }
      } else {
        m_subsystem.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
      }
    }
      // Zeros all of the drive encoders
      m_subsystem.zeroAllDriveEncoders();
  }

  @Override
  public void execute() {
    for (int i = 0; i < 4; i++) {
      SmartDashboard.putNumber("encoderValue/" + i, m_subsystem.getModule(i).getDriveEncoderValue());
      System.out.println("encoderValue" + i + "  " + m_subsystem.getModule(i).getDriveEncoderValue());
    }
    // Goes through 4 times to set each module to an angle
    for(int i = 0; i < 4; i++) {
      getDriveEncoder = m_subsystem.getAverageDriveEncoderDistance();
      // Checks to see if the module is rotated
      if (Math.abs(rotationDegree) > 0) {
        // If the module is even then the angle is inverted
        if (i % 2 == 0) {
          m_subsystem.getModule(i).setModuleAngle(Math.toRadians(-rotationDegree));
        } else {
          m_subsystem.getModule(i).setModuleAngle(Math.toRadians(rotationDegree));
        }
      } 
      SmartDashboard.putNumber("getDriveEncoder", getDriveEncoder);
      SmartDashboard.putNumber("position", position);
      SmartDashboard.putNumber("endPosition", position - 400);

      // If the drive encoder is greater than position - 400 and less than position + 400 then finish gets set accordingly
      if (Math.abs(getDriveEncoder) > Math.abs(position) - 400 && Math.abs(getDriveEncoder) < Math.abs(position) + 400) {
        if (iteration > 10) {
          finish = true;
        } else {
          finish = false;
        }
        SmartDashboard.putBoolean("finish", finish);
        iteration++;
      } else {
        iteration = 0;
        finish = false;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    //Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getYawMod());
  }

  @Override
  public boolean isFinished() {
    return finish;
  }
}