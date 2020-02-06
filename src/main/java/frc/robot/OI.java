package frc.robot;

import frc.robot.commands.auto.*;
import frc.robot.commands.auto.commandgroups.nineball.CenterGoalBack9BallGenerator2Ball;
import frc.robot.commands.auto.commandgroups.swerveCircle;
import frc.robot.commands.auto.commandgroups.swerveDiamond;
import frc.robot.commands.auto.commandgroups.swerveSquare;
import frc.robot.commands.auto.commandgroups.swerveTriangle;
import frc.robot.commands.swerve.*;
import frc.robot.nerdyfiles.controller.*;

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
        driverJoystick.bumperLeft.whileHeld(new turnModulesToDegree(Robot.SwerveDrivetrain, 0, 1.3));
        driverJoystick.bumperRight.whenPressed(new zeroWithAnalog(Robot.SwerveDrivetrain).withTimeout(0.5));

        /* --- OPERATOR JOYSTICK --- */

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