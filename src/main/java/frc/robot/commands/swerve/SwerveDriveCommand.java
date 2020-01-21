package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Command runnign the swerve calculations with the joystick
 * @see SwerveDrivetrain
 * @author Bryce G.
 */
public class SwerveDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final SwerveDrivetrain swerveDrivetrain;

	private double forward = 0;
	private double strafe = 0;
	private double rotation = 0;


  /**
   * Command runnign the swerve calculations with the joystick
   */
  public SwerveDriveCommand(SwerveDrivetrain subsystem) {
    this.swerveDrivetrain = subsystem;
    addRequirements(subsystem);
  }
  
  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    /* --- Joystick Values --- */
   forward = -Robot.OI.driverJoystick.getRawAxis(1); 
   strafe = Robot.OI.driverJoystick.getRawAxis(0); 
   rotation = -Robot.OI.driverJoystick.getRawAxis(4); 

   // Square Joystick Inputs
   Robot.Utilities.squareValues(forward);
   Robot.Utilities.squareValues(strafe);
   Robot.Utilities.squareValues(rotation);

   // Set Deadband
   Robot.Utilities.deadband(forward, 0.1);
   Robot.Utilities.deadband(strafe, 0.1);
   Robot.Utilities.deadband(rotation, 0.1);

   // Smartdashboard prints
   SmartDashboard.putNumber("Forward", forward);
   SmartDashboard.putNumber("Strafe", strafe);
   SmartDashboard.putNumber("Rotation", rotation);
   
   // Pass on joystick values to be calculated into angles and speeds
   Robot.SwerveDrivetrain.calculateJoystickInput(forward, strafe, rotation);
  }

  @Override
  public void end(boolean interrupted) {
    // In the event this command stops, we don't want the motors to move
    swerveDrivetrain.stopAngleMotors();
    swerveDrivetrain.stopDriveMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
