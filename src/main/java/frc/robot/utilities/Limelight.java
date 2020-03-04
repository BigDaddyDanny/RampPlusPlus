  
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private NetworkTable table;
    private NetworkTableEntry tx;
    private boolean isTracking;

    private static Limelight instance;
    public static Limelight getInstance(){
        if(instance == null) instance = new Limelight();

        return instance;
    }

    private Limelight(){
        
        table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
        isTracking = false;
        tx = table.getEntry("tx");
        
    }

    public void switchState(){

        if(isTracking){
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
        }else{
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
        }

        isTracking = !isTracking;
    }

    

    public double getX(){
        return tx.getDouble(0.0);
    }

    

}