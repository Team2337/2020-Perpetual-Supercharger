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
public class zeroWithAnalog extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
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
     * Sets the module angles to the desired rotation angle and rotates the robot a
     * specified direction, either left or right at a set max speed
     * 
     * @param subsystem - SwerveDrivetrain subsystem object
     * @param direction - The direction, left or right, the chassis will go
     * @param angle     - The angle we want the robot to rotate to
     * @param maxSpeed  - The maximum speed we set to the robot
     */
    public zeroWithAnalog(SwerveDrivetrain subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    // Goes through 4 times to set each module to an angle
    for(int i = 0; i < 4; i++) {
          m_subsystem.getModule(i).setAutonModuleAngle(0);
      } 
  }

  @Override
  public void end(boolean interrupted) {
    //Robot.OperatorAngleAdjustment.setOffsetAngle(-Robot.Utilities.getYawMod());
    m_subsystem.zeroAllDriveEncoders();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}