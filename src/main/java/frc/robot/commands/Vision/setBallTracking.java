package frc.robot.commands.Vision;

import frc.robot.subsystems.OperatorAngleAdjustment;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Sets whether or not we're tracking a ball
 */
public class setBallTracking extends InstantCommand {
    private OperatorAngleAdjustment operatorAngleAdjustment;
    private boolean ballTrackingEnabled;

    /**
     * Sets whether or not we're tracking a ball
     * 
     * @param operatorAngleAdjustment The OperatorAngleAdjustment subsystem
     * @param ballTrackingEnabled     Whether or not we're tracking a ball
     */
    public setBallTracking(OperatorAngleAdjustment operatorAngleAdjustment, boolean ballTrackingEnabled) {
        this.operatorAngleAdjustment = operatorAngleAdjustment;
        this.ballTrackingEnabled = ballTrackingEnabled;
        addRequirements(operatorAngleAdjustment);
    }

    @Override
    public void initialize() {
        operatorAngleAdjustment.setBallTrackingEnabled(ballTrackingEnabled);
    }
}