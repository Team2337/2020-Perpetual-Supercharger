package frc.robot.commands.LED;

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
	private final LED led;

	public LEDRuntime(LED led) {
		this.led = led;
		addRequirements(led);
	}

	public void initialize() {
		Robot.LED.setColor(Robot.LED.off);
	}

	public void execute() {
	}

	public boolean isFinished() {
		return false;
	}

	public void end() {
	}

	protected void interrupted() {
		this.end();
	}
}
