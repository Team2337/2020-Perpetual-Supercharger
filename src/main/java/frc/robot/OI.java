package frc.robot;

import frc.robot.commands.colorWheel;
import frc.robot.nerdyfiles.controller.*;

public class OI {

    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
    public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
    public NerdyOperatorStation operatorControls = new NerdyOperatorStation(2);

    public OI() {

        // --- DRIVER JOYSTICK --- //

        // insert code here
        
        driverJoystick.blueX.whenPressed(new colorWheel());
        driverJoystick.greenA.whenPressed(new colorWheel());
        driverJoystick.redB.whenPressed(new colorWheel());
        driverJoystick.yellowY.whenPressed(new colorWheel());

        // --- OPERATOR JOYSTICK --- //

        // insert code here

        // --- DRIVER STATION CONTROLS --- //

        // insert code here

        /////////////////////////////////////
    }

}