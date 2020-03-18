/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.HashMap;

public final class Constants {

    /*
     _____     ______     __     __   __   ______     ______   ______     ______     __     __   __    
    /\  __-.  /\  == \   /\ \   /\ \ / /  /\  ___\   /\__  _\ /\  == \   /\  __ \   /\ \   /\ "-.\ \   
    \ \ \/\ \ \ \  __<   \ \ \  \ \ \'/   \ \  __\   \/_/\ \/ \ \  __<   \ \  __ \  \ \ \  \ \ \-.  \  
     \ \____-  \ \_\ \_\  \ \_\  \ \__|    \ \_____\    \ \_\  \ \_\ \_\  \ \_\ \_\  \ \_\  \ \_\\"\_\ 
      \/____/   \/_/ /_/   \/_/   \/_/      \/_____/     \/_/   \/_/ /_/   \/_/\/_/   \/_/   \/_/ \/_/ 
     */
    public static final double WHEEL_DEADZONE = 0.001; //IDK about this just a guess
    public static final double STICK_DEADZONE = 0.1; //IDK about this just a guess

    public static final float DRIVE_P = 0.008f;
    public static final float DRIVE_I = 0.0075f;
    public static final float DRIVE_D = 0.0001f;
    
    public static final double AUTO_SPIN_SPEED = .3;

    public static final double TICKS_PER_INCH = 0.549020449317;

    public static final double DRIVETRAIN_LAG_TIMER = 0.2;



    /*
     __     __   __     ______   ______     __  __     ______    
    /\ \   /\ "-.\ \   /\__  _\ /\  __ \   /\ \/ /    /\  ___\   
    \ \ \  \ \ \-.  \  \/_/\ \/ \ \  __ \  \ \  _"-.  \ \  __\   
     \ \_\  \ \_\\"\_\    \ \_\  \ \_\ \_\  \ \_\ \_\  \ \_____\ 
      \/_/   \/_/ \/_/     \/_/   \/_/\/_/   \/_/\/_/   \/_____/ 
     */
    public static final double INTAKE_SPEED = 0.6;
    
    public static final double INTAKE_REVERSE_SPEED = -0.25;



    /*
     ______     ______     __    __    
    /\  __ \   /\  == \   /\ "-./  \   
    \ \  __ \  \ \  __<   \ \ \-./\ \  
     \ \_\ \_\  \ \_\ \_\  \ \_\ \ \_\ 
      \/_/\/_/   \/_/ /_/   \/_/  \/_/ 
     */
    public static final double ARM_UP = 1500;
    public static final double ARM_MID = 1000;
    
    public static final float ARM_P = 0.00045f;
    public static final float ARM_I = 0.00003f;
    public static final float ARM_D = 0.000045f;



    /*
     ______     ______     __         ______  
    /\  == \   /\  ___\   /\ \       /\__  _\ 
    \ \  __<   \ \  __\   \ \ \____  \/_/\ \/ 
     \ \_____\  \ \_____\  \ \_____\    \ \_\ 
      \/_____/   \/_____/   \/_____/     \/_/ 
     */
    public static final double BELT_DEAD_ZONE = 0.05;
    public static final double BELT_SPEED = 0.55;
	public static final double BELT_OUTTAKE_SPEED = 0.7;
    public static final double BELT_OUTTAKE_TIME = 2.0;



    /*
     ______     __         ______     __   __   ______     ______   ______     ______    
    /\  ___\   /\ \       /\  ___\   /\ \ / /  /\  __ \   /\__  _\ /\  __ \   /\  == \   
    \ \  __\   \ \ \____  \ \  __\   \ \ \'/   \ \  __ \  \/_/\ \/ \ \ \/\ \  \ \  __<   
     \ \_____\  \ \_____\  \ \_____\  \ \__|    \ \_\ \_\    \ \_\  \ \_____\  \ \_\ \_\ 
      \/_____/   \/_____/   \/_____/   \/_/      \/_/\/_/     \/_/   \/_____/   \/_/ /_/ 
     */    
    public static final double ELEVATOR_UP = -26700;
	public static final double ELEVATOR_DOWN = 0;


    /*
     ______     ______     __         ______     ______    
    /\  ___\   /\  __ \   /\ \       /\  __ \   /\  == \   
    \ \ \____  \ \ \/\ \  \ \ \____  \ \ \/\ \  \ \  __<   
     \ \_____\  \ \_____\  \ \_____\  \ \_____\  \ \_\ \_\ 
      \/_____/   \/_____/   \/_____/   \/_____/   \/_/ /_/                                               
     */
    public static HashMap<String, Integer> colors = new HashMap<String, Integer>();
    public static final int bufferSize = 50;
    public static final int RED_DETECTIONS = 9;
    public static final double SPINFACTOR = 1.0/10;
    public static final double MAX_ARM_SPEED = Math.PI / 5;
	public static final double SPIN_DOWN_FORCE = 0.15;

    public Constants(){

        colors.put("R", 2);
        colors.put("Y", 1);
        colors.put("B", 4);
        colors.put("G", 3);

    }

}
