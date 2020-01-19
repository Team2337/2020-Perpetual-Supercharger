package frc.robot;

import frc.robot.nerdyfiles.controller.*;
import frc.robot.commands.Shooter.shootBall;

public class OI {
    public NerdyXbox joystick = new NerdyXbox(0);
    public OI(){
        joystick.triggerRight .whileHeld(new shootBall(Robot.Shooter, 0.95));//19600
        
    }

}