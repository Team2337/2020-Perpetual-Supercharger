package frc.robot;

import frc.robot.commands.Intake.*;
import frc.robot.nerdyfiles.controller.NerdyXbox;
import frc.robot.Robot;

public class OI {
    NerdyXbox joystick = new NerdyXbox(0);
    public OI(){
        // === TESTING PURPOSES === //
        //The right bumper moves the intake motors one way
        joystick.bumperRight .whenPressed(new adjustIntakeSpeed(Robot.Intake, 0.1));
        //The left bumper moves the intake motors the other way
        joystick.bumperLeft .whenPressed(new adjustIntakeSpeed(Robot.Intake, -0.1));

        // === DRIVING PURPOSES === //
        //Stops intake motors
        joystick.redB .whenPressed(new stopIntakeMotors(Robot.Intake));
        //Sets the intake motors to intake balls
        joystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        joystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4));
    }
}