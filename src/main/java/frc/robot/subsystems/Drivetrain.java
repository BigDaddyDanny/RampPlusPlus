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

    disable();
    
  }

  public void setLeftReverse(boolean reverse){
    leftMaster.setInverted(reverse);
  }
 
   public void shift(){

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

  public void driveForward(double speed){

    // leftMaster.set((speed + getAngleCorretion()[1]) * 1.15);
    // rightMaster.set(speed + getAngleCorretion()[0]);

    leftMaster.set(speed);
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

  @Override
  protected double getMeasurement() {
    return rightMaster.getEncoder().getPosition();
  }

  @Override
  protected void useOutput(double output, final double setpoint) {

    output = -output;

    if(output > 1)
      output = 1;
    else if (output < -1)
      output = -1;
    
    setSpeed(output, 0);

  }

//   public double[] getAngleCorretion(){

//     double[] correction = new double[2];

//     // double angleError = NavX.getInstance().getHeadingDeg();

//     // if(Math.abs(angleError)< 2){
//     //   correction[0] = 0;
//     //   correction[1] = 0;

//     //   return correction;
//     // }

//     // double offset = (angleError / (Math.sqrt(700 + Math.pow(angleError, 2))));

//     // offset = offset * Constants.ANGLE_CORRECTION_SCALING;    

//     // correction[0] = -offset;//right
//     // correction[1] = offset;//left

//     // SmartDashboard.putNumber("Left Correction", offset);
//     // SmartDashboard.putNumber("Right Correction", -offset);
// //************ */

//     double angleError = NavX.getInstance().getHeading();


//     if(angleError > Math.PI)
//       angleError = angleError - 2*Math.PI;

//     if(angleError > Constants.ANGLE_CORRECTION_TOLERANCE*(Math.PI/180)){

//       correction[0] = 0;
//       // correction[0] = (((2*(speed*0.1))/Math.PI)/4)*(Math.atan(Constants.ANGLE_CORRECTION_AGGRESSION*angleError - (Constants.ANGLE_CORRECTION_AGGRESSION*(Constants.ANGLE_CORRECTION_TOLERANCE*(Math.PI/180)))));
//       correction[1] = ((2*1)/Math.PI)*(Math.atan(Constants.ANGLE_CORRECTION_AGGRESSION*Math.abs(angleError) + (Constants.ANGLE_CORRECTION_AGGRESSION*(Constants.ANGLE_CORRECTION_TOLERANCE*(Math.PI/180)))));
    
//     } else if(angleError < -Constants.ANGLE_CORRECTION_TOLERANCE*(Math.PI/180)){

//       correction[0] = ((2*1)/Math.PI)*(Math.atan(Constants.ANGLE_CORRECTION_AGGRESSION*Math.abs(angleError) + (Constants.ANGLE_CORRECTION_AGGRESSION*(Constants.ANGLE_CORRECTION_TOLERANCE*(Math.PI/180)))));
//       // correction[1] = (((2*(speed*0.1))/Math.PI)/4)*(Math.atan(Constants.ANGLE_CORRECTION_AGGRESSION*angleError - (Constants.ANGLE_CORRECTION_AGGRESSION*(Constants.ANGLE_CORRECTION_TOLERANCE*(Math.PI/180)))));
//       correction[1] = 0;
//     } else{

//       correction[0] = 0;
//       correction[1] = 0;
//     }

//     return correction;

//   }
}