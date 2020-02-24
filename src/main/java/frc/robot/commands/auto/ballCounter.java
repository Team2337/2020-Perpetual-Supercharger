package frc.robot.commands.auto;

import frc.robot.Constants.Swerve;
import frc.robot.subsystems.OperatorAngleAdjustment;
import frc.robot.subsystems.SwerveDrivetrain;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Sets the module angles to the desired rotation angle and rotates the robot a specified direction, either left or right
 * @author Madison J.
 * @category AUTON
 */
public class ballCounter extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final SubsystemBase m_subsystem;
    private Counter counter;
    private DigitalInput digitalSensor;
    private double maxPeriod;
    private boolean finished;
    private int maxBallCount;

    public ballCounter(SubsystemBase subsystem, DigitalInput digitalSensor, double maxPeriod, int maxBallCount) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    counter = new Counter(1);
    this.digitalSensor = digitalSensor;
    this.maxPeriod = maxPeriod;
    this.maxBallCount = maxBallCount - 1;
  }

  @Override
  public void initialize() {
    counter.setUpDownCounterMode();
    counter.setDownSource(digitalSensor);
    counter.setMaxPeriod(maxPeriod);
  }

  @Override
  public void execute() {
    if (counter.get() >= maxBallCount) {
      finished = true;
    } else {
      finished = false;
    }
  }

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}