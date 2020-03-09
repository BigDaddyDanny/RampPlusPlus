/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utilities.Limelight;

public class LimelightDrive extends CommandBase {

  double ffTimer, previousPos;
  boolean forceFinish;

  public LimelightDrive() {
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    forceFinish = false;
    ffTimer = Timer.getFPGATimestamp();

    previousPos = Drivetrain.getInstance().getRightPosition();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double error = Limelight.getInstance().getX();
    double y = Limelight.getInstance().getY();

    double speed;

    if(Math.abs(error) < 0.65)
      error = 0;

    double rotSpeed = (0.3 * error)/ Math.sqrt(1 + (0.8 * Math.pow(error, 2)));
    if(y > 3){
      speed = 0.4;
    } else {
      speed = 0.7;
    }
    if(y == 0){
      speed = 0;
    }

    System.out.println(speed);

    Drivetrain.getInstance().setSpeed(speed, -rotSpeed);
    
    if(Math.abs(error) > 15){
      error = 0;
      speed = 0;
    }

    if(Drivetrain.getInstance().getRightPosition() != previousPos){
      ffTimer = Timer.getFPGATimestamp();
    }

    forceFinish = Math.floor(Timer.getFPGATimestamp() * 100.0) / 100.0 - Math.floor(ffTimer * 100.0) / 100.0 >= 0.2;
    
    previousPos = Drivetrain.getInstance().getRightPosition();


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return forceFinish;
  }
}
