package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Controls the release the climber systems using a pneumatics
 */
public class Servo66 extends SubsystemBase {

    private Servo order66Release;

    /**
     * Controls the release the climber systems using a pneumatics
     */
    public Servo66() {
        order66Release = new Servo(Constants.SERVOPORT);

        // LiveWindow.addActuator("servo", 1, order66Release);
    }

    public void servoSet(double pos) {
        order66Release.set(pos);
    }

    public void servoSetPosition(double pos) {
        order66Release.setPosition(pos);
    }

    /**
     * Sets the angle of the servo
     * @param pos - 0-360 degrees
     */
    public void servoSetAngle(double pos) {
        order66Release.setAngle(pos);
    }

    public double getServo() {
        return order66Release.get();
    }

    /**
     * Releases the climber systems
     */
    public void deployHyperLoop() {
        servoSet(0.8);
    }

    /**
     * unReleases the climber systems
     */
    public void retractHyperLoop() {
        servoSet(0.4);
    }

}