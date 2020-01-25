package frc.robot;

import frc.robot.commands.Serializer.cellHolderClose;
import frc.robot.commands.Serializer.cellHolderOpen;
import frc.robot.commands.Serializer.serializerUp;
import frc.robot.nerdyfiles.controller.*;

public class OI {

    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
	public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
	public NerdyOperatorStation	operatorControls = new NerdyOperatorStation(2);
    public OI(){
        
        // --- DRIVER JOYSTICK --- //



        // --- OPERATOR JOYSTICK --- //
        //insert code here
        operatorJoystick.greenA  .whileHeld(new serializerUp());
        operatorJoystick.redB  .whileHeld (new cellHolderClose());
        operatorJoystick.redB  .whenReleased(new cellHolderOpen());
        // --- DRIVER STATION CONTROLS --- //

        //insert code here

        /////////////////////////////////////
    }

}