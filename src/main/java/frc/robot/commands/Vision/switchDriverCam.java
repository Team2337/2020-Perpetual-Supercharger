/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.InstantCommand;

  /**
   * Drivecam will be turned on
   * @author Zayd A. & Madison J.
   */
public class switchDriverCam extends InstantCommand {
  
  // CONSTRUCTOR
  public switchDriverCam() {
    addRequirements(Robot.Vision);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {

  }
}