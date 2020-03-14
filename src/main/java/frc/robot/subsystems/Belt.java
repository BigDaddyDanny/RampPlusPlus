/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Belt extends SubsystemBase {
  
  private final CANSparkMax top = new CANSparkMax(RobotMap.top, MotorType.kBrushless);
  private final CANSparkMax bottom = new CANSparkMax(RobotMap.bottom, MotorType.kBrushless);
  private final DigitalInput limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);

  private static Belt instance;
  
  public static Belt getInstance(){

    if(instance == null){
      
      instance = new Belt();

    }

    return instance;
  
  }

  private Belt() {

    setBeltInverts(false, true);
    
    top.setIdleMode(IdleMode.kBrake);
    bottom.setIdleMode(IdleMode.kBrake);
  
  }

  public void setBeltInverts(boolean t, boolean b){

    top.setInverted(t); //true: runs along with other belt
    bottom.setInverted(b);//false: runs opposite with other belt

  }

  @Override
  public void periodic() { 
  }

  public void setRawSpeed(double topSpeed, double bottomSpeed){
    top.set(topSpeed);
    bottom.set(bottomSpeed);
  }

  public void run(double dir){

    if(dir < 0){
      top.set(-1);
      bottom.set(-1);
    } else if(dir > 0){
      top.set(1);
      bottom.set(1);
    } else{
      top.set(0);
      bottom.set(0);
    }

  }

  public void run(){
  }

  public boolean getLimitSwitch(){
    
    return limitSwitch.get();

  }

}
