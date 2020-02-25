package frc.robot.commands.ColorWheel;

import frc.robot.Constants;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;


/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class positionControl extends CommandBase {
  private KickerWheel kickerWheel;
  private ColorWheel colorWheel;

  /**
   * Sets the kicker's speed.
   * 
   * @param kickerWheel The subsystem used by this command. (Kicker)
   */
  public positionControl(KickerWheel kickerWheel, ColorWheel colorWheel) {
    this.kickerWheel = kickerWheel;
    this.colorWheel = colorWheel;
    addRequirements(kickerWheel, colorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    //Set the speed of the color wheel
    kickerWheel.setKickerSpeed(Constants.COLORWHEELSPEED, Constants.KickerWheel.SHORTVELOCITYP);
  }

  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stop the color wheel
    kickerWheel.stopKicker();
  }

  @Override
  public boolean isFinished() {
    //Stop when the field color is the color that we want
    return colorWheel.getFieldDataColor() == colorWheel.getFieldSensorColor();
  }
}

