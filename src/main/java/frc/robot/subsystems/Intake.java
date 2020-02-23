/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  
  private final VictorSPX intake = new VictorSPX(RobotMap.intake);

  private static Intake instance;

  public static Intake getInstance(){
    if(instance == null)
      instance = new Intake();
    return instance;
  }

  private Intake() {

    intake.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run(double dir){

    intake.set(ControlMode.PercentOutput, dir);
  
  }

  public double getBusVoltage(){

    return intake.getBusVoltage();

  }

  public double getMotorOutputVoltage(){

    return intake.getMotorOutputVoltage();

  }

}
