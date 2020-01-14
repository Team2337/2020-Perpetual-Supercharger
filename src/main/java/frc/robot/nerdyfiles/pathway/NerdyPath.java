package frc.robot.nerdyfiles.pathway;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.Auto.pathway;
import frc.robot.nerdyfiles.NeoNerdyDrive;
import frc.robot.nerdyfiles.TalonNerdyDrive;
import jaci.pathfinder.modifiers.TankModifier;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

/**
 * Stores the code for all of Jaci Pathfinder's autonomous path writer
 * 
 * @author Bryce G. 
 */
public class NerdyPath {
  boolean pathfinderDebug = false;

  /* --- Pathfinder Variables --- */
  private int ticksPerRev = 12078; //13988

  private double inchesToMeters = 0.0254;
  private double wheelDiameter = 6.375 * inchesToMeters;
  private double wheelBase = 20.5 * inchesToMeters; // old practice bot: 21.5
  private double leftOutput, rightOutput, gyro_heading, desired_heading, turn, angleDifference;
  private double turnCompensation = 0.8 * (-1.0 / 80.0); 

  private String filePath = "/home/lvuser/deploy/";
  private String csv = ".csv";
  // private String binary = ".txt";

  public TankModifier modifier;
  public EncoderFollower rightSideFollower;
  public EncoderFollower leftSideFollower;
  public Trajectory.Config config;

  public static NeoNerdyDrive neoDrive;
  public TalonNerdyDrive nerdyDrive;

  public NerdyPath() {
    neoDrive = new NeoNerdyDrive(Robot.Chassis.neoLeftFrontMotor, Robot.Chassis.neoRightFrontMotor);
  }

  /**
   * Use this in execute, if driving forward. These variables are constantly being
   * updated
   */
  public void makePathForawrd() {
    leftOutput = leftSideFollower.calculate((int) Robot.Chassis.getLeftPosition());
    rightOutput = rightSideFollower.calculate((int) Robot.Chassis.getRightPosition());

    gyro_heading = Robot.Pigeon.getYaw();
    desired_heading = Pathfinder.r2d(leftSideFollower.getHeading());

    angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
    turn = turnCompensation * angleDifference;

    neoDrive.tankDrive(leftOutput + turn, rightOutput - turn, false);
  }

  /**
   * Use this in execute, if driving in reverse. These variables are constantly
   * being updated
   */
  public void makePathReverse() {
    //Made values negative to create a reverse trajectory
    leftOutput = leftSideFollower.calculate(-(int) Robot.Chassis.getLeftPosition());
    rightOutput = rightSideFollower.calculate(-(int) Robot.Chassis.getRightPosition());

    gyro_heading = -Robot.Pigeon.getYaw();
    desired_heading = Pathfinder.r2d(leftSideFollower.getHeading());

    angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
    turn = turnCompensation * angleDifference;

    //Changed to negative values to make the robot run in the same direction as the calculated values
    neoDrive.tankDrive(-(leftOutput + turn), -(rightOutput - turn), false);
  }

  /**
   * Sets the trajectory for the robot to follow
   * @param trajectory - input the trajectory of the path you want to follow
   * @param kP - Preportion value of the PID - generally around 1.0
   * @param kI - Intergral value of the PID - brings the plateu closer to the desired position
   * @param kD - Derivitive (dampener) value of the PID
   * @param kA - Starting acceloration value - useful for quick starts
   * @param maxVelocity - the maximum velocity of the chassis, in meters per second
   */
  public void setTrajectory(Trajectory trajectory, double kP, double kI, double kD, double kA, double maxVelocity) {
    modifier = new TankModifier(trajectory).modify(wheelBase);
    config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.1, maxVelocity, 0.25, 2.5);

    leftSideFollower = new EncoderFollower(modifier.getLeftTrajectory());
    rightSideFollower = new EncoderFollower(modifier.getRightTrajectory());

    leftSideFollower.configurePIDVA(kP, kI, kD, 1 / maxVelocity, kA);
    rightSideFollower.configurePIDVA(kP, kI, kD, 1 / maxVelocity, kA);

    leftSideFollower.configureEncoder((int) Robot.Chassis.getLeftPosition(), ticksPerRev, wheelDiameter);
    rightSideFollower.configureEncoder((int) Robot.Chassis.getRightPosition(), ticksPerRev, wheelDiameter);
  }

  /**
   * Sets the trajectory for the robot based off of the waypoints given in the
   * Pathway.java file
   * 
   * @see Pathway.java
   * @param trajectory - set of waypoints being created in Robot.java
   * @param kP         - P value in PID, typically is around 1.0
   * @param kI         - I value in PID, typically is around 0.0
   * @param kD         - D value in PID, helpful to not overshoot, usually around
   *                   0.0 - 0.05
   * @param kA         - Beginning acceloration value, (aka feet forward),
   *                   typically around 0.0 unless you want a quick start out of
   *                   the gate
   */
  public void setTrajectory(Trajectory trajectory, double kP, double kI, double kD, double kA) {
    modifier = new TankModifier(trajectory).modify(wheelBase);

    leftSideFollower = new EncoderFollower(modifier.getLeftTrajectory());
    rightSideFollower = new EncoderFollower(modifier.getRightTrajectory());

    leftSideFollower.configurePIDVA(kP, kI, kD, 1 / pathway.config.max_velocity, kA);
    rightSideFollower.configurePIDVA(kP, kI, kD, 1 / pathway.config.max_velocity, kA);

    leftSideFollower.configureEncoder((int) Robot.Chassis.getLeftPosition(), ticksPerRev, wheelDiameter);
    rightSideFollower.configureEncoder((int) Robot.Chassis.getRightPosition(), ticksPerRev, wheelDiameter);
  }

  /**
   * Writes a generated trajectory to the deploy folder on the robot
   * <br/> Only needs to be run one time in order to write the file to the robot
   * <br/> <p><strong>**NOTE</strong>: Make sure the file permissions on the deploy folder are able to be written to**</p>
   * @param fileName - name of the file you want to create and write to
   * @param trajectory - the desired trajectory to write to the file
   */
  public void writeFile(String fileName, Trajectory trajectory) {
    try {
      File file = new File(filePath + fileName + csv); // writing to csv
      file.createNewFile();
      Pathfinder.writeToCSV(file, trajectory);
      System.out.println("**************** Info Written To File ****************");
    } catch (IOException e) {
      System.out.println("*** ERROR WHEN WRITING TRAJECTORY TO FILE: " + e.getMessage() + " *******************");
    }
  }


  /**
   * Reads in a file from the rio deploy folder, then converting it from a data stream to a trajectory
   * <br/>Filetype is currently set to a csv, if using binary, change to txt
   * @param fileName - Name of the file you're reading from
   * @return - returns a trajectory
   */
  public Trajectory readFile(String fileName) {
    try {
      File file = new File(filePath + fileName + csv); // writing to csv
      BufferedReader br = new BufferedReader(new FileReader(file));
      file.createNewFile(); //creating new file to read from
      file.setReadable(true, false);
      br.close();
      return Pathfinder.readFromCSV(file);
    } catch (IOException e) {
      System.out.println("********ERROR: CANNOT READ FROM FILE: " + e + " *******************");
    }
    return null;
  }

  public void periodic() {
    if (pathfinderDebug) {
      SmartDashboard.putNumber("Encoder Follower: LEFT error", leftSideFollower.error);
      SmartDashboard.putNumber("Encoder Follower: RIGHT error", rightSideFollower.error);
      SmartDashboard.putNumber("Encoder Follower: seg.position", rightSideFollower.seg.position);

      SmartDashboard.putNumber("RightVelocity", Robot.CargoIntake.CargoIntakeMotor.getSelectedSensorVelocity());
      SmartDashboard.putNumber("LeftVelocity", Robot.Chassis.leftFrontMotor.getSelectedSensorVelocity());
      SmartDashboard.putNumber("Turn Value", this.turn);
      SmartDashboard.putNumber("AngleDifferance", this.angleDifference);
      SmartDashboard.putNumber("leftOutput", this.leftOutput);
      SmartDashboard.putNumber("rightOutput", this.rightOutput);
    }
  }
}