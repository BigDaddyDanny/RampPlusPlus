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


public class ManualBeltControl extends CommandBase {

  public ManualBeltControl() {
  }

  @Override
  public void initialize() {

    // Belt.getInstance().run(OI.xbox.getRawAxis(3) - OI.xbox.getRawAxis(2));
    Belt.getInstance().run(.7);

  }

  
  @Override
  public void execute() {
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
