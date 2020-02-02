package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Hopper.*;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;

/**
 * OI Class where all controllers and button presses are placed 
 */
public class OI {
    
    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
	public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
    public NerdyOperatorStation	operatorControls = new NerdyOperatorStation(2);
    
    public OI(){
        
        /* --- DRIVER JOYSTICK --- */

        // Sets the field orientation
        driverJoystick.bumperLeft.whenPressed(new SetFieldOriented(Robot.SwerveDrivetrain, false));
        driverJoystick.bumperLeft.whenReleased(new SetFieldOriented(Robot.SwerveDrivetrain, true));

        /* --- OPERATOR JOYSTICK --- */
        
        // Sets the intake motors to intake balls
        operatorJoystick.bumperRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4, 0.4));
        // Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.bumperLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4, -0.4));

        // Extends and retracts left flipper on hopper to grab power cells and feed them into the intake
        operatorJoystick.macroOne   .whileHeld(new leftHopperExtension(Robot.Hopper));
        // Extends and retracts right flipper on hopper to grab power cells and feed them into the intake
        operatorJoystick.macroTwo  .whileHeld(new rightHopperExtension(Robot.Hopper));
        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}