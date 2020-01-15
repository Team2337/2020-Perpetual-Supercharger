/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import frc.robot.Robot;


import edu.wpi.first.wpilibj2.command.InstantCommand;

public class limeLightLEDOn extends InstantCommand {

  /**
   * Limelight LED will turn on
   * @author Zayd A. & Madison J.
   */
  // CONSTRUCTOR
  public limeLightLEDOn() {
    addRequirements(Robot.Vision);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.Vision.setLEDMode(3);
  }

}