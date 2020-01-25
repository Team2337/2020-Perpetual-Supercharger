package frc.robot;

import frc.robot.commands.Feeder.*;
import frc.robot.commands.Intake.*;
import frc.robot.nerdyfiles.controller.NerdyXbox;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;

public class OI {
    public NerdyXbox joystick = new NerdyXbox(0);
    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
	public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
	public NerdyOperatorStation	operatorControls = new NerdyOperatorStation(2);
    public OI(){
        
        // --- DRIVER JOYSTICK --- //

        //insert code here

        // --- OPERATOR JOYSTICK --- //

        //insert code here

        // --- DRIVER STATION CONTROLS --- //

        //insert code here

        /////////////////////////////////////

        // === TESTING PURPOSES === //
        //The right bumper moves the intake motors one way
        joystick.bumperRight .whenPressed(new adjustIntakeSpeed(Robot.Intake, 0.1));
        //The left bumper moves the intake motors the other way
        joystick.bumperLeft .whenPressed(new adjustIntakeSpeed(Robot.Intake, -0.1));

        // === DRIVING PURPOSES === //
        //Stops intake motors
        joystick.redB .whenPressed(new stopIntakeMotors(Robot.Intake));
        //Sets the intake motors to intake balls
        joystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        joystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4));
        
        //Sets the feeder motor to intake balls into the serializer
        joystick.greenA. whileHeld(new setFeederSpeed(Robot.Feeder, 0.4));
    }

}