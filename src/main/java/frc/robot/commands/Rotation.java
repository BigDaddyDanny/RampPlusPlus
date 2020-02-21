/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import frc.robot.Utilities.ColorSensor;

public class Rotation extends CommandBase {
  /**
   * Creates a new Rotation.
   */

  private int counter;
  private int pastColor;

  public Rotation() {

    counter = 0;

    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    System.out.println("Rotation command running...");

    counter = 0;
    pastColor = 1;
    Arm.getInstance().spin(.4);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(ColorSensor.getInstance().getEstimatedColor() == 4 && pastColor != 4){
      
      counter++;
      System.out.println(counter);

    }

    pastColor = ColorSensor.getInstance().getEstimatedColor();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    Arm.getInstance().spin(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return counter >= Constants.RED_DETECTIONS;
  
  }

}