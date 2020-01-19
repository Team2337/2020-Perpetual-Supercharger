package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {
  
  //Solenoids being defined
  Solenoid leftFlipper;
  Solenoid rightFlipper;
  
  //Sets port the Solenoids work from
  public Hopper(){
    leftFlipper  = new Solenoid(0);
    rightFlipper = new Solenoid(1);
  }

  //Defines the act of extending and retracting the left flipper
  public void extendLeftFlipper(){
    leftFlipper.set(true);
  }
  public void retractLeftFlipper(){
    leftFlipper.set(false);
  }

  //Defines the act of extending and retracting the right flipper
  public void extendRightFlipper(){
    rightFlipper.set(true);
  }
  public void retractRightFlipper(){
    rightFlipper.set(false);
  }
}