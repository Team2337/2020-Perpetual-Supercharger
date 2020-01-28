package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Intake.*;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;

/**
 * OI Class where all controllers and button presses are placed 
 */
public class OI {

    /**
     * Driver Joystick Object (NerdyUltimateXboxDriver object)
     */
    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0 /* Joystick Slot ID */);
    /**
     * Driver Joystick Object (NerdyUltimateXboxDriver object)
     */
    public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1 /* Joystick Slot ID */);
    /**
     * Driver Joystick Object (NerdyUltimateXboxDriver object)
     */
    public NerdyOperatorStation	operatorControls = new NerdyOperatorStation(2 /* Joystick Slot ID */);
    
    /**
     * OI Class where all controllers and button presses are placed 
     */
    public OI(){
        
        /* --- DRIVER JOYSTICK --- */

        // Sets the field orientation
        driverJoystick.bumperLeft.whenPressed(new SetFieldOriented(false));
        driverJoystick.bumperLeft.whenReleased(new SetFieldOriented(true));

        /* --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4, -0.4));

        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}