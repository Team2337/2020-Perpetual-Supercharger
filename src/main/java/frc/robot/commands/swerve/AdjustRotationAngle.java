package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.Constants.Swerve;
import frc.robot.subsystems.SwerveDrivetrain;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the module angles to the desired rotation angle and rotates the robot for a specified number of degrees
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
 * Sets the module angles to the desired rotation angle and rotates the robot for a specified number of degrees
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param position - The desired distance to rotate in inches (49 inches = 180 degrees)
 * @param rotationDegree - The angle each module is being set to
 * @param kP - The value that the error is multiplied by to get our speed
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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Goes through 4 times to get the angle of each module
    for(int i = 0; i < 4; i++) {
      // Resests the drive configuration 
      m_subsystem.getModule(i)._fx.slot0.kP = kP;
      m_subsystem.getModule(i)._fx.slot0.allowableClosedloopError = 50;
      m_subsystem.getModule(i).driveMotor.configAllSettings(m_subsystem.getModule(i)._fx, 0);
      // Checks to see if the modules are rotating
      if (Math.abs(rotationDegree) > 0) {
        // Checks to see if it is module 1 or 2 and inverts their position so they will go in the opposite direction
        if (i > 0 && i < 3) {
          m_subsystem.getModule(i).setSetpoint(-position);
        } else {
          m_subsystem.getModule(i).setSetpoint(position);
        }
      } else {
        m_subsystem.getModule(i).driveMotor.setNeutralMode(NeutralMode.Coast);
      }
    }
      // Zeros all of the drive encoders
      m_subsystem.zeroAllDriveEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    for (int i = 0; i < 4; i++) {
    SmartDashboard.putNumber("encoderValue/" + i, m_subsystem.getModule(i).getDriveEncoder());
    System.out.println("encoderValue" + i + "  " + m_subsystem.getModule(i).getDriveEncoder());
    }
    // Goes through 4 times to set each module to an angle
    for(int i = 0; i < 4; i++) {
      // Checks to see if the module is rotated
      if (Math.abs(rotationDegree) > 0) {
        // If the module is even then the angle is inverted
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