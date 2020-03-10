/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Test extends SequentialCommandGroup {
  /**
   * Creates a new Test.
   */
  public Test() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

    //  super(new DriveTo(120), new Spin(-90), new DriveTo(36), new Spin(-90), new Spin(-90), new DriveTo(36), 
    //  new Spin(-90), new DriveTo(-116), new ToggleConveyor(), new WaitCommand(1), new Outtake());

    // super(new DriveTo(120), new DriveTo(-120), new WaitCommand(0.5), new ToggleConveyor(), new Outtake());

    //super(new DriveTo(126), new WaitCommand(0.35), new DriveTo(-126), new Spin(45), new DriveTo(-96), new Spin(-45), new DriveTo(-56), new ToggleConveyor(), new Outtake());

    super(new DriveTo(130));

    //super(new Spin(90));
  }


}
