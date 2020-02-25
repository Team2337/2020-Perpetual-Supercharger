package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * This is the subsystem for the color wheel sensor that checks the color shown on the color wheel.
 * @author Maddy Duhart, Nicholas Stokes
 */
public class ColorWheel extends SubsystemBase {
// Defines i2c port on robot//
  I2C.Port i2cPort = I2C.Port.kOnboard; 

// defines all the colors needed in regards to the sensors inputs through RevRobotics//
  ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  public static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  
  ColorMatch m_colorMatcher = new ColorMatch();
  String colorString;
  public String positionColor;
  
  public ColorWheel() {
// This makes it so that the colors match to the RGB values
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 
  }
// periodic runs every 20 milliseconds to detect the color found and send that reading to the SmartDashboard//

  public void periodic() {
    // Finds what the position control color will be and converts into a usable string for other code.
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
if(gameData.length() > 0)
{
  switch (gameData.charAt(0))
  {
    case 'B' :
    positionColor = "Blue";
      break;
    case 'G' :
      positionColor = "Green";
      break;
    case 'R' :
    positionColor = "Red";
      break;
    case 'Y' :
    positionColor = "Yellow";
      break;
    default :
      break;
  }
}
    String color = getColor();
    
    SmartDashboard.putString("Detected Color", color);
  }
  // This checks the color and sees if its a color on the color wheel.
  public String getColor() {
    Color detectedColor = m_colorSensor.getColor();
    
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    /*

    */
    if (match.color == kBlueTarget) {
      colorString = "Red";
    } else if (match.color == kRedTarget) {
      colorString = "Blue";
    } else if (match.color == kGreenTarget) {
      colorString = "Yellow";
    } else if (match.color == kYellowTarget) {
      colorString = "Green";
    } else {
      colorString = "Unknown";
    }

    return colorString;
    
  }
}
