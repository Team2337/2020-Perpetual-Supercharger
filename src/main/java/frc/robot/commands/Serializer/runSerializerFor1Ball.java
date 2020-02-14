package frc.robot.commands.Serializer;

import frc.robot.Constants;
import frc.robot.subsystems.Serializer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the serializer speed
 * @author Nicholas Stokes
 */

public class runSerializerFor1Ball extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Serializer subsystem;
  private double serializerSpeed;

  /**
   * Sets the serializer speed
   * 
   * @author Nicholas Stokes
   * @param serializer The subsystem used by this command. (Serializer)
   * @param speed      A double number that sets what speed the motors move at
   */
  public runSerializerFor1Ball(Serializer serializer, double speed) {
    subsystem = serializer;
    serializerSpeed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will set the serializer to run at a set speed
    serializerSpeed = SmartDashboard.getNumber("Serializer Speed", Constants.SERIALIZERPEAKSPEED);
    subsystem.setSerializerSpeed(serializerSpeed);
  }

  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return subsystem.counter.get() >= 1;
  }
  
}
