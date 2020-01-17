/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Simple system for the intake
 * @author
 * <a href="mailto:mfrancis48439@gmail.com?subject=Intake%20Programming">Michael Francis</a>
 */
public class Intake extends SubsystemBase {

  //Motors
  TalonFX intakeMotor1;
  //TalonFX intakeMotor2;

  /**
   * Creates a new Intake subsystem and sets up the motors to their corresponding ports.
   */
  public Intake() {
    /**
     * Currently, this sets the motors up. We have:
     *   • intakeMotor1 (set to a placeholder motor value)
     *   • intakeMotor2 (an extra motor in case there are 2 motors)
     * All instances of intakeMotor2 have been commented out for now and the
     * name can be changed later. The motor can be uncommented once it is
     * confirmed that there are going to be 2 motors used.
     */
    intakeMotor1 = new TalonFX(0);//Placeholder value right now, please replace once we figure out what we are doing
    // intakeMotor2 = new TalonFX(1);//Also a placeholder value
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * A method that sets the speed of the intake motor(s)
   * @author Michael Francis
   * @param speed
   * Sets the speed of the motors as a value -1 through 1
   */
  public void setIntakeSpeed(double speed){
    //Sets the speed of the intake motor
    intakeMotor1.set(ControlMode.PercentOutput, speed);
    //intakeMotor2.set(ControlMode.PercentOutput, speed);
  }

  /**
   * A method that stops the intake motors. (both)
   * @author Michael Francis
   */
  public void stopIntake(){
    intakeMotor1.set(ControlMode.PercentOutput, 0);
    // intakeMotor2.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Method that returns the temperature of the intake motor requested.
   * @param mtr
   * An integer value that determines what temperature the method returns.
   * <ul><li>1 = intakeMotor1</li>
   * <li>2 = intakeMotor2</li>
   * <li>If any other value is entered it will return a 0</li></ul>
   * @return
   * Returns a double value equal to the temperature of the motor determined 
   * in the parameter <em>mtr</em>.
   * @author Michael Francis
   */
  public double getIntakeTemperature(int mtr){
    /**
     * The if statement is determined by the integer received.
     * The number received determines what intake motor it checks.
     * If it is any other number, it will return 0.
     */
    if(mtr == 1){
      //If the given variable is 1, return intakeMotor1's temperature.
      return intakeMotor1.getTemperature();
    }else if(mtr == 2){
      //If the given variable is 2, return intakeMotor2's temperature.
      /**
       * NOTE: IF there is a second motor, uncomment the first return and
       * remove the second.
       */
      //return intakeMotor2.getTemperature();
      return 0;
    }else{
      //If the given variable is anything but 1 or 2, return 0.
      return 0;
    }
  }
}
