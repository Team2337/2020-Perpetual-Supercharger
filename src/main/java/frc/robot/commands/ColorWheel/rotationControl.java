package frc.robot.commands.ColorWheel;

import frc.robot.Constants;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;


/**
 * A command that sets the kicker speed using the Kicker subsystem.
 */
public class rotationControl extends CommandBase {
  private KickerWheel kickerWheel;
  private ColorWheel colorWheel;

  private int rotationCount = 0;

  private String startingColor;

  private boolean alreadyCounted = true;

  /**
   * Sets the kicker's speed.
   * 
   * @param kickerWheel The subsystem used by this command. (Kicker)
   */
  public rotationControl(KickerWheel kickerWheel, ColorWheel colorWheel) {
    this.kickerWheel = kickerWheel;
    this.colorWheel = colorWheel;
    addRequirements(kickerWheel, colorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){ 
    //Set the speed of the color wheel
    kickerWheel.setKickerSpeed(Constants.COLORWHEELSPEED, Constants.KickerWheel.SHORTVELOCITYP);

    startingColor = colorWheel.getFieldSensorColor();
  }

  @Override
  public void execute() {
    if(colorWheel.getFieldSensorColor() == startingColor){
      //If the color sensor is still reading the same color on the next loopthrough, don't count it as a rotation
      if(!alreadyCounted){
        rotationCount++;
        alreadyCounted = true;
      }
    } else {
      //If the sensor is no longer seeing it's starting color, it needs to count it as a half rotation on the next turn
      alreadyCounted = false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  kickerWheel.stopKicker();
  }
    @Override
    public boolean isFinished() {
      return rotationCount > 3 * 2;
    }
    //Stop when we have rotated 3.5 times
    
  }


