package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Auton.Positions;
import java.util.ArrayList;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.*;


  /**
   * The vision code for the limelight and camera
   * @author Madison J., Zayd A., Nicholas S., and Michael F.
   * @category VISION
   */
public class Vision extends SubsystemBase {
  public AnalogInput pixyLeftAnalog;
  public AnalogInput pixyRightAnalog;
  public DigitalInput pixyLeftDigital;
  public DigitalInput pixyRightDigital;

  public Pixy2 pixySPI;
  
  private boolean rotateLimelight = false;
  private boolean visionDebug = true;

  // private Positions autonPath = Positions.C; //Used in PixyCam analog/digital auton selection

  public Vision() {
    pixyRightAnalog = new AnalogInput(4);
    pixyRightDigital = new DigitalInput(5);
    pixyLeftAnalog = new AnalogInput(5);
    pixyLeftDigital = new DigitalInput(6);
    //SPI testing
    pixySPI = Pixy2.createInstance(new SPILink());
    pixySPI.init();
  }

  ///////////////////////////////
  /* ------------------------- */
  /* --- LIMELIGHT METHODS --- */
  /* ------------------------- */
  ///////////////////////////////

  /**
   * Sets the LED mode to on, off, or blink
   * @param mode - the mode of the LEDs
   * Example: 
   * 0: Sets the mode to what is in the current pipeline
   * 1: Turns off the LEDs
   * 2: Blink mode on LEDs
   * 3: Turns on the LEDs
   */
  public void setLEDMode(int mode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(mode);
  }

  /**
    * Gets the Limelight mode number from the NetworkTable
    * @return - returns the mode number from the NetworkTable 
    */
  public int getLEDMode() {
    return (int)NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").getValue().getDouble();
  }

  /**
   * Sets the pipeline of the limelight
   * @param pipeline - sets the desired pipeline number between 0-9
   * 0 - CloseVision (0-15 ft away from the tower)
   * 1 - MediumVision (between 15-21 ft)
   * 2 - FarVision (21-26 ft)
   * 9 - Drivecam
   */
  public void switchPipeLine(int pipeline) {
    double currentPipeline = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0);
    if(currentPipeline != pipeline){
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
    }
  }

  /**
   * Gets the current pipeline on the limelight
   * @return - Double value limelight pipeline (0 -> 9)
   */
  public double getPipeline() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0);
  }

  /**
   * This will get the value from tx, ta, etc. by using a string
   * @param output - string double value
   * @return - returns the double value from the string for example from ta, tx, etc.
   */
  public double getDoubleValue(String output) {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry(output).getDouble(0);
  }
  /**
   * This will get the X coordinte from Opensight
   * @return - returns the x-coordinate value, which will show how far we need to rotate
   */
  public double getOpenSightXCoordinateValue() {
    return NetworkTableInstance.getDefault().getTable("PutCoordinate").getEntry("coord-x").getDouble(0);
  }
  /**
   * This will get the Y coordinate from Opensight
   * @return - returns the y-coordinate value, which will show how far the robot needs to drive
   */
  public double getOpenSightYCoordinateValue() {
    return NetworkTableInstance.getDefault().getTable("PutCoordinate").getEntry("coord-y").getDouble(0);
  }
  /**
   * Receives the NetworkTable values from Opensight
   * @return - returns the opensight networktable which will return a true/false value
   */
  public boolean getOpenSightNTValue() {
    return NetworkTableInstance.getDefault().getTable("PutNT").getEntry("succ").getBoolean(false);
  }

  /**
   * Lets us know if we are in the limelight mode, we are rotating using the limelight
   * @param rotateLimelight - Boolean value (limelight mode: true | not limelight mode: false)
   */
  public void setRotateLimelight(boolean rotateLimelight) {
    this.rotateLimelight = rotateLimelight;
  }

  /**
   * Lets us know if we are in the limelight mode, we are rotating using the limelight
   * @return - Boolean value limelight mode
   */
  public boolean getRotateLimelight() {
    return rotateLimelight;
  }
  
  ////////////////////////////////////////////
  /* -------------------------------------- */
  /* --- PIXYCAM ANALOG/DIGITAL METHODS --- */
  /* -------------------------------------- */
  ////////////////////////////////////////////

  /**
   * Gets the voltage of the right Pixy camera
   * @return The voltage of the Pixy camera
   */
  public double getPixyRightValue() {
    return (pixyRightAnalog.getVoltage() * (60 / 3.3) - 30) /2;
  }
  
  /**
   * Gets the voltage of the left Pixy camera
   * @return The voltage of the Pixy camera
   */
  public double getPixyLeftValue() {
    return (pixyLeftAnalog.getVoltage() * (60 / 3.3) - 30) / 2;
  }

  public boolean getPixyRightTarget() {
    return pixyRightDigital.get();
  }

  public boolean getPixyLeftTarget() {
    return pixyLeftDigital.get();
  }

  /////////////////////////////////
  /* --------------------------- */
  /* --- PIXYCAM SPI METHODS --- */
  /* --------------------------- */
  /////////////////////////////////

  /**
   * Gets the number of targets currently in the view of the PixyCam, up to 48 (the total number of balls)
   * @return The number of targets currently in the view of the PixyCam.
   */
  public int getNumberOfTargets(){
    int blockCount = pixySPI.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 48);
    System.out.println("Pixy: " + pixySPI.getCCC().getBlocks(true));
    return blockCount;
  }
  /**
   * Returns the information for all targets in view of the PixyCam
   * @return An ArrayList containing Block classes that contain the information for every target.
   * Returns null if no targets were found.
   * @see Block
   */
  public ArrayList<Block> getAllTargets(){
    //Get the number of targets
    int blockCount = getNumberOfTargets();
    //If there are no targets, return nothing
    if(blockCount <= 0){
      return null;
    }
    ArrayList<Block> blocks = pixySPI.getCCC().getBlockCache();
    return blocks;
  }
  /**
   * Finds the biggest target in view of the PixyCam
   * @return A Block class of the biggest target in view of the PixyCam.
   * Returns null if no targets were found.
   * @see Block
   */
  public Block getBiggestTarget(){
    int blockCount = getNumberOfTargets();
    //If there are no targets, stop trying to look for them.
		if(blockCount <= 0){
			return null;
    }
    //Get all the targets
		ArrayList<Block> blocks = getAllTargets();
    Block largestBlock = null;
    //Loops through all targets and finds the widest one
		for(Block block : blocks){
			if (largestBlock == null){
        //If this is the first iteration, set largestBlock to the current block.
				largestBlock = block;
			} else if(block.getWidth() > largestBlock.getWidth()){
        //If we find a wider block, set largestBlock to the current block.
				largestBlock = block;
			}
		}
		return largestBlock;
  }
  /**
   * Gets the width of the largest target
   * @return The width of the largest target. Returns -1 if there is no target.
   */
  public int getBiggestTargetWidth(){
    Block block = getBiggestTarget();
    if(block == null) return -1;
    return block.getWidth();
  }
  /**
   * Gets the height of the largest target
   * @return The height of the largest target. Returns -1 if there is no target.
   */
  public int getBiggestTargetHeight(){
    Block block = getBiggestTarget();
    if(block == null) return -1;
    return block.getHeight();
  }
  /**
   * Gets the x-position of the largest target
   * @return The x-position of the largest target from 0 to 316.
   * Returns -1 if no target was found.
   */
  public int getBiggestTargetXPos(){
    Block block = getBiggestTarget();
    if(block == null) return -1;
    return block.getX();
  }
  /**
   * Gets the y-position of the largest target
   * @return The y-position of the largest target from 0 to 208.
   * Returns -1 if no target was found.
   */
  public int getBiggestTargetYPos(){
    Block block = getBiggestTarget();
    if(block == null) return -1;
    return block.getY();
  }
  /**
   * Uses the target x-position to find the angle to turn to reach the target.
   * @return An angle in degrees from -15 to 15 that the robot should turn.
   * As a failsafe, this will return 0 if there is no target detected.
   */
  public int getBiggestTargetAngle(){
    Block block = getBiggestTarget();
    if(block == null) return -1;
    return block.getAngle();
  }

  ///////////////////////////
  /* --------------------- */
  /* --- OTHER METHODS --- */
  /* --------------------- */
  ///////////////////////////

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(visionDebug) {
      //PixyCam values
      SmartDashboard.putNumber("PixyCam Number of Targets", getNumberOfTargets());
      SmartDashboard.putNumber("PixyCam Biggest Target X", getBiggestTargetXPos());
      SmartDashboard.putNumber("PixyCam Biggest Target Y", getBiggestTargetYPos());
      SmartDashboard.putNumber("PixyCam Biggest Target Angle", getBiggestTargetAngle());
      /*
      SmartDashboard.putNumber("Pixy Right Raw Analog Voltage", pixyRightAnalog.getVoltage());
      SmartDashboard.putNumber("Pixy Right Analog Degrees", getPixyRightValue());
      SmartDashboard.putNumber("Pixy Left Raw Analog Voltage", pixyLeftAnalog.getVoltage());
      SmartDashboard.putNumber("Pixy Left Analog Degrees", getPixyLeftValue());
      SmartDashboard.putBoolean("Pixy Left sees target", pixyLeftDigital.get());
      SmartDashboard.putBoolean("Pixy Right sees target", pixyRightDigital.get());
      //Auton
      SmartDashboard.putNumber("Pixy-Detected Auton Value", detectAutonPath(autonPath));
      String testAutonPath;
      switch(detectAutonPath(autonPath)){
        default:
        case -1:
          testAutonPath = "Auton Error";
          break;
        case 0:
          testAutonPath = "Path A red";
          break;
        case 1:
          testAutonPath = "Path B red";
          break;
        case 2:
          testAutonPath = "Path A blue";
          break;
        case 3:
          testAutonPath = "Path B blue";
          break;
      }
      SmartDashboard.putString("Pixy Auton String Test", testAutonPath);
      */
    }
  }

  /**
   * Uses the right PixyCam digital/analog to detect which auton to use.
   * @param pos A letter B or C, indicating the starting position the left side of the robot is aligned with.
   * (Yes there are other options in the Constants file, but only B and C are programmed)
   * @return A number determining which auton to use.
   * <ul>
   * <li>-1 - <b>Cannot determine path.</b> Default value
   * <li> 0 - Path A red
   * <li> 1 - Path B red
   * <li> 2 - Path A blue
   * <li> 3 - Path B blue
   * </ul>
   */
  public int detectAutonPath(Positions pos){
    int auton = -1;
    boolean seesBall = getPixyRightTarget();
    double degrees = getPixyRightValue();

    //Detect if we see a ball. If we do, where is it?
    if(seesBall){
      switch (pos){
        //Aligned with path B
        case B:
          //Detect if ball is in a red spot
          if(degrees > 8.5 && degrees < 9.5){
            auton = 0;//Path A red
          } else if(degrees >= -2.5 && degrees < 2.5){
            auton = 1;//Path B red
          } else if(degrees > 7.5 && degrees < 8.5){
            auton = 2;//Path A blue
          } else if(degrees > 10 && degrees < 15){
            auton = 3;//Path B blue
          }
          //else we have a strange circumstance
          break;
        //Aligned with path C
        case C:
          //Detect if ball is in a red spot
          if(degrees > -5.5 && degrees < -4){
            auton = 0;//Path A red
          } else if(degrees < -10){
            auton = 1;//Path B red (might be out of view here)
          } else if(degrees > 5.5 && degrees < 7.5){
            auton = 2;//Path A blue
          } else if(degrees > -1 && degrees < 2){
            auton = 3;//Path B blue
          }
          break;
        //Anything else
        default:
          System.out.println("Auton detection: unavailable selection");
          break;
      }
    }
    return auton;
  }
}