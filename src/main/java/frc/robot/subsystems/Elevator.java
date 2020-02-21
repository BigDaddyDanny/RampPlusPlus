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

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Elevator extends SubsystemBase {

  private final CANSparkMax elevator = new CANSparkMax(RobotMap.ELEVATOR, MotorType.kBrushless);
  private double encoderZero;
  private static Elevator instance;
  
  public static Elevator getInstance(){
    if(instance == null)
      instance = new Elevator();
    return instance;
  }

  private Elevator() {

    elevator.setIdleMode(IdleMode.kBrake);

  }

  @Override
  public void periodic() {
  }

  public double getRealEncPos(){

    return elevator.getEncoder().getPosition();

  }

  public void setEncoderZero(){

    System.out.println("Elevator Zero ed");

    encoderZero = getRealEncPos();
  }

  public double getEncPos(){
    return getRealEncPos()-encoderZero;
  }

  public void set(double speed){
    elevator.set(speed);
  }

  public double getVoltage(){
    return elevator.getBusVoltage();
  }
}
