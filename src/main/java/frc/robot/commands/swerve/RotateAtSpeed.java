package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the module angles to the desired rotation angle and rotates the robot for a specified number of degrees at a certain speed
 * @author Madison J.
 * @category AUTON
 */
public class RotateAtSpeed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SwerveDrivetrain m_subsystem;
  /* --- Doubles --- */
  private double rotationDegree = 45;
  private double kP = 1;
  private double speed;
  /* --- Booleans --- */
  private boolean finished = false;

/**
 * Sets the module angles to the desired rotation angle and rotates the robot for a specified number of degrees at a certain speed
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param direction - The direction we want to rotate, left or right
 * @param speed - The speed of the robot that we set
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

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    // Goes through 4 times to put our encoder values on smart dashboard
    for (int i = 0; i < 4; i++) {
    SmartDashboard.putNumber("encoderValue/" + i, m_subsystem.getModule(i).getDriveEncoderValue());
    }
    // Goes through 4 times and sets modules 1 and 2 to negative speed and modules 0 and 3 to positive speed
    for(int i = 0; i < 4; i++) {
      if (i > 0 && i < 3) {
        m_subsystem.getModule(i).setDriveSpeed(-speed);
      } else {
        m_subsystem.getModule(i).setDriveSpeed(speed);
      }
      // Checks to see if the modules are rotating
      if (Math.abs(rotationDegree) > 0) {
        // If the module is even then the rotation degree is negative otherwise it is positive
        if (i % 2 == 0) {
          m_subsystem.getModule(i).setModuleAngle(Math.toRadians(-rotationDegree));
        } else {
          m_subsystem.getModule(i).setModuleAngle(Math.toRadians(rotationDegree));
        }
      } 
    }
    finished = speed == 0 ? true: false;
    finished = Robot.OI.driverJoystick.getRawButtonReleased(2) || Robot.OI.driverJoystick.getRawButtonReleased(3) ? true: false;
  }

  @Override
  public void end(boolean interrupted) {
    // Sets the offset angle to a negative yaw
   Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getYawMod()); 
    // Goes through 4 times and sets the modules speed to zero
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