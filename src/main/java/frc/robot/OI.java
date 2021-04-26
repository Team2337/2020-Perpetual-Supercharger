package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.commands.Agitator.*;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Serializer.*;
import frc.robot.commands.Servo.deployHyperLoop;
import frc.robot.commands.Servo.retractHyperLoop;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.nerdyfiles.controller.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.ShooterSystem.*;
import frc.robot.commands.Vision.limeLightLEDOff;
import frc.robot.commands.Vision.limeLightLEDOn;
import frc.robot.commands.Vision.setBallTracking;

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

        driverJoystick.bumperRight.whenPressed(new runSerializer(Robot.Serializer, Constants.SERIALIZERDRIVERFORWARDSPEED));
        driverJoystick.bumperRight.whenReleased(new stopSerializer(Robot.Serializer));

        // Run the shooter
        // If the shooter is not running then feed system forward cannot run. If the shooter is running then feed system forward can run
        /* driverJoystick.triggerRight.whenPressed(new ConditionalCommand(new feedSystemForward(), 
        new CommandBase(){}, Robot.Shooter.shooterAtVelocityBooleanSupplier));
        driverJoystick.triggerRight.whenReleased(new ConditionalCommand(new feedSystemStop(),
        new CommandBase(){} , Robot.Shooter.shooterAtVelocityBooleanSupplier)); */

        // driverJoystick.triggerRight.whenPressed(new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED));
        // driverJoystick.triggerRight.whenReleased(new stopIntake(Robot.Intake));

        // Slow rotates to the right
        driverJoystick.redB         .whenPressed(new setSlowRotateMode(Robot.OperatorAngleAdjustment, true, -Constants.Swerve.SLOWROTATESPEED));
        driverJoystick.redB         .whenReleased(new setSlowRotateMode(Robot.OperatorAngleAdjustment, false, 0));
        
        // Slow rotates to the left
        driverJoystick.blueX         .whenPressed(new setSlowRotateMode(Robot.OperatorAngleAdjustment, true, Constants.Swerve.SLOWROTATESPEED));
        driverJoystick.blueX         .whenReleased(new setSlowRotateMode(Robot.OperatorAngleAdjustment, false, 0));

        driverJoystick.povUp.whenPressed(new ResetGyro(Robot.Pigeon));

        driverJoystick.back.whenPressed(new ChangeVisionAngleOffset(Robot.OperatorAngleAdjustment, true));
        driverJoystick.back.whenReleased(new ChangeVisionAngleOffset(Robot.OperatorAngleAdjustment, false));

        driverJoystick.start.whenPressed(new setBallTracking(Robot.OperatorAngleAdjustment, true));
        driverJoystick.start.whenReleased(new setBallTracking(Robot.OperatorAngleAdjustment, false));

        /* driverJoystick.back.whenPressed(new ConditionalCommand(new ChangeVisionAngleOffset(Robot.OperatorAngleAdjustment, true),
                        new setBallTracking(Robot.OperatorAngleAdjustment, true),
                        Robot.Shooter.shooterAtVelocityBooleanSupplier));
            
        driverJoystick.back.whenReleased(new ConditionalCommand(new ChangeVisionAngleOffset(Robot.OperatorAngleAdjustment, false),
                        new setBallTracking(Robot.OperatorAngleAdjustment, false),
                        Robot.Shooter.shooterAtVelocityBooleanSupplier)); */

        //Run the intake while shooting balls
        driverJoystick.triggerRight.whenPressed(new ConditionalCommand(new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED), 
        new CommandBase(){}, Robot.Shooter.shooterAtVelocityBooleanSupplier));
        driverJoystick.triggerRight.whenReleased(new ConditionalCommand(new stopIntake(Robot.Intake),
        new CommandBase(){} , Robot.Shooter.shooterAtVelocityBooleanSupplier));

        //DRIVER JOYSTICK RIGHT TRIGGER IS USED IN THE CO-OP COMMANDS IN SERIALIZER AND KICKERWHEEL

        /* --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight   .whenPressed(new runIntake(Robot.Intake, Constants.INTAKEFORWARDSPEED));
        operatorJoystick.triggerRight   .whenReleased(new stopIntake(Robot.Intake));

        operatorJoystick.triggerRight.whenPressed(new runAgitator(Robot.Agitator, Constants.AGITATORSPEED));
        operatorJoystick.triggerRight.whenReleased(new stopAgitator(Robot.Agitator));

        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.bumperRight    .whenPressed(new runIntake(Robot.Intake, -Constants.INTAKEFORWARDSPEED));
        operatorJoystick.bumperRight    .whenReleased(new stopIntake(Robot.Intake));
/* 
         operatorJoystick.triggerLeft.whenPressed(new ConditionalCommand(new CommandBase() {
        }, new feedSystemForward(), Robot.Shooter.shooterAtVelocityBooleanSupplier));
        operatorJoystick.triggerLeft.whenReleased(new ConditionalCommand(new CommandBase() {
        }, new feedSystemStop(), Robot.Shooter.shooterAtVelocityBooleanSupplier));  */
        //TRIGGER LEFT IN SERIALIZER COMMAND

        operatorJoystick.bumperLeft.whenPressed(new feedSystemReverse());
        operatorJoystick.bumperLeft.whenReleased(new feedSystemStop());

         // Run the agitator leftwards
         /* 
        operatorJoystick.rightStickButton        .whileHeld(new runAgitatorManual(Robot.Agitator, Constants.AGITATORSPEED));
        operatorJoystick.rightStickButton        .whenReleased(new stopAgitator(Robot.Agitator)); */

        // Move the climber upwards
        operatorJoystick.leftStickButton          .whenPressed(new runSerializer(Robot.Serializer, -Constants.SERIALIZEROPERATORFORWARDSPEED));
        operatorJoystick.leftStickButton.whenReleased(new stopSerializer(Robot.Serializer));
        

        // Backs the serializer up
        operatorJoystick.start          .whenPressed(new adjustSerializer(Robot.Serializer, Constants.SERIALIZERREGRESSIONDISTANCE).withTimeout(0.5));
        operatorJoystick.start          .whenReleased(new limeLightLEDOn(Robot.Vision).andThen(new shooterSystemOn()));

        operatorJoystick.back. whenPressed(new shooterSystemOff().andThen(new stopShooter(Robot.Shooter)).andThen(new limeLightLEDOff(Robot.Vision)));

        // Buttons to queue the robot's angle offset 
        operatorJoystick.yellowY.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "farShot"));
        operatorJoystick.redB.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "nearShot"));
        operatorJoystick.blueX.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "frontTrenchShot"));
        operatorJoystick.greenA.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "frontTrenchRunShot"));
        
        operatorJoystick.povUp.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "0"));
        operatorJoystick.povRight.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "90"));
        operatorJoystick.povDown.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "180"));
        operatorJoystick.povLeft.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "270"));

        /* --- DRIVER STATION CONTROLS --- */
        operatorControls.BlackSwitch.whenPressed(new activateClimber(Robot.Climber, true));
        operatorControls.BlackSwitch.whenPressed(new SetGyroAngleOffset(Robot.OperatorAngleAdjustment, "climbing"));
        operatorControls.BlackSwitch.whenReleased(new activateClimber(Robot.Climber, false));

        operatorControls.BlackButton.whenPressed(new runClimber(Robot.Climber, 177500, false));
        operatorControls.BlackButton.whenReleased(new runClimber(Robot.Climber, 177500, true));

        operatorControls.BlueButton.whenPressed(new runClimber(Robot.Climber, 50000, false));
        operatorControls.BlueButton.whenReleased(new runClimber(Robot.Climber, 50000, true));

        operatorControls.YellowButton.whenPressed(new runControlPanelMode(Robot.KickerWheel));
        operatorControls.YellowButton.whenReleased(new stopKicker(Robot.KickerWheel));

        operatorControls.BlueSwitch.whenPressed(new ChangeVisionAngleOffset(Robot.OperatorAngleAdjustment, false));

        operatorControls.YellowSwitch.whenPressed(new deployHyperLoop(Robot.Servo66));
        operatorControls.YellowSwitch.whenReleased(new retractHyperLoop(Robot.Servo66));
        
    }

}
