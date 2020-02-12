package frc.robot.commands.swerve;

import frc.robot.Robot;
import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Takes the preset angle offset and the robot goes to that angle using the limelight
 * @author Madison J.
 * @category AUTON
 */
public class ChangeGyroAngleOffset extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final OperatorAngleAdjustment m_subsystem;
  /* --- Booleans --- */
  private boolean isRotating;

/**
 * Takes the preset angle offset and the robot goes to that angle using the limelight
 * @param subsystem - SwerveDrivetrain subsystem object
 * @param isRotating - Tells whether or not the robot is rotating
 */
  public ChangeGyroAngleOffset(OperatorAngleAdjustment subsystem, boolean isRotating) {
    m_subsystem = subsystem;
    /* --- Parameters Being Set to Global Variables --- */
    this.isRotating = isRotating;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    // Sets the offset angle to the gyro angle offset
    Robot.OperatorAngleAdjustment.setOffsetAngle(Robot.OperatorAngleAdjustment.getGyroAngleOffset());
    Robot.OperatorAngleAdjustment.setIsChangingGyroAngle(isRotating);
    // If the robot is rotating 
   /*  if (Robot.Vision.getRotateLimelight() && isRotating) {
      // Turns the limelight on and sets the limelight rotation mode
      Robot.Vision.setLEDMode(3);
      Robot.OperatorAngleAdjustment.setLimelightRotationMode(true);
    } else {
      // Turns the limelight off and turns off the limelight rotation mode
      Robot.Vision.setLEDMode(0);
      Robot.OperatorAngleAdjustment.setLimelightRotationMode(false);
    }
 */
    /* if (!isRotating) {
      // If the limelight is on target then set the offset angle and stop rotating
      if (Robot.OperatorAngleAdjustment.getMode().equals("targetLimelightOn")) {
        // Sets the offset angle
        Robot.OperatorAngleAdjustment.setOffsetAngle(Robot.SwerveDrivetrain.getYaw()); // TODO: 
        // Turns off limelight rotation mode
        Robot.OperatorAngleAdjustment.setLimelightRotationMode(false);
      }
    } */
  }

  @Override
  public void end(boolean interrupted) {
    // Robot.Vision.setLEDMode(0);
  }

}
