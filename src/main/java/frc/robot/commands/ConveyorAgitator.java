/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Belt;

public class ConveyorAgitator extends CommandBase {

  double stamp, runTime, delay;
  boolean mode; // True means running belts

  public ConveyorAgitator() {
  }

  @Override
  public void initialize() {
    runTime = 0.35;
    delay = 0.7;

    System.out.println("Start");
    Belt.getInstance().run(0.3);
    stamp = Timer.getFPGATimestamp();
    mode = true;
  }

  @Override
  public void execute() {
    if(mode && Timer.getFPGATimestamp() - stamp >= runTime){

      System.out.println("Stop");

      Belt.getInstance().run(0);
      stamp = Timer.getFPGATimestamp();
      mode = !mode;

    } else if(!mode && Timer.getFPGATimestamp() - stamp >= delay){
      
      System.out.println("Start");

      Belt.getInstance().run(0.3);
      stamp = Timer.getFPGATimestamp();

      mode = !mode;
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
