/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Serializer;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 *  @author Hunter B. & John R. 
**/
public class cellHolderClose extends CommandBase {

  // Activate pnumatic to hold power cell before shooting.
  public cellHolderClose() {
    addRequirements(Robot.CellHolder);
  }

  // This function is called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.CellHolder.cellHolderClose();
  }

  // This is called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }

}
