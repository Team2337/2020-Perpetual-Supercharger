package frc.robot;

import frc.robot.commands.Intake.intakeCommand;
import frc.robot.nerdyfiles.controller.NerdyXbox;
import frc.robot.Robot;

public class OI {
    NerdyXbox joystick = new NerdyXbox(0);
    public OI(){
        joystick.bumperRight .whileHeld(new intakeCommand(Robot.Intake, 0.5));
        joystick.bumperLeft .whileHeld(new intakeCommand(Robot.Intake, -0.5));
    }
}