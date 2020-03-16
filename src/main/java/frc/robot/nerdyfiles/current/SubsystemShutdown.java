package frc.robot.nerdyfiles.current;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.Robot;
import frc.robot.nerdyfiles.controller.NerdyUltimateXbox;
import frc.robot.nerdyfiles.subsystems.NerdySubsystem;

/**
 * This class is used to monitor other Nerdy Subsystems
 * to shutdown subsystems as they go over the specified
 * current limit. 
 * A shutdown simply means cancelling the command currently running,
 * and vibrating the specified controller
 * 
 * @see NerdySubsystem 
 * @author Team 2337 - The EngiNERDs
 * @version 0.1.0 - Experimental
 */
public class SubsystemShutdown {
    /* --- Array Lists --- */

    /** Array list of Nerdy Subsystems in order of their importance */
    private ArrayList<NerdySubsystem> subsystems = new ArrayList<>();

    private NerdyUltimateXbox controller;

    /* --- Double Values --- */
    /** Maximum robot current allowed before subsystems are shutdown */
    private double maxRobotCurrentLimit = 120;

    /** Robot's total current */
    private double totalRobotCurrent = 0;

    /* --- Integer Values --- */

    /** How many times the robot has iterated since this system has been active */
    private int iterations = 0;

    /** How many iterations have gone by since the shutdown occured  */
    private int shutDownIteration = 0;

    /**
     * This class is used to monitor other Nerdy Subsystems
     * to shutdown subsystems as they go over the specified
     * current limit. 
     * A shutdown simply means cancelling the command currently running,
     * and vibrating the specified controller
     * 
     * @param subsystems - Array List of NerdySubsystem objects
     * @see Robot
     */
    public SubsystemShutdown(ArrayList<NerdySubsystem> subsystems) {
        this.subsystems = subsystems;
    }

    /**
     * Sets the max current limit on the entire robot
     * @param maxCurrentLimit - double value in amps
     */
    public void setMaxCurrentLimit(double maxCurrentLimit) {
        this.maxRobotCurrentLimit = maxCurrentLimit;
    }

    /**
     * Gets the max current limit for the entire robot that is currently set. 
     * (Default: 120)
     * @return - double value in amps 
     */
    public double getMaxCurrentLimit() {
        return this.maxRobotCurrentLimit;
    }

    /**
     * Gets the robot's total current 
     * @return - double value in amps
     */
    public double getTotalRobotCurrent() {
        for(int i=0; i < subsystems.size(); i++) {
            totalRobotCurrent += Robot.PDP.getCurrent(i);
        }
        return totalRobotCurrent;
    }

    /**
     * Watches the current of the entire robot.
     * Will disable subsystems in order of importance 
     * if the max robot current is overriden for more 
     * than the total allowed iterations 
     */
    public void currentWatch() {
        iterations++;
        if(getTotalRobotCurrent() > maxRobotCurrentLimit) {
            shutDownIteration = iterations;
            shutDownSubsystems(iterations);
        } else {
            shutDownSubsystems(shutDownIteration);
        }
    }

    /**
     * Looks at each individual subsystem with smart current enabled
     * and individually disables subsystems, if they are over the max current
     */
    public void smartCurrentWatch() {
        for(int i=0; i<subsystems.size(); i++) {
            if(subsystems.get(i).smartCurrentShutdownEnabled()) {
                if(subsystems.get(i).totalMotorCurrent() >= subsystems.get(i).maxSubsystemCurrent()) {
                    shutDownSubsystems(i);
                }
            }
        }
    }

    public void setController(NerdyUltimateXbox controller) {
        this.controller = controller;
    }

    /**
     * Shutsdown the specified subsystem
     * @param iterations - int value for the subsystem in the array 
     */
    private void shutDownSubsystems(int iterations) {
        subsystems.get(0).getCurrentCommand().cancel();
        this.controller.setRumble(RumbleType.kLeftRumble, 1.0);
        //TODO: Shutoff rumble
        this.controller.setRumble(RumbleType.kLeftRumble, 0);
    }
}