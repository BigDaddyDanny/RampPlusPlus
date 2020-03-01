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

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drivetrain extends PIDSubsystem {

  private final CANSparkMax leftMaster = new CANSparkMax(RobotMap.leftMaster, MotorType.kBrushless);
  private final CANSparkMax leftSlaveA = new CANSparkMax(RobotMap.leftSlaveA, MotorType.kBrushless);
  private final CANSparkMax leftSlaveB = new CANSparkMax(RobotMap.leftSlaveB, MotorType.kBrushless);

  private final CANSparkMax rightMaster = new CANSparkMax(RobotMap.rightMaster, MotorType.kBrushless);
  private final CANSparkMax rightSlaveA = new CANSparkMax(RobotMap.rightSlaveA, MotorType.kBrushless);
  private final CANSparkMax rightSlaveB = new CANSparkMax(RobotMap.rightSlaveB, MotorType.kBrushless);


  private final DoubleSolenoid shift = new DoubleSolenoid(RobotMap.shiftHigh, RobotMap.shiftLow);
  
  private boolean isHigh;
  private final DifferentialDrive drive;

  private static Drivetrain instance;
  public static Drivetrain getInstance(){
    if(instance == null)
      instance = new Drivetrain();
    return instance;
  }

  private Drivetrain() {
    super(new PIDController(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D));

    zero();
    setSetpoint(0);
    leftSlaveA.follow(leftMaster);
    leftSlaveB.follow(leftMaster);

    rightSlaveA.follow(rightMaster);
    rightSlaveB.follow(rightMaster);
    
    leftMaster.setIdleMode(IdleMode.kBrake);
    rightMaster.setIdleMode(IdleMode.kBrake);

    isHigh = true;
    shift.set(Value.kReverse);

    drive = new DifferentialDrive(leftMaster, rightMaster);
    
  }
  
  public double getPos(){

    return rightMaster.getEncoder().getPosition();
    
   }
 
   public void shift(){
      System.out.println("Actual Shift Meth");
     if(isHigh)
       shift.set(Value.kForward);
     else
       shift.set(Value.kReverse);
 
     isHigh = !isHigh;
   }
 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    super.periodic();
  }

  public void setSpeed(final double xSpeed, final double zRotation){
    drive.arcadeDrive(xSpeed, zRotation);
  }

  public void testSetSpeedDELETE_LATER(double speed){

    leftMaster.set(-speed * 1.0586943897);
    rightMaster.set(speed);
    
  }

  public double getLeftPosition(){
    return leftMaster.getEncoder().getPosition();
  }

  public double getRightPosition(){
    return rightMaster.getEncoder().getPosition();
  }

  public void zero(){
    leftMaster.getEncoder().setPosition(0);
    rightMaster.getEncoder().setPosition(0);
  }

  public void zero(final boolean side){//right == true
    if(side)
      rightMaster.getEncoder().setPosition(0);
    else 
      leftMaster.getEncoder().setPosition(0);

  }

  public void reset(){
    disable();
    enable();
  }

  public void setPID(final boolean enable){
    if(enable){
      enable();
    }else{
      disable();
    }
  }

  @Override
  protected double getMeasurement() {
    return rightMaster.getEncoder().getPosition();
  }

  @Override
  protected void useOutput(double output, final double setpoint) {

    // leftMaster.set(output);
    // rightMaster.set(output);
    output = -output;

    if(output > 1){
      output = 1;
    }

    drive.arcadeDrive(output, 0);
    SmartDashboard.putNumber("Drive Output", output);

  }
}
