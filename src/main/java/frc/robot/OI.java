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

        //insert code here

        // --- OPERATOR JOYSTICK --- //

        operatorJoystick.triggerLeft   .whileHeld(new leftHopperExtension(Robot.Hopper));
        operatorJoystick.triggerRight  .whileHeld(new rightHopperExtension(Robot.Hopper));

        // --- DRIVER STATION CONTROLS --- //

        //insert code here

        /////////////////////////////////////
    }

}