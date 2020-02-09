package frc.robot;

import frc.robot.commands.auto.*;
import frc.robot.commands.auto.commandgroups.nineball.CenterGoalBack9BallGenerator2Ball;
import frc.robot.commands.auto.commandgroups.swerveCircle;
import frc.robot.commands.auto.commandgroups.swerveDiamond;
import frc.robot.commands.auto.commandgroups.swerveSquare;
import frc.robot.commands.auto.commandgroups.swerveTriangle;
import frc.robot.commands.swerve.*;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Serializer.*;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.ShooterSystem.*;

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
    public NerdyOperatorStation operatorControls = new NerdyOperatorStation(2 /* Joystick Slot ID */);

    /**
     * OI Class where all controllers and button presses are placed
     */
    public OI() {

        /* --- DRIVER JOYSTICK --- */

        // Sets the field orientation
        // driverJoystick.bumperLeft.whenPressed(new SetFieldOriented(false));
        // driverJoystick.bumperLeft.whenReleased(new SetFieldOriented(true));
        driverJoystick.bumperLeft.whenPressed(new ChangeGyroAngleOffset(Robot.OperatorAngleAdjustment, true));
        driverJoystick.bumperLeft.whenReleased(new ChangeGyroAngleOffset(Robot.OperatorAngleAdjustment, false));

        driverJoystick.greenA.whenPressed(new zeroAngleEncoders(Robot.SwerveDrivetrain));

        driverJoystick.povDown.whileHeld(new turnModulesToDegree(Robot.SwerveDrivetrain, 0, 1.3, 0.3));
        driverJoystick.povUp.whenPressed(new zeroWithAnalog(Robot.SwerveDrivetrain).withTimeout(0.5));

        driverJoystick.blueX.whileHeld(new RotateAtSpeed(Robot.SwerveDrivetrain, "left", -0.07));
        driverJoystick.redB.whileHeld(new RotateAtSpeed(Robot.SwerveDrivetrain, "right", 0.07));

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

        operatorJoystick.blueX.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "climbing"));
        operatorJoystick.redB.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "nearShot"));
        operatorJoystick.yellowY.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "farShot"));
        operatorJoystick.greenA.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "resetZero"));
        operatorJoystick.triggerLeft.whenPressed(new swerveCircle());
        operatorJoystick.triggerRight.whenPressed(new CenterGoalBack9BallGenerator2Ball());

        /* --- DRIVER STATION CONTROLS --- */

        // insert code here

        /////////////////////////////////////
    }

}
