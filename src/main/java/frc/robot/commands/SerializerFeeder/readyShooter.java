package frc.robot.commands.SerializerFeeder;

import frc.robot.subsystems.SerializerFeeder;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Move balls back to ready the kicker wheel so that the robot can shoot
 * @author Nicholas Stokes
 */
public class readyShooter extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final SerializerFeeder m_subsystem;
  public double positionThing;

  /**
   * Creates the ready shooter command
   *
   * @param subsystem The subsystem used by this command. (SerializerFeeder)
   * @param positionThingg What value the command is passed in OI.java
   * @param positionThing is the value the method uses for shifting
   * These values are exactly the same but are named different for Java Reasons
   */
  public readyShooter(SerializerFeeder subsystem,  double positionThingg) {
    m_subsystem = subsystem;
    positionThing = positionThingg;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //This calls the Subsystem method positionShift with a value designated in OI
    m_subsystem.positionShift(positionThing);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}
