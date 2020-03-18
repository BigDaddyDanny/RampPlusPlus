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
import frc.robot.subsystems.Elevator;

public class ElevatorUp extends CommandBase {



  public ElevatorUp() {

  }

  @Override
  public void initialize() {

    Elevator.getInstance().set(1);

  }

  @Override
  public void execute() {
    
    if(Elevator.getInstance().getEncPos() < -24000){
      Elevator.getInstance().set(.5);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    Elevator.getInstance().set(0);
  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    return Elevator.getInstance().getEncPos() <= (Constants.ELEVATOR_UP)
      || !OI.elevUp.get();
  
  }

}
