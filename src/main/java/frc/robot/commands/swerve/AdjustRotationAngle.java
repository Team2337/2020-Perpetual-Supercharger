package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.Constants.Swerve;
import frc.robot.subsystems.SwerveDrivetrain;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the module angles to the desired rotation angle and adjusts our rotation
 * @author Madison J.
 * @category AUTON
 */
public class AdjustRotationAngle extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  /* --- Integers --- */
  private int position = (int) (1* Swerve.TICKSPERINCH);
  /* --- Doubles --- */
  private double rotationDegree = 45;
  private double kP = 1;

/**
 * Sets the module angles to the desired rotation angle and adjusts our rotation
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param direction - The direction we want to rotate, left or right
 */
  public AdjustRotationAngle(SwerveDrivetrain subsystem, String direction) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    /* --- Parameters Being Set to Global Variables --- */
    switch (direction) {
      case "right":
      position = position;
      break;
      case "left":
      position = -position;
    }
  }

  @Override
  public void initialize() {
    // Goes through 4 times to configure and set the module angles to either a positive position or a negative position
    for(int i = 0; i < 4; i++) {
      m_subsystem.getModule(i).TalonFXConfigurationDrive.slot0.kP = kP;
      // Sets our allowable error equal to 50
      m_subsystem.getModule(i).TalonFXConfigurationDrive.slot0.allowableClosedloopError = 50;
      // Configures the drive motors
      m_subsystem.getModule(i).driveMotor.configAllSettings(m_subsystem.getModule(i).TalonFXConfigurationDrive, 0);
      // Checks to see if the modules are rotating
      if (Math.abs(rotationDegree) > 0) {
        // Checks to see if it is module 1 or 2 and inverts their position so they will go in the opposite direction
        if (i > 0 && i < 3) {
          m_subsystem.getModule(i).setAngleSetpoint(-position);
        } else {
          m_subsystem.getModule(i).setAngleSetpoint(position);
        }
      } else {
        // Sets coast mode
        m_subsystem.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
      }
    }
      // Zeros all of the drive encoders
      m_subsystem.zeroAllDriveEncoders();
  }

  @Override
  public void execute() {
    // Goes through 4 times and prints the enocder value to smart dashboard
    for (int i = 0; i < 4; i++) {
    SmartDashboard.putNumber("encoderValue/" + i, m_subsystem.getModule(i).getDriveEncoderValue());
    System.out.println("encoderValue" + i + "  " + m_subsystem.getModule(i).getDriveEncoderValue());
    }
    // Goes through 4 times to rotate the modules either in a positive direction or a negative one
    for(int i = 0; i < 4; i++) {
      // Checks to see if the modules are rotating
      if (Math.abs(rotationDegree) > 0) {
        // If the module is even then rotation degree is set to negative
        if (i % 2 == 0) {
          m_subsystem.getModule(i).setModuleAngle(Math.toRadians(-rotationDegree));
        } else {
          m_subsystem.getModule(i).setModuleAngle(Math.toRadians(rotationDegree));
        }
      } 
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getYawMod());
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}