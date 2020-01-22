/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CellHolder extends SubsystemBase {

  //solenoids
  Solenoid cellHolderSolenoid;
  /**
   * Creates a new ExampleSubsystem.
   */
  public CellHolder() {
   

    cellHolderSolenoid = new Solenoid(0);


  }
public void cellHolderClose(){
  cellHolderSolenoid.set(true);
}
public void cellHolderOpen(){
  cellHolderSolenoid.set(false);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
