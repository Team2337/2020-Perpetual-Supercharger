/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.nerdyfiles.controller.*;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.Vision.*;

public class OI {
  /**
   * Creates a new ExampleSubsystem.
   */
  public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
  public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
  public NerdyOperatorStation operatorControls = new NerdyOperatorStation(2);

  public OI() {
    driverJoystick.blueX.whenPressed(new limeLightLEDOff());
    driverJoystick.greenA.whenPressed(new limeLightLEDOn());
    driverJoystick.redB.whenPressed(new limeLightLEDBlink());
    driverJoystick.yellowY.whenPressed(new limelightPipeline());



  }
  public Joystick getDriverJoystick() {
		return driverJoystick;
	}

	public Joystick getOperatorJoystick() {
		return operatorJoystick;
	}

	public Joystick getOperatorControls() {
		return operatorControls;
	}
  
}
