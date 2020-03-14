/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {

  boolean runningBelt;

  public RunIntake() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    Intake.getInstance().run(Constants.INTAKE_SPEED);

    runningBelt = false;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(Belt.getInstance().getLimitSwitch() && !runningBelt){
      
      new BeltScheduler().schedule();
      runningBelt = true;

    }
    else if(!Belt.getInstance().getLimitSwitch()) {
      runningBelt = false;
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    Intake.getInstance().run(Constants.INTAKE_REVERSE_SPEED);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return !OI.intake.get();
  
  }

}
