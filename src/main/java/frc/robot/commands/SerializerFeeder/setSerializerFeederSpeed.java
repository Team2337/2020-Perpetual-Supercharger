package frc.robot.commands.SerializerFeeder;

import frc.robot.subsystems.SerializerFeeder;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the serializer speed
 * 
 * @author Nicholas Stokes
 */
public class setSerializerFeederSpeed extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final SerializerFeeder subsystem;
  private double serializerFeederSpeed;

  /**
   * Sets the feeder speed to a given percent
   * 
   * @param feeder The subsystem used by this command. (Feeder)
   * @param speed  A double number that sets what speed the motors move at
   */
  public setSerializerFeederSpeed(SerializerFeeder serializer, double speed) {
    subsystem = serializer;
    serializerFeederSpeed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the feeder to run at a set speed
    subsystem.setFeederSpeed(serializerFeederSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the feeder
    subsystem.stopFeeder();
  }

}
