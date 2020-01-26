/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sensor test stuff!
 * @author Nicholas Stokes
 */
public class sensorTest extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  public int counter;
  public boolean previousSensorValue;
  public boolean sensorValue;
  private final Shooter m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public sensorTest(Shooter subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    previousSensorValue = false;
    counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.

  /**
   * @param sensorValue
   * This value is a boolean value that represents what the sensor value is at the moment of triggering
   * @param previousSensorValue
   * This value is pretty simple, what the sensor was 20 milaseconds ago
   * @param counter
   * This is the counter, it counts the values up from zero
   */

  @Override
  public void execute() {
    sensorValue = m_subsystem.shooterSensor.get();
    if (counter < 6){
      if (!sensorValue && previousSensorValue) {
        counter++;
      }
    }
    else {
     counter = 0;
    }
    previousSensorValue = sensorValue;
    
    SmartDashboard.putNumber("Counter Value", counter);
    SmartDashboard.putBoolean("Sensor Value", sensorValue);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
