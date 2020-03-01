/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * Add your docs here.
 */
public class NavX {

    AHRS navx;
    double angularZero;

    private static NavX instance;
    
    public static NavX getInstance(){
        if(instance == null)
            instance = new NavX();
        return instance;
    }

    private NavX(){

        try {

            navx = new AHRS(SPI.Port.kMXP);

        } catch (RuntimeException ex) {

            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);

        }

    }

    public void zeroAngle(){

        angularZero = navx.getAngle();

    }

    public double getHeading(){

        return ((-(navx.getAngle()-angularZero)%360)*Math.PI)/180;

    }

}
