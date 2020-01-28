/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Drivecam will be turned on
 * @author Zayd A. & Madison J.
 * @category VISION
 */
public class switchDriverCam extends InstantCommand {
  private Vision subsystem;
  /**
   * Drivecam will be turned on
   */
  public switchDriverCam(Vision subsystem) {
    this.subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    subsystem.switchPipeLine(9);
  }
}