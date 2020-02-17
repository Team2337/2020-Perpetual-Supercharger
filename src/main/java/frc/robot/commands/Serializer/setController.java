package frc.robot.commands.Serializer;

import frc.robot.subsystems.Serializer;
import frc.robot.subsystems.KickerWheel;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Holds the serializer position
 * @author Nicholas Stokes
 */
public class setController extends InstantCommand {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Serializer serializer;
    private final KickerWheel kickerWheel;
    public boolean isDriver;
    public boolean isControlling;

    /**
     * Holds the serializer position
     */
    public setController(Serializer serializer, KickerWheel kickerWheel, boolean isDriver, boolean isControlling) {
    this.serializer = serializer;
    this.kickerWheel = kickerWheel;
    this.isDriver = isDriver;
    this.isControlling = isControlling;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(serializer, kickerWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    serializer.setCoOp(isDriver, isControlling);
    kickerWheel.setCoOp(isDriver, isControlling);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}
