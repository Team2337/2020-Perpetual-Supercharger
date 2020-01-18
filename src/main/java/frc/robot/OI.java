package frc.robot;

import frc.robot.commands.Intake.*;
import frc.robot.nerdyfiles.controller.NerdyXbox;
import frc.robot.Robot;

public class OI {
    NerdyXbox joystick = new NerdyXbox(0);
    public OI(){
        //The right bumper moves the intake motors one way
        joystick.bumperRight .whenPressed(new adjustIntakeSpeed(Robot.Intake, 0.1));
        //The left bumper moves the intake motors the other way
        joystick.bumperLeft .whenPressed(new adjustIntakeSpeed(Robot.Intake, -0.1));
        //Stops intake motors
        joystick.redB .whenPressed(new stopIntakeMotors(Robot.Intake));
    }
}