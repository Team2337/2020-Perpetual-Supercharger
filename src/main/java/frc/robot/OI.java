package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Serializer.*;
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
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4, -0.4));


        //DELETE AFTER TESTING
        operatorJoystick.start . whenPressed(new setSerializerSpeed(Robot.Serializer, 0.7));
        operatorJoystick.start . whenReleased(new stopSerializerMotor(Robot.Serializer));
        operatorJoystick.back . whenPressed(new readyShooter(Robot.Serializer, 4096));
        operatorJoystick.greenA .whenPressed(new setSerializerSpeed(Robot.Serializer, -0.7));
        operatorJoystick.greenA .whenReleased(new stopSerializerMotor(Robot.Serializer));

        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}