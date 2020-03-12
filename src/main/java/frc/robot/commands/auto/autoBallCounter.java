package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Counts the number of balls we have based on how many times the sensor is tripped
 * @author Madison J.
 * @category AUTON
 */
public class autoBallCounter extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final SubsystemBase m_subsystem;
    private DigitalInput digitalSensor;
    private double maxPeriod;
    private boolean finished;
    private boolean hadBall = false;
    private int maxBallCount;
    private int ballCount = 0;
    private int iteration = 0;

    public autoBallCounter(SubsystemBase subsystem, DigitalInput digitalSensor, double maxPeriod, int maxBallCount) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    this.digitalSensor = digitalSensor;
    this.maxPeriod = maxPeriod;
    this.maxBallCount = maxBallCount;
  }
  
  @Override
  public void initialize() {
    ballCount = 0;
    iteration = 0;
  }

  @Override
  public void execute() {
    if(digitalSensor.get()) {
      hadBall = true;
    }
    if(hadBall && !digitalSensor.get()) {
      ballCount++;
      hadBall = false;
    }

    if (ballCount >= maxBallCount) {
      if(iteration > 5) {
        finished = true;
      } 
      iteration++;
    } else {
      finished = false;
    }

    SmartDashboard.putNumber("Ball Count", ballCount);
    SmartDashboard.putBoolean("Had Ball", hadBall);
  }

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}