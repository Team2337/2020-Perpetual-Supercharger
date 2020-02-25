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
 * This is the subsystem for the color wheel sensor that checks the color shown
 * on the color wheel.
 * 
 * @author Maddy Duhart, Nicholas Stokes
 */
public class ColorWheel extends SubsystemBase {
  // Defines i2c port on robot//
  I2C.Port i2cPort = I2C.Port.kOnboard;

  // The game data is set late in the match, so start it as an empty string
  String gameData = "";

  // After we receive the game data, we don't need to keep checking for it
  boolean gameDataSet = false;

  //The color matching object
  ColorMatch m_colorMatcher = new ColorMatch();

  // The currently matched color
  ColorMatchResult match;

  // defines all the colors needed in regards to the sensors inputs through
  // RevRobotics//
  ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  //Defines the different colors we can see
  Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  String fieldSensorColor;
  String robotSensorColor;
  String fieldDataColor;

  public ColorWheel() {
    // This makes it so that the colors match to the RGB values
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }

  // periodic runs every 20 milliseconds to detect the color found and send that
  // reading to the SmartDashboard//
  public void periodic() {

    // Get the color that the robot's color sensor sees
    match = m_colorMatcher.matchClosestColor(m_colorSensor.getColor());

    if (!gameDataSet) {
      // Finds what the position control color will be and converts into a usable
      // string for other code.
      gameData = DriverStation.getInstance().getGameSpecificMessage();
      if (gameData.length() > 0) {
        // Once this code is reached, we don't need to set the color value anymore
        gameDataSet = true;
        switch (gameData.charAt(0)) {
        case 'B':
          fieldDataColor = "Blue";
          break;
        case 'G':
          fieldDataColor = "Green";
          break;
        case 'R':
          fieldDataColor = "Red";
          break;
        case 'Y':
          fieldDataColor = "Yellow";
          break;
        }
      }
    }

    SmartDashboard.putString("Robot Sensor Color", getRobotColor());
    SmartDashboard.putString("Field Sensor Color", getFieldSensorColor());
    SmartDashboard.putString("Field Data Color", getFieldDataColor());
  }

  /**
   * Gets the color that the ROBOT is currently sensing
   * @return The color that the robot is currently sensing
   */
  public String getRobotColor() {

    if (match.color == kBlueTarget) {
      robotSensorColor = "Blue";
    } else if (match.color == kRedTarget) {
      robotSensorColor = "Red";
    } else if (match.color == kGreenTarget) {
      robotSensorColor = "Green";
    } else if (match.color == kYellowTarget) {
      robotSensorColor = "Yellow";
    } else {
      robotSensorColor = "Unknown";
    }

    return robotSensorColor;
  }

  /**
   * Gets the color that the FIELD is currently sensing
   * @return The color that the FIELD is currently sensing
   */
  public String getFieldSensorColor() {

    // These values are offset based on the robot's color sensor reading a different
    // color than the field's color sensor
    if (match.color == kBlueTarget) {
      fieldSensorColor = "Red";
    } else if (match.color == kRedTarget) {
      fieldSensorColor = "Blue";
    } else if (match.color == kGreenTarget) {
      fieldSensorColor = "Yellow";
    } else if (match.color == kYellowTarget) {
      fieldSensorColor = "Green";
    } else {
      fieldSensorColor = "Unknown";
    }

    return fieldSensorColor;
  }

  /**
   * Uses the data given from the field and converts it to usable string
   * @return The color string that the field is sending
   */
  public String getFieldDataColor() {
    // This value is set in periodic
    return fieldDataColor;
  }
}
