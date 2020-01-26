package frc.robot;

import frc.robot.commands.leftHopperExtension;
import frc.robot.commands.rightHopperExtension;
import frc.robot.commands.Intake.*;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;

public class OI {
    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
	public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
	public NerdyOperatorStation	operatorControls = new NerdyOperatorStation(2);
    public OI(){
        
        /* --- DRIVER JOYSTICK --- */

        //insert code here

        // --- OPERATOR JOYSTICK --- //
        
        // Sets the intake motors to intake balls
        operatorJoystick.bumperRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4, 0.4));
        // Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.bumperLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4, -0.4));

        // Extends and retracts left flipper on hopper to grab power cells and feed them into the intake
        operatorJoystick.triggerLeft   .whileHeld(new leftHopperExtension(Robot.Hopper));
        // Extends and retracts right flipper on hopper to grab power cells and feed them into the intake
        operatorJoystick.triggerRight  .whileHeld(new rightHopperExtension(Robot.Hopper));
        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}