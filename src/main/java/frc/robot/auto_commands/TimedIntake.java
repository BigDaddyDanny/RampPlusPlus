/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Intake;

public class TimedIntake extends CommandBase {

  double startTime;
  double seconds;

  /**
   * Creates a new TimedIntake.
   */
  public TimedIntake(double sec) {
    // Use addRequirements() here to declare subsystem dependencies.

    seconds = sec;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    startTime = Timer.getFPGATimestamp();
    Intake.getInstance().run(Constants.INTAKE_SPEED);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(Belt.getInstance().getLimitSwitch()){
      Belt.getInstance().run(Constants.BELT_SPEED);
    }else{
      Belt.getInstance().run(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Belt.getInstance().run(0);
    Intake.getInstance().run(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTime >= seconds;
  }
}
