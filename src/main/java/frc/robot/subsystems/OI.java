package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.commands.leftHopperExtension;
import frc.robot.commands.rightHopperExtension;
import frc.robot.nerdyfiles.controller.NerdyXbox;

public class OI{

    public NerdyXbox joystick = new NerdyXbox(0);

    public OI() {

        joystick.triggerLeft  .whenPressed(new leftHopperExtension(Robot.Hopper));
        joystick.triggerRight .whenPressed(new rightHopperExtension(Robot.Hopper));
    }
}