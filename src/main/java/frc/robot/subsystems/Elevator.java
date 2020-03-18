/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Elevator extends SubsystemBase {

  // private final CANSparkMax elevator = new CANSparkMax(RobotMap.ELEVATOR, MotorType.kBrushless);
  private final TalonSRX elevator = new TalonSRX(RobotMap.ELEVATOR1);
  private final VictorSPX elevator2 = new VictorSPX(RobotMap.ELEVATOR2);
  private static Elevator instance;
  
  public static Elevator getInstance(){
    if(instance == null)
      instance = new Elevator();
    return instance;
  }

  private Elevator() {

    elevator.setNeutralMode(NeutralMode.Brake);
    elevator2.follow(elevator);
    elevator2.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {
  }

  public void setEncoderZero(){

    elevator.setSelectedSensorPosition(0);
    
  }

  public double getEncPos(){

    return elevator.getSelectedSensorPosition();

  }

  public void set(double speed){
    elevator.set(ControlMode.PercentOutput, speed);
  }

  public double getVoltage(){
    return elevator.getBusVoltage();
  }
}
