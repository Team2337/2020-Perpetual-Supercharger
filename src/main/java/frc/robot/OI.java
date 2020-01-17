package frc.robot;

import frc.robot.commands.Intake.intakeCommand;
import frc.robot.nerdyfiles.controller.NerdyXbox;
import frc.robot.Robot;

public class OI {
    NerdyXbox joystick = new NerdyXbox(0);
    public OI(){
        //The right bumper moves the motors one way
        //The left bumper moves the motors the opposite way
        joystick.bumperRight .whileHeld(new intakeCommand(Robot.Intake, 0.5));
        joystick.bumperLeft .whileHeld(new intakeCommand(Robot.Intake, -0.5));
    }
}