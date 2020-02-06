package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * By default, the compressor automatically turns itself on/off depending on the pressure,
 * but it can be disabled in a power-intensive situation
 */
public class AirCompressor extends Subsystem {

  public static Compressor compressor = new Compressor();

  public AirCompressor() {

  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new compressor_TurnOn());
  }

  public static void enable() {
    compressor.start();
  }

  public static void disable() {
    compressor.stop();
  }

}