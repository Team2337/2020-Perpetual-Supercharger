package frc.robot.commands.Serializer;

import frc.robot.Robot;
import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Move balls back to ready the kicker wheel so that the kicker wheel can get up
 * to speed
 * 
 * @author Nicholas Stokes
 */
public class adjustSerializer extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer m_subsystem;
  public double position;
  public double target;
  public double tolerance = 15;
  public int withinTolerance = 0;

  /**
   * Move balls back to ready the kicker wheel so that the kicker wheel can get up
   * to speed
   *
   * @param subsystem  The subsystem used by this command. (Serializer)
   * @param adjustment What value the command is passed in OI.java position is the
   *                   position = value the method uses for shifting
   */
  public adjustSerializer(Serializer subsystem, double adjustment) {
    m_subsystem = subsystem;
    position = adjustment;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    target = m_subsystem.getSerializerPosition() + position;
    m_subsystem.setPosition(target);
  
  }

  @Override
  public void execute() {
    if(Robot.Utilities.withinTolerance(target, m_subsystem.getSerializerPosition(), tolerance)){
      withinTolerance++;
    } else {
      withinTolerance = 0;
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopSerializer();
  }

  @Override
  public boolean isFinished() {
    return withinTolerance >= 10;
  }

}
