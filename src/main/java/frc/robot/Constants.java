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
    public final class SWERVE {
        // Sets the distances from module to module 
        public static final double WHEELBASE = 22.5; 
        public static final double TRACKWIDTH = 23.5;
        // Length and width of the robot
        public static final double WIDTH = 29;  
        public static final double LENGTH = 30; 
    }

    public final class CANIDS {
        /* --- CAN IDs --- */
        public static final int MODULE0DRIVEMOTORID = 1;
        public static final int MODULE0ANGLEMOTORID = 2;
        public static final int MODULE1DRIVEMOTORID = 3;
        public static final int MODULE1ANGLEMOTORID = 4;
        public static final int MODULE2DRIVEMOTORID = 5;
        public static final int MODULE2ANGLEMOTORID = 6;
        public static final int MODULE3DRIVEMOTORID = 7;
        public static final int MODULE3ANGLEMOTORID = 8;
        public static final int LEFTINTAKE = 9;
        public static final int RIGHTINTAKE = 10;
        public static final int LEFTFEEDER = 11;
        public static final int RIGHTFEEDER = 12;
        public static final int CANID13 = 13;
        public static final int CANID14 = 14;
        public static final int CANID15 = 15;
    }

    public final class PNEUMATICPORTS {
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
    }

    public final class SENSORPORTS {
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
        public static int MODULE0ANALOGSENSOR = 0;
        public static int MODULE1ANALOGSENSOR = 1;
        public static int MODULE2ANALOGSENSOR = 2;
        public static int MODULE3ANALOGSENSOR = 3;

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
    }
}
