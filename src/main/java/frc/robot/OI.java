package frc.robot;

import frc.robot.commands.Intake.*;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;

public class OI {
    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
	public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
	public NerdyOperatorStation	operatorControls = new NerdyOperatorStation(2);
    public OI(){
        
        /** --- DRIVER JOYSTICK --- */

        //insert code here

        /** --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4));

        /** --- DRIVER STATION CONTROLS --- */

        //insert code here

        /////////////////////////////////////

        /** --- TESTING PURPOSES --- */
        //The right bumper moves the intake motors one way
        operatorJoystick.bumperRight .whenPressed(new adjustIntakeSpeed(Robot.Intake, 0.1));
        //The left bumper moves the intake motors the other way
        operatorJoystick.bumperLeft .whenPressed(new adjustIntakeSpeed(Robot.Intake, -0.1));
        //Stops intake motors
        operatorJoystick.redB .whenPressed(new stopIntakeMotors(Robot.Intake));
        
    }

}