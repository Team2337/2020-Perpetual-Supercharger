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
public class RotateAtSpeed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  /* --- Integers --- */
  /* --- Doubles --- */
  private double rotationDegree = 45;
  private double kP = 1;
  private double speed;
  private boolean finished = false;
/**
 * Sets the module angles to the desired rotation angle and rotates the robot for a specified number of degrees
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param position - The desired distance to rotate in inches (49 inches = 180 degrees)
 * @param rotationDegree - The angle each module is being set to
 * @param kP - The value that the error is multiplied by to get our speed
 */
  public RotateAtSpeed(SwerveDrivetrain subsystem, String direction, double speed) {
    m_subsystem = subsystem;
    addRequirements(subsystem, Robot.OperatorAngleAdjustment);
    /* --- Parameters Being Set to Global Variables --- */
    this.speed = speed;
    switch (direction) {
      case "right":
      speed = speed;
      break;
      case "left":
      speed = -speed;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      // Zeros all of the drive encoders
     // m_subsystem.zeroAllDriveEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    for (int i = 0; i < 4; i++) {
    SmartDashboard.putNumber("encoderValue/" + i, m_subsystem.getModule(i).getDriveEncoder());
    //System.out.println("encoderValue" + i + "  " + m_subsystem.getModule(i).getDriveEncoder());
    }
    // Goes through 4 times to set each module to an angle
    for(int i = 0; i < 4; i++) {
      if (i > 0 && i < 3) {
        m_subsystem.getModule(i).setDriveSpeed(-speed);
      } else {
        m_subsystem.getModule(i).setDriveSpeed(speed);
      }
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
    finished = speed == 0 ? true: false;
    finished = Robot.OI.driverJoystick.getRawButtonReleased(2) || Robot.OI.driverJoystick.getRawButtonReleased(3) ? true: false;
    //System.out.println(finished);
  }

  @Override
  public void end(boolean interrupted) {
   Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getYawMod()); //-Robot.Utilities.getYawMod()
    for (int i = 0; i < 4; i++) {
      Robot.SwerveDrivetrain.getModule(i).setDriveSpeed(0);
      System.out.println("**********************************************************************************");
    }
    System.out.println("end");
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}