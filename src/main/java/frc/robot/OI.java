package frc.robot;

import frc.robot.commands.Shooter.*;
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

        /* --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4, -0.4));

        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
        ///////////////////////////////////////////////////////////////////////

        /* --- TEST CODE --- */

        operatorJoystick.greenA. whileHeld(new CGSequentialShooter(Robot.Shooter, 15265, 3));
        operatorJoystick.redB. whileHeld(new sensorTest(Robot.Shooter));
        operatorJoystick.bumperRight .whileHeld(new shootContinuously(Robot.Shooter, 15295));
        operatorJoystick.bumperLeft .whenPressed(new shootSingleBall(Robot.Shooter, 15295));
    }

}