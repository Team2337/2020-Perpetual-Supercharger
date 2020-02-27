package frc.robot.commands.LED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.LEDs;

/**
 * Turns the LED strip on or off depending on if the shooter is running.
 * This will also flash the LEDs on and off, if the intake is running 
 * while the shooter is off
 * @author Bryce G.
 */
public class LEDRuntime extends CommandBase {
	private final LEDs led;
	private int iterations = 0;
	private int lastState = 0;

	public LEDRuntime(LEDs led) {
		this.led = led;
		addRequirements(led);
	}

	public void initialize() {
		Robot.LEDs.turnOffLEDs();
	}

	public void execute() {
		if(Robot.Shooter.shooterAtVelocity) {
			Robot.LEDs.turnOnLEDs();
		} else if(Robot.Intake.getIntakeSpeed() > 0) {
			if(iterations % 20 == 0) {
				if(lastState == 0) {
					Robot.LEDs.turnOnLEDs();
					lastState = 1;
				} else {
					Robot.LEDs.turnOffLEDs();
					lastState = 0;
				}
			}
			iterations++;
		} else {
			Robot.LEDs.turnOffLEDs();
		}
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
