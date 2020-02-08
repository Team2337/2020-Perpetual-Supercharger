package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Climber.*;
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
        operatorJoystick.triggerRight   .whenPressed(new runIntake(Robot.Intake, Constants.INTAKESPEED));
        operatorJoystick.triggerRight   .whenReleased(new stopIntake(Robot.Intake));

        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft    .whenPressed(new runIntake(Robot.Intake, -Constants.INTAKESPEED));
        operatorJoystick.triggerLeft    .whenReleased(new stopIntake(Robot.Intake));

        operatorJoystick.yellowY        .whenPressed(new runAgitator(Robot.Agitator, Constants.AGITATORSPEED));
        operatorJoystick.yellowY        .whenReleased(new stopAgitatorMotors(Robot.Agitator));

        operatorJoystick.blueX          .whenPressed(new runClimber(Robot.Climber, Constants.CLIMBERSPEED));
        operatorJoystick.blueX          .whenReleased(new stopClimberMotors(Robot.Climber));


        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}