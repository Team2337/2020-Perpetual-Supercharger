package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Serializer.*;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.ShooterSystem.*;
import frc.robot.commands.Vision.limeLightLEDBlink;

/**
 * OI Class where all controllers and button presses are placed 
 */
public class OI {
    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
    public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
    public NerdyOperatorStation operatorControls = new NerdyOperatorStation(2);

    public OI() {

        /* --- DRIVER JOYSTICK --- */

        // Sets the robots rotation angle offset, while the button is being pressed
        driverJoystick.bumperLeft.whenPressed(new ChangeGyroAngleOffset(Robot.OperatorAngleAdjustment, true));
        driverJoystick.bumperLeft.whenReleased(new ChangeGyroAngleOffset(Robot.OperatorAngleAdjustment, false));

        //Run the shooter
        driverJoystick.triggerRight .whenPressed(new feedSystemForward());
        driverJoystick.triggerRight.whenReleased(new feedSystemStop());

        // Slow rotates to the right
        driverJoystick.redB         .whenPressed(new setSlowRotateMode(Robot.OperatorAngleAdjustment, true, -Constants.Swerve.SLOWROTATESPEED));
        driverJoystick.redB         .whenReleased(new setSlowRotateMode(Robot.OperatorAngleAdjustment, false, 0));
        
        // Slow rotates to the left
        driverJoystick.blueX         .whenPressed(new setSlowRotateMode(Robot.OperatorAngleAdjustment, true, Constants.Swerve.SLOWROTATESPEED));
        driverJoystick.blueX         .whenReleased(new setSlowRotateMode(Robot.OperatorAngleAdjustment, false, 0));

        driverJoystick.povUp.whenPressed(new ResetGyro(Robot.Pigeon));


        /* --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight   .whenPressed(new runIntake(Robot.Intake, Constants.INTAKESPEED));
        operatorJoystick.triggerRight   .whenReleased(new stopIntake(Robot.Intake));

        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.bumperRight    .whenPressed(new runIntake(Robot.Intake, -Constants.INTAKESPEED));
        operatorJoystick.bumperRight    .whenReleased(new stopIntake(Robot.Intake));

        operatorJoystick.triggerLeft.whenPressed(new ConditionalCommand(new CommandBase() {
        }, new feedSystemForward(), Robot.Shooter.shooterAtVelocityBooleanSupplier));
        operatorJoystick.triggerLeft.whenReleased(new feedSystemStop());

        operatorJoystick.bumperLeft.whenPressed(new feedSystemReverse());
        operatorJoystick.bumperLeft.whenReleased(new feedSystemStop());

         // Run the agitator leftwards
        operatorJoystick.rightStickButton        .whenPressed(new runAgitator(Robot.Agitator, Constants.AGITATORSPEED));
        operatorJoystick.rightStickButton        .whenReleased(new stopAgitator(Robot.Agitator));

        // Move the climber upwards
        operatorJoystick.leftStickButton          .whenPressed(new runSerializer(Robot.Serializer, -Constants.SERIALIZERFORWARDSPEED));
        operatorJoystick.leftStickButton          .whenReleased(new stopClimber(Robot.Climber));
        

        // Backs the seri
        operatorJoystick.start          .whenPressed(new backUpSerializer(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE).withTimeout(0.5));
        operatorJoystick.start          .whenReleased(new shooterSystemOn());

        operatorJoystick.back. whenPressed(new shooterSystemOff().andThen(new stopShooter(Robot.Shooter)));

        // Buttons to queue the robot's angle offset 
        operatorJoystick.yellowY.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "farShot"));
        operatorJoystick.redB.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "nearShot"));
        operatorJoystick.blueX.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "targetLimelightOn"));
        operatorJoystick.greenA.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "resetZero"));
        
        operatorJoystick.povUp.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "0"));
        operatorJoystick.povRight.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "90"));
        operatorJoystick.povDown.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "180"));
        operatorJoystick.povLeft.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "270"));

        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }

}
