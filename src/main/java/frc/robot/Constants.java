/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.HashMap;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final double WHEEL_DEADZONE = Math.PI/20; //IDK about this just a guess
    public static final double STICK_DEADZONE = 0.2; //IDK about this just a guess

    public static HashMap<Integer, String> colors = new HashMap<Integer, String>();
    public static final int bufferSize = 50;
    public static final int RED_DETECTIONS = 9;
    public static final double SPINFACTOR = 1.0/10;
    public static final double MAX_ARM_SPEED = Math.PI / 5;
    
    public static final double ARM_UP = 1500;
    public static final double ARM_MID = 1000;
    
    public static final float ARM_P = 0.00045f;
    public static final float ARM_I = 0.00003f;
    public static final float ARM_D = 0.000045f;

    public static final float DRIVE_P = 0f;
    public static final float DRIVE_I = 0f;
    public static final float DRIVE_D = 0f;
    
    public static final double ELEVATOR_UP = 1335;

	public static final double ELEVATOR_DOWN = 0;
    public static final double ELEVATOR_VOLTAGE_THRESHOLD = 0;
    
    public static final double BALL_DETECTION = 300;
    
    public static final double INTAKE_SPEED = 0.6;
    
    public static final double BELT_DEAD_ZONE = 0.05;
    public static final double BELT_SPEED = 0.55;
	public static final double BELT_RUN_TIME = 0.15;
	public static final double BELT_RESET_TIME = 0.75;

	public static final double AUTO_DRIVE_SPEED2 = 0;
	public static final double AUTO_DRIVE_SPEED1 = 0;
	public static final double SPIN_DOWN_FORCE = 0.15;

    public Constants(){

        colors.put(4, "Red");
        colors.put(3, "Yellow");
        colors.put(2, "Blue");
        colors.put(1, "Green");

    }

}
