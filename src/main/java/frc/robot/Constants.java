package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static final).  Do not put anything functional in this class.
 *
 * <p>It is advised to static finalally import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class Swerve {
        public static final double GEARRATIO = 8.31;
        public static final double WHEELDIAMETER = 4;
        public static final double TICKSPERREVOLUTION = GEARRATIO * 4096;
        public static final double INCHESPERREVOLUTION = WHEELDIAMETER * Math.PI;
        public static final double TICKSPERINCH = 1550;
        public static final double INCHESPERDEGREE = 0.2722;
        public static final double MINVELOCITY = 1;
        public static final double TICKSPERDEGREE = 102;
        public final static double SLOWROTATESPEED = 0.05;
    }
    public final class Vision {
        public final static double VISIONROTATIONP = 0.85;
    }
    /* --- CAN IDs --- */
    public static int CANID0 = 0;
    public static int MODULE0DRIVEMOTORID = 1;
    public static int MODULE0ANGLEMOTORID = 2;
    public static int MODULE1DRIVEMOTORID = 3;
    public static int MODULE1ANGLEMOTORID = 4;
    public static int MODULE2DRIVEMOTORID = 5;
    public static int MODULE2ANGLEMOTORID = 6;
    public static int MODULE3DRIVEMOTORID = 7;
    public static int MODULE3ANGLEMOTORID = 8;
    public static int INTAKE = 9;
    public static int AGITATOR = 10;
    public static int CLIMBER = 11;
    public static int KICKER = 12;
    public static int SHOOTERLEFTMOTOR = 13;
    public static int SHOOTERRIGHTMOTOR = 14;
    public static int SERIALIZER = 15;

    /* --- PCMs --- */
    public static final int PCM0 = 0;
    public static final int PCM1 = 1;

    /* --- PCM Ports --- */
    public static int PCM0PORT0 = 0;
    public static int PCM0PORT1 = 1;
    public static int PCM0PORT2 = 2;
    public static int PCM0PORT3 = 3;
    public static int PCM0PORT4 = 4;
    public static int PCM0PORT5 = 5;
    public static int PCM0PORT6 = 6;
    public static int PCM0PORT7 = 7;

    /* --- DIO Ports --- */
    public static int DIOPORT0 = 0;
    public static int DIOPORT1 = 1;
    public static int DIOPORT2 = 2;
    public static int DIOPORT3 = 3;
    public static int DIOPORT4 = 4;
    public static int DIOPORT5 = 5;
    public static int DIOPORT6 = 6;
    public static int DIOPORT7 = 7;
    public static int DIOPORT8 = 8;
    public static int DIOPORT9 = 9;

    /* --- Analog Ports --- */
    public static int ANALOGPORT0 = 0;
    public static int ANALOGPORT1 = 1;
    public static int ANALOGPORT2 = 2;
    public static int ANALOGPORT3 = 3;

    /* --- Relay Ports --- */
    public static int RELAYPORT0 = 0;
    public static int RELAYPORT1 = 1;
    public static int RELAYPORT2 = 2;
    public static int RELAYPORT3 = 3;

    /* --- PWM Ports --- */
    public static int PWMPORT0 = 0;
    public static int PWMPORT1 = 1;
    public static int PWMPORT2 = 2;
    public static int PWMPORT3 = 3;
    public static int PWMPORT4 = 4;
    public static int PWMPORT5 = 5;
    public static int PWMPORT6 = 6;
    public static int PWMPORT7 = 7;
    public static int PWMPORT8 = 8;
    public static int PWMPORT9 = 9;

    /* --- TIME OF FLIGHT Variables --- */
    /** Configure range mode. 0=short; 1=medium; 2=long */
    public static int TOFMODE = 0;


    /* --- Agitator --- */
    public static double AGITATORSPEED = 0.4;
    public static double AGITATORREVERSESPEED = -0.4;


    /* --- Intake --- */
    public static double INTAKESPEED = 0.4;


    /* --- Climber --- */
    public static double CLIMBERSPEED = 0.4;


    /* --- Shooter Values --- */
    /**
     * This value is the number at which the closed loop ramp rate of the shooter
     * goes from 0.5 to 0 to increase speed
     */
    public static int SHOOTERRAMPSWITCHVALUE = 5000;
    
    // Speed to shoot at from ~16 feet away
    public static int SHOOTSPEEDCLOSE = 14450;
    // Speed to shoot at from ~34 feet away
    public static int SHOOTSPEEDFAR = 15700; //15295

    /* ---Serializer--- */
    //Maximum speed of the serializer
    public static double SERIALIZERPEAKSPEED = 0.3;
    public static double SERIALIZERPOSITIONSPEED = 0.1;
    public static double SERIALIZERFORWARDSPEED = 0.2;
    public static double SERIALIZERREVERSESPEED = -0.2;
    
    //Amount of ticks to reverse the serializer by when readying the kicker wheel
    public static double SERIALIZERREGRESSIONDISTANCE = 768;

    /* --- Kicker --- */
    public static int KICKERSPEED = 3000;
}
