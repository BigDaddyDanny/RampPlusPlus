/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Utilities;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Add your docs here.
 */
public class ColorSensor {

    private ColorSensorV3 sensor;
    private final double YELLOW_G = 1.734947849;
    private final double YELLOW_B = 0.3967310461;

    private final double BLUE_G = 3.3980969;
    private final double BLUE_B = 3.532986873;
    
    private final double GREEN_G = 3.350501674;
    private final double GREEN_B = 1.369153581;
    
    private final double RED_G = 0.7660518247;
    private final double RED_B = 0.3022244518;


    private static ColorSensor instance;

    public static ColorSensor getInstance(){
        if(instance == null) instance = new ColorSensor();

        return instance;
    }

    private ColorSensor(){
        sensor = new ColorSensorV3(I2C.Port.kOnboard);
    }

    public int getEstimatedColor(){

        double red = sensor.getRed();
        double green = sensor.getGreen();
        double blue = sensor.getBlue();

        double ratioG = green / red;
        double ratioB = blue / red;

        double yellowD = distanceForm(ratioG, YELLOW_G, ratioB, YELLOW_B);
        double blueD = distanceForm(ratioG, BLUE_G, ratioB, BLUE_B);
        double greenD = distanceForm(ratioG, GREEN_G, ratioB, GREEN_B);
        double redD = distanceForm(ratioG, RED_G, ratioB, RED_B);

        if(yellowD < blueD && yellowD < greenD && yellowD < redD){
            return 3;
        }else if(blueD < greenD && blueD < redD){
            return 2;
        }else if(greenD < redD){
            return 1;
        }else{
            return 4;
        }

    }

    public double distanceForm(double x1, double x2, double y1, double y2){
        double y = Math.pow(y1 - y2, 2);
        double x = Math.pow(x1 - x2, 2);

        return Math.sqrt(y + x);
    }

    public int getDistance(){
        return sensor.getProximity();
    }

    public Color getColor(){
        return sensor.getColor();
    }

}