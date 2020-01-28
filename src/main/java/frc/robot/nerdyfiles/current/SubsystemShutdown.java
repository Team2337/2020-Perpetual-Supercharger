package frc.robot.nerdyfiles.current;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class SubsystemShutdown {
    private ArrayList<SubsystemBase> subsystems = new ArrayList<>();

    private double maxRobotCurrentLimit;
    private double totalRobotCurrent = 0;
    /**
     * How many times the robot has iterated since this system has been active
     */
    private int iterations = 0;
    private int shutDownIteration = 0;

    public SubsystemShutdown(ArrayList<SubsystemBase> subsystems) {
        this.subsystems = subsystems;
    }

    public void setMaxCurrentLimit(double maxCurrentLimit) {
        this.maxRobotCurrentLimit = maxCurrentLimit;
    }

    public double getMaxCurrentLimit() {
        return this.maxRobotCurrentLimit;
    }

    public double getTotalRobotCurrent() {
        for(int i=0; i < subsystems.size(); i++) {
            totalRobotCurrent += Robot.PDP.getCurrent(i);
        }
        return totalRobotCurrent;
    }

    public void currentWatch() {
        iterations++;
        
        if(getTotalRobotCurrent() > maxRobotCurrentLimit) {
            shutDownIteration = iterations;
            shutDownSubsystems(iterations);
        } else {
            shutDownSubsystems(shutDownIteration);
        }
    }

    private void shutDownSubsystems(int iterations) {
        subsystems.get(0).getCurrentCommand().cancel();
    }

}