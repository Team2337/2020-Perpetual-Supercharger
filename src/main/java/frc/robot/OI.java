package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Intake.*;
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

        /**
         * operatorJoystick.start .whileHeld(new setSerializerFeederSpeed(Robot.Feeder, 0.4));
         * operatorJoystick.back .whenPressed(new readyShooter(Robot.Feeder, -2*2048));
         * 
         * These are the controller commands for the SerializerFeeder
         * These are commented out because they might be purely autonomous
         * If that happens delete these comments
         * If not, delete these lines and keep the commands
         */
        
    

        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}