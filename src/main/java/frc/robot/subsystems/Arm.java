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
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Arm extends PIDSubsystem {
  
  private final TalonSRX arm = new TalonSRX(RobotMap.arm);
  private final CANSparkMax spinner = new CANSparkMax(RobotMap.spinner, MotorType.kBrushless);

  private static Arm instance;

  public static Arm getInstance(){
    if(instance == null)
      instance = new Arm();
    return instance;
  }

  private Arm() {
    super(new PIDController(Constants.ARM_P, Constants.ARM_I, Constants.ARM_D));
    arm.setSelectedSensorPosition(0);
    spinner.setIdleMode(IdleMode.kBrake);
    arm.setNeutralMode(NeutralMode.Brake);
  }

  public void reset(){
    zeroArmEnc();
    this.disable();
  }

  @Override
  public void periodic() {
    super.periodic();
  }

  public void spin(double speed){

    spinner.set(speed);

  }

  public void setArmSpeed(double speed){

    if(Math.abs(speed) >= Constants.MAX_ARM_SPEED){

      speed = Constants.MAX_ARM_SPEED * (speed / Math.abs(speed));

    }

    arm.set(ControlMode.PercentOutput, speed);

  }

  public void setRawSpeed(double speed){

    arm.set(ControlMode.PercentOutput, speed);
    
  }

  public void toggleEnabled(){
    if(isEnabled())
      disable();
    else{
      enable();
    }
  }

  public void zeroArmEnc(){
    
    arm.setSelectedSensorPosition(0);

  }

  public double getArmPos(){

    return arm.getSelectedSensorPosition();

  }

  @Override
  protected void useOutput(double output, double setpoint) {
    
    setArmSpeed(output);

  }

  @Override
  protected double getMeasurement() {
    return arm.getSelectedSensorPosition();
  }

}