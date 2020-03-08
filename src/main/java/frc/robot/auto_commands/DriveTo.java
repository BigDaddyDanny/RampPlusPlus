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

public class DriveTo extends CommandBase {

  double position;

  public DriveTo(double inches) {

    position = (inches*Constants.TICKS_PER_INCH);

  }

  @Override
  public void initialize() {

    Drivetrain.getInstance().zero();
    Drivetrain.getInstance().setSetpoint(position);
    Drivetrain.getInstance().enable();
    

  }

  @Override
  public void execute() {

    
  }

  @Override
  public void end(boolean interrupted) {

    Drivetrain.getInstance().disable();

    Drivetrain.getInstance().setSpeed(0, 0);
    
  }

  @Override
  public boolean isFinished() {
    return Math.abs(Drivetrain.getInstance().getRightPosition()) >= Math.abs(position);
  }
}
