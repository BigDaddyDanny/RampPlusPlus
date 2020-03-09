/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ToggleCommand;
import frc.robot.commands.ToggleConveyor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TrenchAuto extends SequentialCommandGroup {
  /**
   * Creates a new TrenchAuto.
   */
  

  public TrenchAuto() {
    super(
      new ParallelCommandGroup(
        new DriveTo(132),
        new TimedIntake(3.5)),// wait command .3
      new Spin(30),
      new DriveTo(-130),
      new Spin(-35),
      new LimelightDrive(),
      new ToggleConveyor(),
      new WaitCommand(0.2),
      new Outtake());
  }
}
