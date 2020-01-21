package frc.robot;

import frc.robot.commands.swerve.*;
import frc.robot.nerdyfiles.controller.*;

/**
 * 
 */
public class OI {

    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
	public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
    public NerdyOperatorStation	operatorControls = new NerdyOperatorStation(2);
    
    /**
     * 
     */
    public OI(){
        
        /* --- DRIVER JOYSTICK --- */

        driverJoystick.bumperLeft.whenPressed(new SetFieldOriented(false));
        driverJoystick.bumperLeft.whenReleased(new SetFieldOriented(true));

        /* --- OPERATOR JOYSTICK --- */

        //insert code here

        /* --- DRIVER STATION CONTROLS --- */

        //insert code here

        /////////////////////////////////////
    }

}