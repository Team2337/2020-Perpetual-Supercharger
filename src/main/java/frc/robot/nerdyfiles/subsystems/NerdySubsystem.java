package frc.robot.nerdyfiles.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public abstract class NerdySubsystem extends SubsystemBase {

  private boolean smartCurrentEnabled = false; 

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
  
  public abstract void stopAllMotors();

  public abstract void debugPrints(boolean debug);

  public void enableSmartCurrentShutdown() {
    this.smartCurrentEnabled = true;
  }

  public void disableSmartCurrentShutdown() {
    this.smartCurrentEnabled = false;
  }

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
