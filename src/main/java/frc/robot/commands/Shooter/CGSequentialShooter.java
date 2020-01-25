package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shooter;


public class CGSequentialShooter extends SequentialCommandGroup {
    public Shooter m_subsystem;
    public CGSequentialShooter(Shooter shooter, double sec) {
        m_subsystem = shooter;
        addCommands(
            new shootSingleBall(m_subsystem, 21000), 
             new shooterWait(m_subsystem).withTimeout(sec),
             new shootContinuously(m_subsystem, 21000)
        );
    }
}