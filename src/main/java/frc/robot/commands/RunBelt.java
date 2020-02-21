/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Belt;

public class RunBelt extends CommandBase {

  public RunBelt() {
  }

  @Override
  public void initialize() {

    System.out.println("RunBelt command running...");

  }

  @Override
  public void execute() {

    Belt.getInstance().run(OI.xbox.getRawAxis(3) - OI.xbox.getRawAxis(2));

  }

  @Override
  public void end(boolean interrupted) {

    Belt.getInstance().run(0);

  }

  @Override
  public boolean isFinished() {

    return !OI.runBelt.get();

  }
  
}
