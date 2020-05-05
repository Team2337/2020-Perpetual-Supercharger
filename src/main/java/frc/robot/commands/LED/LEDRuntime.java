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
	private final LEDs LEDs;
	private int iterations = 0;
	private int lastState = 0;

	public LEDRuntime(LEDs led) {
		this.LEDs = led;
		addRequirements(led);
	}

	public void initialize() {
		LEDs.turnOffLEDs();
	}

	public void execute() {
		if(Robot.Shooter.shooterAtVelocity) {
			LEDs.turnOnLEDs();
		} else if(Robot.Intake.getIntakeSpeed() > 0) {
			if(iterations % 10 == 0) {
				if(lastState == 0) {
					LEDs.turnOnLEDs();
					lastState = 1;
				} else {
					LEDs.turnOffLEDs();
					lastState = 0;
				}
			}
			iterations++;
		} else {
			LEDs.turnOffLEDs();
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
