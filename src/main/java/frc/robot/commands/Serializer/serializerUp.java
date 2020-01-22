package frc.robot.commands.Serializer;

import frc.robot.subsystems.SerializerElevator;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class serializerUp extends CommandBase {
    private final SerializerElevator m_subsystem;
    
    public SerializerElevator getM_subsystem() {
        return m_subsystem;
      }
    
    public serializerUp(final SerializerElevator serializer){
        m_subsystem = serializer;
        addRequirements(serializer);
    }
 
    @Override
    public void initialize(){
        m_subsystem.setSerializerSpeed(1);

    }
    @Override
    public void execute(){

    }
    @Override 
    public void end(final boolean interrupted){
        m_subsystem.setSerializerSpeed(0);
    }

}
