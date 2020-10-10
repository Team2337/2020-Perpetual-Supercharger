package frc.robot.commands.Lightsaber;

import frc.robot.subsystems.Lightsaber;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Turns the lightsaber off
 * @author Madison J.
 * @category Lightsaber
 */
public class turnOffLightsaber extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Lightsaber lightsaber;

  /**
   * Turns the lightsaber light off
   * @param lightsaber - The subsystem that is required
   */
  public turnOffLightsaber(Lightsaber lightsaber) {
    this.lightsaber = lightsaber;
    
    addRequirements(lightsaber);
  }

  @Override
  public void initialize() {
    Lightsaber.LightsaberLight.set(false);
  }

  @Override
  public void end(boolean interrupted) {
  }

}
