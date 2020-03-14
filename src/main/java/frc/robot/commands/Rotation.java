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
import frc.robot.utilities.ColorSensor;

public class Rotation extends CommandBase {
  /**
   * Creates a new Rotation.
   */

  private int counter;
  private int pastColor;

  private static Rotation instance;

  public static Rotation getInstance(){
    if(instance == null)
      instance = new Rotation();
    return instance;
  }

  private Rotation() {

    counter = 0;

    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    counter = 0;
    pastColor = 1;
    Arm.getInstance().spin(.3);
    Arm.getInstance().setRawSpeed(Constants.SPIN_DOWN_FORCE);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(ColorSensor.getInstance().getEstimatedColor() == 4 && pastColor != 4){
      
      counter++;

    }

    if(counter >= 2 && counter <= Constants.RED_DETECTIONS-2){

      Arm.getInstance().spin(.5);

    } else {

      Arm.getInstance().spin(.3);

    }

    pastColor = ColorSensor.getInstance().getEstimatedColor();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    Arm.getInstance().spin(0);

    Arm.getInstance().setRawSpeed(0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return counter >= Constants.RED_DETECTIONS;
  
  }

}