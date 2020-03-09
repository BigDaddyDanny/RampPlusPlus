/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utilities.NavX;

public class Spin extends CommandBase {

  double radians;
  double turnSpeed;

  /**
   * Creates a new Spin.
   */
  public Spin(double degrees) {// positive = left, negative = right
    // Use addRequirements() here to declare subsystem dependencies.
    radians = (degrees * Math.PI) / 180;

    System.out.println("    Spin: " + degrees);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    if(radians > 0){
      turnSpeed = Constants.AUTO_SPIN_SPEED;
    }else{
      turnSpeed = -Constants.AUTO_SPIN_SPEED;
    }

    NavX.getInstance().zeroAngle();
    Drivetrain.getInstance().setSpeed(0, turnSpeed);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double error = Math.abs(radians) - Math.abs(NavX.getInstance().getHeading());

    double speed = (error / (Math.sqrt(1 + Math.pow(error, 2))));

    if(speed > .6){
      speed = .6;
    }

    if(turnSpeed < 0){
      speed = -speed;
    }

    Drivetrain.getInstance().setSpeed(0, speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Drivetrain.getInstance().setSpeed(0, 0);

    System.out.println("  Spin Finished");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(NavX.getInstance().getHeading()) >= Math.abs(radians) - 0.15;
  }
}
