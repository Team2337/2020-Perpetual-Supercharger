package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Command running the swerve calculations with the joystick
 * @see SwerveDrivetrain
 * @author Bryce G.
 * @category SWERVE
 */
public class SwerveDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final SwerveDrivetrain subsystem;

  /**
   * Value from the Y axis on the left joystick, on the driver controller  
   */
	private double forward = 0;

  /**
   * Value from the X axis on the left joystick, on the driver controller  
   */
	private double strafe = 0;

  /**
   * Value from the X axis on the right joystick, on the driver controller  
   */
	private double rotation = 0;


  /**
   * Command running the swerve calculations with the joystick
   * @param subsystem - SwerveDrivetrain subsystem object
   */
  public SwerveDriveCommand(SwerveDrivetrain subsystem) {
    this.subsystem = subsystem;
    addRequirements(subsystem);
  }
  
  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    /* --- Joystick Values --- */
   forward = -Robot.OI.driverJoystick.getLeftStickY(); 
   strafe = Robot.OI.driverJoystick.getLeftStickX(); 
   rotation = -Robot.OI.driverJoystick.getRightStickX();

   // Set Deadband
   forward = Robot.Utilities.deadband(forward, 0.1);
   strafe = Robot.Utilities.deadband(strafe, 0.1);
   rotation = Robot.Utilities.deadband(rotation, 0.1);

   // Smartdashboard prints
   SmartDashboard.putNumber("Forward", forward);
   SmartDashboard.putNumber("Strafe", strafe);
   SmartDashboard.putNumber("Rotation", rotation);
   
   // Pass on joystick values to be calculated into angles and speeds
   subsystem.calculateJoystickInput(forward, strafe, rotation);
  }

  @Override
  public void end(boolean interrupted) {
    // In the event this command stops, we don't want the motors to move
    subsystem.stopAngleMotors();
    subsystem.stopDriveMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}