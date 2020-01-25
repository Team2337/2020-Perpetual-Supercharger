package frc.robot;

import frc.robot.commands.leftHopperExtension;
import frc.robot.commands.rightHopperExtension;
import frc.robot.nerdyfiles.controller.*;

public class OI {

    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
	public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
	public NerdyOperatorStation	operatorControls = new NerdyOperatorStation(2);
    public OI(){
        
        // --- DRIVER JOYSTICK --- //

        driverJoystick.triggerLeft   .whileHeld(new leftHopperExtension(Robot.Hopper));
        driverJoystick.triggerRight  .whileHeld(new rightHopperExtension(Robot.Hopper));

        // --- OPERATOR JOYSTICK --- //

        //insert code here

        // --- DRIVER STATION CONTROLS --- //

        //insert code here

        /////////////////////////////////////
    }

}