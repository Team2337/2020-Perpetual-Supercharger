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

        driverJoystick.greenA  .whileHeld(new serializerUp());
        driverJoystick.redB  .whileHeld (new cellHolderClose());
        driverJoystick.redB  .whenReleased(new cellHolderOpen());

        // --- OPERATOR JOYSTICK --- //
        //insert code here

        // --- DRIVER STATION CONTROLS --- //

        //insert code here

        /////////////////////////////////////
    }

}