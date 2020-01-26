/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Feeder;

import frc.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Adjusts the intake speed
 * @author Nicholas Stokes
 */
public class adjustFeederSpeed extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Feeder subsystem;
  private double modifier;

  /**
   * Increases the feeder speed by a given amount. The motors do not stop after.
   * @param feeder The subsystem used by this command. (Feeder)
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
    // This will change the feeder speed by a set amount
    subsystem.setFeederSpeed(subsystem.getFeederSpeed()[0] + modifier, 
     subsystem.getFeederSpeed()[1] + modifier);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Because this command is for testing purposes, the motors do not stop. Use stopIntakeMotors to stop.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
