package frc.robot;

/**
 * Utilities class to house basic methods that can be used across subsystems 
 * @author Bryce G.
 */
public class Utilities {

    /**
     * Utilities class to house basic methods that can be used across subsystems 
     */
    public Utilities() {

    }

    /**
     * Checks to see if the absolute value of the input is less than the deadband
     * @param input - Value in which the deadband will be applied (0 < input < 1)
     * @param deadband - Deadband to set on the input (double)
     * @return - input double value adjusted for the deadband
     */
    public double deadband(double input, double deadband) {
        if (Math.abs(input) < deadband) return 0;
		return input;
    }

    /**
     * Squares the input value
     * @param value - double value wanting to be squared
     * @return - squared input double value
     */
    public double squareValues(double value) {
        // double direction = value < 0 ? -1 : 1;
        return Math.copySign(Math.pow(value, 2), value);
    }

    /**
     * Calculates derivative based on the current and previous sensor inputs
     * @param error - double value reading the current sensor error (current - target)
     * @param lastError - double value reading the previous sensor error (current - target)
     * @param dt - Change in time from when the previous sensor error was gotten to the time the current was gotten
     * @return - returns derivative double value to add to the speed of the motor
     */
    public double calculateDerivative(double error, double lastError, double dt) {
        if (Double.isFinite(lastError)) {
            return (error - lastError) / dt;
        } else {
            return 0;
        }
    }

    /**
     * Gets the yaw of the gyro device being used
     * @param gyroType - String value for the gyro being used (pigeon: "pigeon" | navx: "navx")
     * @return - yaw from the gyro
     */
    public double getYaw(String gyroType) {
      switch(gyroType) {
          case "pigeon":
            return -Robot.Pigeon.getYaw();
          case "navx":
            // return Robot.navx.getYaw();
          default:
          return 0;
      }
    }

    /**
     * Gets the yaw value from the Pigeon between -360 & +360
     * @return - gyro double value between -360 & +360
     */
    public double getPigeonYawMod() {
      return getYaw("pigeon") % 360;
    }

  /**
   * Determines whether or not the given value is within a certain amount of a target
   * 
   * @param target The desired value
   * @param current The current value
   * @param tolerance A range that the given value can be within the target value before returning true
   * @return Whether or not the current value is within a tolerance of the target
   */
  public boolean withinTolerance(double target, double current, double tolerance) {
    return Math.abs(target - current) <= tolerance;
  }
}