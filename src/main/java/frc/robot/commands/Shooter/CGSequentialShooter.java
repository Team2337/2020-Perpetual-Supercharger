package frc.robot.commands.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shooter;

/**
 * A command group that runs shooter commands in a sequential order.
 * @author Nicholas Stokes, Michael Francis
 */
public class CGSequentialShooter extends SequentialCommandGroup {
    public Shooter m_subsystem;
    /** 
     * 
     * @param shooter Robot.Shooter
     * @param speed The speed that the shooter will shoot at
     * @param sec The amount of seconds
     */
    public CGSequentialShooter(Shooter shooter, double speed, double sec) {
        m_subsystem = shooter;
        addCommands(
            new shootSingleBall(m_subsystem, speed), 
             new shooterDoNothing(m_subsystem).withTimeout(sec),
             new shootContinuously(m_subsystem, speed)
        );
    }
}