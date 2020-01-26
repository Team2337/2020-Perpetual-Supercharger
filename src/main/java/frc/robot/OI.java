package frc.robot;

import frc.robot.commands.Shooter.CGSequentialShooter;
import frc.robot.commands.Shooter.sensorTest;
import frc.robot.nerdyfiles.controller.*;
import frc.robot.commands.Shooter.*;

public class OI {
    public NerdyXbox joystick = new NerdyXbox(0);
    public OI(){
        joystick.greenA. whileHeld(new CGSequentialShooter(Robot.Shooter, 15265));
        joystick.redB. whileHeld(new sensorTest(Robot.Shooter));
        joystick.bumperRight .whileHeld(new shootContinuously(Robot.Shooter, 15295));
        joystick.bumperLeft .whenPressed(new shootSingleBall(Robot.Shooter, 15295));
    }

}