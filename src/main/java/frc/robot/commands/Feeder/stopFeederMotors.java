/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.Feeder;
import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj2.command.InstantCommand;
/**
 * Stops the feeder motors.
 * @author Nicholas Stokes
 */
public class stopFeederMotors extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Feeder subsystem;

  /**
   * Stops the feeder motors.
   * @param subsystem The subsystem used by this command. (Feeder)
   */
  public stopFeederMotors(Feeder feeder) {
    subsystem = feeder;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // This will stop the feeder
    subsystem.stopFeeder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }


}
