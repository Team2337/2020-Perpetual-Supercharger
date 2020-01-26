package frc.robot;

import frc.robot.commands.Shooter.CGSequentialShooter;
import frc.robot.commands.Shooter.sensorTest;
import frc.robot.nerdyfiles.controller.*;


public class OI {
    public NerdyXbox joystick = new NerdyXbox(0);
    public OI(){
        joystick.greenA. whileHeld(new CGSequentialShooter(Robot.Shooter, 15265));
        joystick.redB. whileHeld(new sensorTest(Robot.Shooter));
    }

}