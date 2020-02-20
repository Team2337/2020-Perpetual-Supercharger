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
    public final class Swerve {
        public final static double SLOWROTATESPEED = 0.05;
        public final static boolean SWERVEDEBUG = false;
    }

    public final class KickerWheel {
        public final static double SHORTVELOCITYP = 0.0001;
    }
    
    /*******************/
    /* --------------- */
    /* --- CAN IDs --- */
    /* --------------- */
    /*******************/

    public static int CANID0;
    public static int MODULE0DRIVEMOTORID;
    public static int MODULE0ANGLEMOTORID;
    public static int MODULE1DRIVEMOTORID;
    public static int MODULE1ANGLEMOTORID;
    public static int MODULE2DRIVEMOTORID;
    public static int MODULE2ANGLEMOTORID;
    public static int MODULE3DRIVEMOTORID;
    public static int MODULE3ANGLEMOTORID;
    public static int INTAKE;
    public static int AGITATOR;
    public static int CLIMBER;
    public static int KICKER;
    public static int SHOOTERLEFTMOTOR;
    public static int SHOOTERRIGHTMOTOR;
    public static int SERIALIZER;

    /***************/
    /* ----------- */
    /* --- PCM --- */
    /* ----------- */
    /***************/

    public static final int PCM0 = 0;
    public static final int PCM1 = 1;

    /*********************/
    /* ----------------- */
    /* --- PCM PORTS --- */
    /* ----------------- */
    /*********************/

    public static int PCM0PORT0 = 0;
    public static int PCM0PORT1 = 1;
    public static int PCM0PORT2 = 2;
    public static int PCM0PORT3 = 3;
    public static int PCM0PORT4 = 4;
    public static int PCM0PORT5 = 5;
    public static int PCM0PORT6 = 6;
    public static int PCM0PORT7 = 7;

    /*********************/
    /* ----------------- */
    /* --- DIO PORTS --- */
    /* ----------------- */
    /*********************/

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

    /************************/
    /* -------------------- */
    /* --- ANALOG PORTS --- */
    /* -------------------- */
    /************************/

    public static int ANALOGPORT0 = 0;
    public static int ANALOGPORT1 = 1;
    public static int ANALOGPORT2 = 2;
    public static int ANALOGPORT3 = 3;

    /***********************/
    /* ------------------- */
    /* --- RELAY PORTS --- */
    /* ------------------- */
    /***********************/

    public static int RELAYPORT0 = 0;
    public static int RELAYPORT1 = 1;
    public static int RELAYPORT2 = 2;
    public static int RELAYPORT3 = 3;

    /*********************/
    /* ----------------- */
    /* --- PWM PORTS --- */
    /* ----------------- */
    /*********************/

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


    /********************/
    /* ---------------- */
    /* --- AGITATOR --- */
    /* ---------------- */
    /********************/

    /** Percent speed of the agitator */
    public static double AGITATORSPEED = 0.2;

    /** Percent speed of the agitator */
    public static double AGITATORREVERSESPEED = -0.2;


    /******************/
    /* -------------- */
    /* --- INTAKE --- */
    /* -------------- */
    /******************/

    /** Percent speed on the intake */
    public static double INTAKESPEED = 0.5;


    /*******************/
    /* --------------- */
    /* --- CLIMBER --- */
    /* --------------- */
    /*******************/

    /** Percent speed on the climber */
    public static double CLIMBERSPEED = 0.7;


    /*******************/
    /* --------------- */
    /* --- SHOOTER --- */
    /* --------------- */
    /*******************/

    /**
     * This value is the number at which the closed loop ramp rate of the shooter
     * goes from 0.5 to 0 to increase speed
     */
    public static int SHOOTERRAMPSWITCHVALUE = 5000;
    
    /** Speed to shoot at from ~16 feet away */
    public static int SHOOTSPEEDCLOSE;

    /** Speed to shoot at from ~34 feet away */
    public static int SHOOTSPEEDFAR;

    /**********************/
    /* ------------------ */
    /* --- SERIALIZER --- */
    /* ------------------ */
    /**********************/

    /** Maximum speed of the serializer */
    public static double SERIALIZERPEAKSPEED = 0.3;

    /** Percent speed on the serializer when moving to positions */
    public static double SERIALIZERPOSITIONSPEED = 0.2;

    /** Percent forward speed when serializing or shooting */
    public static double SERIALIZERFORWARDSPEED = 0.3;

    /** Percent reverse speed when serializing or shooting */
    public static double SERIALIZERREVERSESPEED = -0.3;
    
    /** Amount of ticks to reverse the serializer by when readying the kicker wheel */
    public static double SERIALIZERREGRESSIONDISTANCE = 768;

    /******************/
    /* -------------- */
    /* --- KICKER --- */
    /* -------------- */
    /******************/
    
    /** Kicker wheel velocity for the far shot */
    public static int KICKERSPEEDFAR;

    /** Kicker wheel velocity for the near shot */
    public static int KICKERSPEEDCLOSE;

    /******************/
    /* -------------- */
    /* --- VISION --- */
    /* -------------- */
    /******************/

    /** P value for vision rotation */
    public static double VISIONROTATIONP = 0.65; // 0.85


    /**
     * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
     * constants.  This class should not be used for any other purpose.  All constants should be
     * declared globally (i.e. public static final).  Do not put anything functional in this class.
     *
     * <p>It is advised to static finalally import this class (or one of its inner classes) wherever the
     * constants are needed, to reduce verbosity.
     */
    public Constants() {
        if(Robot.isComp) {
            MODULE0DRIVEMOTORID = 0;
            MODULE1DRIVEMOTORID = 1;
            SHOOTERRIGHTMOTOR = 2;
            SHOOTERLEFTMOTOR = 3;
            MODULE0ANGLEMOTORID = 4;
            MODULE1ANGLEMOTORID = 5;
            KICKER = 6;
            //Limelight = 7
            INTAKE = 8;
            AGITATOR = 9;
            MODULE2ANGLEMOTORID = 10;
            MODULE3ANGLEMOTORID = 11;
            SERIALIZER = 12;
            CLIMBER = 13;
            MODULE2DRIVEMOTORID = 14;
            MODULE3DRIVEMOTORID = 15;

            /* --- Shooter --- */
            SHOOTSPEEDCLOSE = 13000; //11500
            SHOOTSPEEDFAR = 15100; //14800

            /* --- Kicker --- */
            KICKERSPEEDCLOSE = 3000; //500 //750
            KICKERSPEEDFAR = 4500;

            /* --- Vision --- */
            VISIONROTATIONP = 0.65;

        } else {
            CANID0 = 0;
            MODULE0DRIVEMOTORID = 1;
            MODULE0ANGLEMOTORID = 2;
            MODULE1DRIVEMOTORID = 3;
            MODULE1ANGLEMOTORID = 4;
            MODULE2DRIVEMOTORID = 5;
            MODULE2ANGLEMOTORID = 6;
            MODULE3DRIVEMOTORID = 7;
            MODULE3ANGLEMOTORID = 8;
            INTAKE = 9;
            AGITATOR = 10;
            CLIMBER = 11;
            KICKER = 12;
            SHOOTERLEFTMOTOR = 13;
            SHOOTERRIGHTMOTOR = 14;
            SERIALIZER = 15;

            /* --- Shooter --- */
            SHOOTSPEEDCLOSE = 11500;
            SHOOTSPEEDFAR = 14800;

            /* --- Kicker --- */
            KICKERSPEEDCLOSE = 500;
            KICKERSPEEDFAR = 500;

            /* --- Vision --- */
            VISIONROTATIONP = 0.85;

        }
    }
}
