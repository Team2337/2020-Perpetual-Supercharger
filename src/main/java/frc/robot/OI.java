package frc.robot;

import frc.robot.nerdyfiles.controller.*;
import frc.robot.commands.Shooter.shootBall;

public class OI {
    public NerdyXbox joystick = new NerdyXbox(0);
    public OI(){
        //See src\main\java\frc\robot\OI_test_results.txt for values
        joystick.triggerRight .whileHeld(new shootBall(Robot.Shooter, 15295));//16120
    }
}