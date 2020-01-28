
package frc.robot.commands.Feeder;

import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Adjusts the intake speed
 * 
 * @author Nicholas Stokes
 */
public class adjustFeederSpeed extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Feeder subsystem;
  private double modifier;

  /**
   * Increases the feeder speed by a given amount. The motors do not stop after.
   * 
   * @param feeder     The subsystem used by this command. (Feeder)
   * @param m_modifier A double that the robot changes the speed of the motors by.
   */
  public adjustFeederSpeed(Feeder feeder, double m_modifier) {
    subsystem = feeder;
    modifier = m_modifier;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will change the feeder speed by a set amount designated in constants.
    subsystem.setFeederSpeed(subsystem.getFeederSpeed()[0] + modifier, subsystem.getFeederSpeed()[1] + modifier);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Because this command is for testing purposes, the motors do not stop. Use stopFeederMotors to stop.
  }

}
