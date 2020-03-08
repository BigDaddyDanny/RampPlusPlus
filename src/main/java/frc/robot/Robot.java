 
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.Limelight;
import frc.robot.utilities.NavX;
import frc.robot.auto_commands.DriveTo;
import frc.robot.auto_commands.Test;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // private RobotContainer m_robotContainer;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    new OI();
    new Constants();
    Arm.getInstance().zeroArmEnc();
    Elevator.getInstance().setEncoderZero();
    SmartDashboard.putBoolean("Enable Drive", true);
    SmartDashboard.putNumber("Drive Setpoint", 0);
    SmartDashboard.putNumber("Pick Color", 1);

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    // m_robotContainer = new RobotContainer();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    Drivetrain.getInstance().disable();
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    Drivetrain.getInstance().setLeftReverse(false);

    NavX.getInstance().zeroAngle();
    Drivetrain.getInstance().zero();

    // Drivetrain.getInstance().enable();
    // Drivetrain.getInstance().setSetpoint(120*Constants.TICKS_PER_INCH);
    
    SmartDashboard.putData("Drive PID", Drivetrain.getInstance().getController());

    new Test().schedule();

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("NavX Heading", NavX.getInstance().getHeadingDeg());
  }

  @Override
  public void teleopInit() {

    Drivetrain.getInstance().setLeftReverse(false);

    NavX.getInstance().zeroAngle();

    CommandScheduler.getInstance().cancelAll();

    // SmartDashboard.putNumber("Setpoint", 200);
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    Drivetrain.getInstance().setSpeed(OI.getDriveFwd(), OI.getDriveHoz());

    SmartDashboard.putBoolean("Limit Switch", Belt.getInstance().getLimitSwitch());
    SmartDashboard.putNumber("Drive R Enc", Drivetrain.getInstance().getRightPosition());
    SmartDashboard.putNumber("Drive L Enc", Drivetrain.getInstance().getLeftPosition());

    SmartDashboard.putNumber("Left Correction", Drivetrain.getInstance().getAngleCorretion()[1]);
    SmartDashboard.putNumber("Right Correction", Drivetrain.getInstance().getAngleCorretion()[0]);

  }

  @Override
  public void testInit() {
    
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

}

