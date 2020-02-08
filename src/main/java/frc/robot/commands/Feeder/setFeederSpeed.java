package frc.robot.commands.Feeder;

import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets the feeder speed
 * 
 * @author Nicholas Stokes
 */
public class setFeederSpeed extends InstantCommand {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Feeder subsystem;
  private double rightFeederSpeed;
  private double leftFeederSpeed;

  /**
   * Sets the feeder speed to a given percent
   * 
   * @param feeder The subsystem used by this command. (Feeder)
   * @param speed  A double number that sets what speed the motors move at
   */
  public setFeederSpeed(Feeder feeder, double lSpeed, double rSpeed) {
    subsystem = feeder;
    rightFeederSpeed = rSpeed;
    leftFeederSpeed = lSpeed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the feeder to run at a set speed
    subsystem.setFeederSpeed(leftFeederSpeed, rightFeederSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the feeder
    subsystem.stopFeeder();
  }

}