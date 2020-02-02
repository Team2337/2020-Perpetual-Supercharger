package frc.robot;

import frc.robot.commands.KickerWheel.*;
import frc.robot.commands.Intake.*;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.*;



public class OI {
    public NerdyUltimateXboxDriver driverJoystick = new NerdyUltimateXboxDriver(0);
    public NerdyUltimateXboxOperator operatorJoystick = new NerdyUltimateXboxOperator(1);
    public NerdyOperatorStation operatorControls = new NerdyOperatorStation(2);

    public OI() {

        /* --- DRIVER JOYSTICK --- */

        // Increase Neo speed by 10%
        driverJoystick.yellowY .whenPressed(new increaseKickerSpeed(Robot.KickerWheel, 0.1));
        driverJoystick.greenA .whenPressed(new decreaseKickerSpeed(Robot.KickerWheel, 0.1));
        driverJoystick. redB .whenPressed(new stopKickerSpeed(Robot.KickerWheel, 0.1));

        /* --- OPERATOR JOYSTICK --- */
        
        //Sets the intake motors to intake balls
        operatorJoystick.triggerRight .whileHeld(new setIntakeSpeed(Robot.Intake, 0.4, 0.4));
        //Sets the intake motors to outtake balls (reverse mode)
        operatorJoystick.triggerLeft .whileHeld(new setIntakeSpeed(Robot.Intake, -0.4, -0.4));

        /* --- DRIVER STATION CONTROLS --- */

        //insert code here
        
    }
	public Joystick getDriverJoystick() {
		return driverJoystick;
	}
}