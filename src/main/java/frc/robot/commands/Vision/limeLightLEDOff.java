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
   * Limelight LED will turn off
   * <p><br/>Mode 1 turns off the LEDs</p>
   * @author Zayd A. & Madison J.
   * @category VISION
   */
public class limeLightLEDOff extends InstantCommand {
  private Vision subsystem;

  /**
   * Limelight LED will turn off
   * <p><br/>Mode 1 turns off the LEDs</p>
   */
  public limeLightLEDOff(Vision subsystem) {
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    subsystem.setLEDMode(1);
  }

}