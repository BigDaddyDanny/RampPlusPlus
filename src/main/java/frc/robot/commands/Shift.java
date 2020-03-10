/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;

public class Shift extends CommandBase {

      @Override
      public void initialize() {

        Drivetrain.getInstance().shift();

      }

      @Override
      public void end(boolean interrupted) {

        Drivetrain.getInstance().shift();

      }

      @Override
      public boolean isFinished() {

          return !OI.shifter.get();

      }

}
