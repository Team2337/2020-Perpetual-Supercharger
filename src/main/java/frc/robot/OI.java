package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Serializer.*;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;
import frc.robot.commands.Shooter.*;

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

        //Run the shooter
        driverJoystick.triggerRight .whileHeld(new startShooter(Robot.Shooter, Constants.SHOOTSPEEDFAR));
        driverJoystick.triggerLeft  .whileHeld(new startShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE));

        /* --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4, -0.4));
        // Sets the serializer motor to move up and stop when released
        operatorJoystick.povUp .whenPressed(new setSerializerSpeed(Robot.Serializer, 0.5));
        operatorJoystick.povUp . whenReleased(new stopSerializerMotor(Robot.Serializer));
        // Readies the shooter to get the kicker wheel up to speed
        operatorJoystick.povRight .whenPressed(new readyShooter(Robot.Serializer, 4096));
        //Sets the serializer motor to move down and stop when released
        operatorJoystick.povDown .whenPressed(new setSerializerSpeed(Robot.Serializer, -0.5));
        operatorJoystick.povDown .whenReleased(new stopSerializerMotor(Robot.Serializer));
        
        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}
