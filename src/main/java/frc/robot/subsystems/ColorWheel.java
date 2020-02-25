/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class ColorWheel extends SubsystemBase {

  public CANSparkMax ColorWheelMotor;
  
  public ColorWheel(){ 
    ColorWheelMotor = new CANSparkMax(Robot.Constants.ColorWheelMotorID, MotorType.kBrushless);
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }
  I2C.Port i2cPort = I2C.Port.kOnboard;

// Defines i2c port on robot// 

  ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);


  public final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public final Color kYellowTarget = ColorMatch.makeColor( 0.361, 0.524, 0.113);

  // defines all the colors needed in regards to the sensors inputs through RevRobotics 
  // Rev Color threshold V3
  // blue 0.143, 0.427, 0.429
  // green 0.197, 0.561, 0.240
  // red 0.561, 0.232, 0.114
  // yellow 0.361, 0.524, 0.113
  // Rev Color threshold V2
  //
  String colorString;
  
  ColorMatch m_colorMatcher = new ColorMatch();
  
  public void periodic() {
    
    String color = getColor();
    
    SmartDashboard.putString("Detected Color", color);
  }
  // periodic runs every 20 milliseconds to detect the color found and send that reading to the SmartDashboard//
  
  public String getColor() {
    Color detectedColor = m_colorSensor.getColor();
    
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    SmartDashboard.putString("Match Color", match.color.toString());
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Green", detectedColor.green);
    
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    SmartDashboard.putString("detected", colorString);

    return colorString;
    
  }
}
