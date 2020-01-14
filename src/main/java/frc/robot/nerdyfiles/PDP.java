package frc.robot.nerdyfiles;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class PDP extends SubsystemBase {

  public PDP() {
    //stuff
  }

  public void initDefaultCommand() {
    //also stuff
  }

  public void periodic() {
    SmartDashboard.putNumber("PDP_Total_Current", Robot.PDP.getTotalCurrent());
    SmartDashboard.putNumber("PDP_Current_0", Robot.PDP.getCurrent(0));
    SmartDashboard.putNumber("PDP_Current_1", Robot.PDP.getCurrent(1));
    SmartDashboard.putNumber("PDP_Current_2", Robot.PDP.getCurrent(2));
    SmartDashboard.putNumber("PDP_Current_3", Robot.PDP.getCurrent(3));
    SmartDashboard.putNumber("PDP_Current_4", Robot.PDP.getCurrent(4));
    SmartDashboard.putNumber("PDP_Current_5", Robot.PDP.getCurrent(5));
    SmartDashboard.putNumber("PDP_Current_6", Robot.PDP.getCurrent(6));
    SmartDashboard.putNumber("PDP_Current_7", Robot.PDP.getCurrent(7));
    SmartDashboard.putNumber("PDP_Current_8", Robot.PDP.getCurrent(8));
    SmartDashboard.putNumber("PDP_Current_9", Robot.PDP.getCurrent(9));
    SmartDashboard.putNumber("PDP_Current_10", Robot.PDP.getCurrent(10));
    SmartDashboard.putNumber("PDP_Current_11", Robot.PDP.getCurrent(11));
    SmartDashboard.putNumber("PDP_Current_12", Robot.PDP.getCurrent(12));
    SmartDashboard.putNumber("PDP_Current_13", Robot.PDP.getCurrent(13));
    SmartDashboard.putNumber("PDP_Current_14", Robot.PDP.getCurrent(14));
    SmartDashboard.putNumber("PDP_Current_15", Robot.PDP.getCurrent(15));

    SmartDashboard.putNumber("PDP_Temperature", Robot.PDP.getTemperature());
    SmartDashboard.putNumber("PDP_Voltage", Robot.PDP.getVoltage());
   // SmartDashboard.putNumber("PDP_", Robot.PDP.);

  }

}