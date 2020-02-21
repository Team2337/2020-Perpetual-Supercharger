package frc.robot.commands.Vision;

import frc.robot.Robot;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Vision;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
  public class ChameleonVisionDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Vision m_subsystem;
  public double forward;
  public double rotation;
  double current = 0;
  double previous = 0;

  /**
   * ChameleonVision's system will identify anything that is yellow, typically balls, and give coordinates on how far it is
   * @author Zayd A.
   * @param subsystem - Ball tracking system that identifies the balls and get them
   */
  public ChameleonVisionDrive(Vision subsystem) {
  
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Receives the distance and motors will move accordingly
    current = Robot.Vision.getChameleonVisionXDistance();
    double p = 0.05; 
    rotation = Robot.Vision.calculateMotorSpeed(current, p);
    SmartDashboard.putNumber("Rotation", rotation);
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
