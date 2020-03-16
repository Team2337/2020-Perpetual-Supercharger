package frc.robot.nerdyfiles.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This subsystem is used as a base subsystem for all EngiNERDs subsystems.
 * Features available:
 * <ul>
 * <li> Smart Current Limit
 * <li> Subsystem Shutdown based on Current 
 * <li> Debug Prints
 * <li> Returning Average and total current limits
 * </ul>
 * 
 * @see SubsystemShutdown
 * @author Team 2337 - The EngiNERDs
 * @version 0.1.0
 */
public abstract class NerdySubsystem extends SubsystemBase {

  /* --- Boolean Values --- */
  /** Debug mode */
  private boolean debug = false;

  /** Smart current mode */
  private boolean smartCurrentEnabled = false; 

  /* --- Double Values --- */
  /** Max Current Limit for the subsystem (Default: 80 amps) */
  private double currentLimit = 80;

  /**
   * Gets the total current of all the motors in the subsystem
   * @return - double value in amps 
   */
  public abstract double totalMotorCurrent();

  /**
   * Gets the average current of all the motors in the subsystem
   * @return - double value in amps 
   */
  public abstract double averageMotorCurrent();
  
  /**
   * Stops all the motors in this subsystem
   */
  public abstract void stopAllMotors();

  /**
   * Enables the subsystem's debug prints
   * @param debug - boolean value (true: debug prints enabled | false: debug prints disabled)
   */
  public void enableDebugPrints(boolean debug) {
    this.debug = debug;
  }

  /**
   * Gets the debug state
   * @return - boolean value (true: debug is enabled | false: debug is disabled)
   */
  public boolean getDebug() {
    return this.debug;
  }

  /**
   * Dashboard prints used when debugging a subsystem. 
   * Can be enabled by putting debug as true.
   * @param debug - boolean value (true: prints to the dashboard | false: does nothing)
   */
  public abstract void debugPrints();

  /**
   * Enables the smart current limit mode for the subsystem 
   */
  public void enableSmartCurrentShutdown() {
    this.smartCurrentEnabled = true;
  }

  /**
   * Disables the smart current limit mode for the subsystem
   */
  public void disableSmartCurrentShutdown() {
    this.smartCurrentEnabled = false;
  }

  /**
   * Returns the smart current shutdown mode
   * @return - boolean value (true: smart current enabled | false: smart current disabled)
   */
  public boolean smartCurrentShutdownEnabled() {
    return this.smartCurrentEnabled;
  }

  /**
   * Sets the max current limit for all motors in the subsystem. 
   * If this is not set, the default is 80 amps, which should allow
   * for two motors to run 
   * @param currentLimit - double value in amps
   */
  public void setMaxSubsystemCurrent(double currentLimit) {
    this.currentLimit = currentLimit;
  }

  /**
   * Gets the max current limit that the subsystem can 
   * run at once. This will be used to handle the 
   * smart subsystem shutdown. 
   * @see SubsystemShutdown
   * @return - double value in amps
   */
  public double maxSubsystemCurrent() {
    return this.currentLimit;
  }

  @Override
  public void periodic() {
 
  }
}
