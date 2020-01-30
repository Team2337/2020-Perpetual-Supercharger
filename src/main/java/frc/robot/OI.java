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
        /* 
        driverJoystick.bumperLeft.whenPressed(new SetFieldOriented(Robot.SwerveDrivetrain, false));
        driverJoystick.bumperLeft.whenReleased(new SetFieldOriented(Robot.SwerveDrivetrain, true));
        */
        driverJoystick.bumperLeft.whenPressed(new ChangeGyroAngleOffset(Robot.OperatorAngleAdjustment, true));
        driverJoystick.bumperLeft.whenReleased(new ChangeGyroAngleOffset(Robot.OperatorAngleAdjustment, false));
        /* --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4, -0.4));

        operatorJoystick.blueX.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "climbing"));
        operatorJoystick.redB.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "nearShot"));
        operatorJoystick.yellowY.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "farShot"));
        operatorJoystick.greenA.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "resetZero"));

        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}