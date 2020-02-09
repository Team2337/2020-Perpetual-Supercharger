package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Serializer.*;
import frc.robot.commands.Hopper.*;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.ShooterSystem.*;

/**
 * OI Class where all controllers and button presses are placed 
 */
public class OI {
    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
    public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
    public NerdyOperatorStation operatorControls = new NerdyOperatorStation(2);

    public OI() {

        /* --- DRIVER JOYSTICK --- */

        // Sets the field orientation
        driverJoystick.bumperLeft.whenPressed(new SetFieldOriented(Robot.SwerveDrivetrain, false));
        driverJoystick.bumperLeft.whenReleased(new SetFieldOriented(Robot.SwerveDrivetrain, true));

        //Run the shooter
        driverJoystick.triggerRight .whileHeld(new startShooter(Robot.Shooter, Constants.SHOOTSPEEDFAR));
        driverJoystick.triggerLeft  .whileHeld(new startShooter(Robot.Shooter, Constants.SHOOTSPEEDCLOSE));

        /* --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight   .whenPressed(new runIntake(Robot.Intake, Constants.INTAKESPEED));
        operatorJoystick.triggerRight   .whenReleased(new stopIntake(Robot.Intake));

        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft    .whenPressed(new runIntake(Robot.Intake, -Constants.INTAKESPEED));
        operatorJoystick.triggerLeft    .whenReleased(new stopIntake(Robot.Intake));

        // Run the agitator leftwards
        operatorJoystick.yellowY        .whenPressed(new runAgitator(Robot.Agitator, Constants.AGITATORSPEED));
        operatorJoystick.yellowY        .whenReleased(new stopAgitator(Robot.Agitator));

        // Move the climber upwards
        operatorJoystick.blueX          .whenPressed(new runClimber(Robot.Climber, Constants.CLIMBERSPEED));
        operatorJoystick.blueX          .whenReleased(new stopClimber(Robot.Climber));

        // Sets the kicker wheel's speed
        operatorJoystick.greenA         .whenPressed(new runKicker(Robot.KickerWheel, Constants.KICKERSPEED));
        operatorJoystick.greenA         .whenReleased(new stopKicker(Robot.KickerWheel));

        // Prepare the shooter to fire long range
        operatorJoystick.redB           .whenPressed(new longShooterSystemOn());
        operatorJoystick.redB           .whenReleased(new shooterSystemOff());
        
        // Holds the kicker wheel's position
        operatorJoystick.start          .whenPressed(new holdKickerPosition(Robot.KickerWheel));

        // Sets the serializer motor to move up and stop when released
        operatorJoystick.povUp          .whenPressed(new runSerializer(Robot.Serializer, Constants.SERIALIZERPEAKSPEED));
        operatorJoystick.povUp          .whenReleased(new stopSerializer(Robot.Serializer));
        
        // Readies the shooter to get the kicker wheel up to speed
        operatorJoystick.povRight       .whenPressed(new readyShooter(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE));
        
        //Sets the serializer motor to move down and stop when released
        operatorJoystick.povDown        .whenPressed(new runSerializer(Robot.Serializer, -Constants.SERIALIZERPEAKSPEED));
        operatorJoystick.povDown        .whenReleased(new stopSerializer(Robot.Serializer));
        
        // Run the feeding system towards the shooter
        operatorJoystick.povLeft        .whenPressed(new feedSystemForward());
        operatorJoystick.povLeft        .whenReleased(new feedSystemStop());

        /* Extends and retracts left flipper on hopper to grab power cells and feed them into the intake
        operatorJoystick.macroOne   .whileHeld(new leftHopperExtension(Robot.Hopper));
        // Extends and retracts right flipper on hopper to grab power cells and feed them into the intake
        operatorJoystick.macroTwo  .whileHeld(new rightHopperExtension(Robot.Hopper));
        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}
