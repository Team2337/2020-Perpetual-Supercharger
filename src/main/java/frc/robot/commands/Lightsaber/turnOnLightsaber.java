package frc.robot.commands.Lightsaber;

import frc.robot.subsystems.Lightsaber;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Turns the lightsaber on
 * @author Madison J.
 * @category Lightsaber
 */
public class turnOnLightsaber extends InstantCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Lightsaber lightsaber;

  /**
   * Turns the lightsaber on
   * @param lightsaber - The subsystem that is required
   */
  public turnOnLightsaber(Lightsaber lightsaber) {
    this.lightsaber = lightsaber;
    
    addRequirements(lightsaber);
  }

  @Override
  public void initialize() {
    Lightsaber.LightsaberLight.set(true);
  }

  @Override
  public void end(boolean interrupted) {
  }

}
