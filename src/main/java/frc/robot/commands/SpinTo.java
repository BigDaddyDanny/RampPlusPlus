/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import frc.robot.utilities.ColorSensor;

public class SpinTo extends CommandBase {

  boolean stop = false;
  int currentColor, targetColor, dist;
  ArrayList<Integer> colorLog;

  private static SpinTo instance;
  public static SpinTo getInstance(){
    if(instance == null)
      instance = new SpinTo();
    return instance;
  }

  private SpinTo() {
    colorLog = new ArrayList<Integer>();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    colorLog.clear();

    Arm.getInstance().setRawSpeed(Constants.SPIN_DOWN_FORCE);

    for(int i = 0; i < Constants.bufferSize; i++){

      colorLog.add(0);

    }

    String gameData = DriverStation.getInstance().getGameSpecificMessage().length() > 0 ? DriverStation.getInstance().getGameSpecificMessage() : "R";
    targetColor = Constants.colors.get(gameData);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    currentColor = ColorSensor.getInstance().getEstimatedColor();

    dist = ((targetColor-currentColor+2)%4)-2;


    colorLog.remove(0);
    colorLog.add(currentColor);

    Arm.getInstance().spin(((double) dist) * Constants.SPINFACTOR);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    Arm.getInstance().spin(0);
    Arm.getInstance().setRawSpeed(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    boolean condition = currentColor == targetColor && bufferAchieved();

    return condition;

  }

  public boolean bufferAchieved() {

    for(int color: colorLog){

      if(colorLog.get(0) != color){

        return false;

      }

    }

    return true;

  }

}
