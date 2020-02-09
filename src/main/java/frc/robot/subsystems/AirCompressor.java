package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * By default, the compressor automatically turns itself on/off depending on the
 * pressure, but it can be disabled in a power-intensive situation
 */
public class AirCompressor extends SubsystemBase {

  public static Compressor compressor = new Compressor();

  public AirCompressor() {

  }

  public void enable() {
    compressor.start();
  }

  public void disable() {
    compressor.stop();
  }

}