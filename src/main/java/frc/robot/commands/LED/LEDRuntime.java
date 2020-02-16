package frc.robot.commands.LED;

import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.LED;

/**
 * Sets the color according to various factors, such as if the robot was
 * intaking, climbing, etc.
 * 
 * @author Zayd A. Jack E.
 */
public class LEDRuntime extends CommandBase {

	public LEDRuntime() {
		SendableRegistry.addChild(this, Robot.LED);
	}

	public  void initialize() {
		LED.setColor(LED.blue);
	}

	public void execute() {
			/**if(Robot.HatchBeak.status()) {
				LED.setColor(LED.white);
			} else {
				if(Robot.ClimberDeploy.climberPhase < 5) {
					if(autoEndAuto.endedAutoLED) {
						LED.setColor(LED.red);
					} 
					if(!Robot.ClimberDeploy.climberLineSensor.get() || Robot.ClimberDeploy.climberPhase == 3) {
						LED.setColor(LED.green);
					} else {
						if(Robot.ClimberDeploy.getServo() == 0.8) {
							LED.setColor(LED.darkBlue);
						} else {
							LED.setColor(LED.rainbow);
						}
					}
				} else {
					if(Robot..getServo() == 0.8) {
						LED.setColor(LED.blue);
					} else {
						LED.setColor(LED.off);
					}*/
				}

	public boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		this.end();
	}
}