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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Belt extends SubsystemBase {
  
  private final CANSparkMax top = new CANSparkMax(RobotMap.top, MotorType.kBrushless);
  private final CANSparkMax bottom = new CANSparkMax(RobotMap.bottom, MotorType.kBrushless);
  private final AnalogInput m_ultrasonic = new AnalogInput(RobotMap.ULTRASONIC);

  private static Belt instance;
  public static Belt getInstance(){
    if(instance == null)
      instance = new Belt();
    return instance;
  }
  private Belt() {
    top.setInverted(true);
    top.setIdleMode(IdleMode.kBrake);
    bottom.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
  }

  public void run(double dir){
    top.set(dir);
    if(dir*2 > 1)
      bottom.set(1);
    else
      bottom.set(dir*2);
  }

  public double getUltrasonic(){
    
    return m_ultrasonic.getValue();

  }
}