package frc.robot;

import java.util.Set;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.RunBelt;
import frc.robot.commands.RunIntake;
import frc.robot.commands.Shift;
import frc.robot.commands.SpinTo;
import frc.robot.commands.ToggleConveyor;


public class OI {

    private static final int ARM_UP_PORT = 4; //Xbox Y
    private static final int ARM_DOWN_PORT = 3; //Xbox X
    private static final int INTAKE_PORT = 1; //Xbox A
    private static final int SPIN_TO_PORT = 2; //Xbox B
    private static final int CONVEYOR_POSITION_PORT = 5; //XBox Left Bumper
    private static final int SHIFTER_PORT = 5; //Right Wheel Shifter *Unverified*
    
    private static final int ELEVATOR_UP_ANGLE = 0; //Xbox DPad Up
    private static final int ELEVATOR_DOWN_ANGLE = 180; //Xbox DPad Down

    private static Joystick stick;
    private static Joystick wheel;
    public static XboxController xbox;

    public static Trigger armUp, armDown, intake, spinTo, conveyorPosition, runBelt, shifter;

    public static Trigger ballDetected;
    
    
    public static POVButton elevUp, elevDown;



    public OI() {

        stick = new Joystick(1);
        wheel = new Joystick(2);

        xbox = new XboxController(0);


        elevUp = new POVButton(xbox, ELEVATOR_UP_ANGLE);
        elevDown = new POVButton(xbox, ELEVATOR_DOWN_ANGLE);

        elevUp.whenActive(new ElevatorUp());
        elevDown.whenActive(new ElevatorDown());
        
        // ballDetected = new Trigger(){
        //     @Override
        //     public boolean get() {

        //         return Belt.getInstance().getUltrasonic() < Constants.BALL_DETECTION && !runBelt.get();

        //     }
        // };

        // ballDetected.whenActive(new JogBelt());

        armUp = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(ARM_UP_PORT);

            }

        };

        armDown = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(ARM_DOWN_PORT);

            }

        };

        intake = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(INTAKE_PORT);

            }

        };

        spinTo = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(SPIN_TO_PORT);

            }

        };

        conveyorPosition = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(CONVEYOR_POSITION_PORT);

            }

        };

        runBelt = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawAxis(3) > Constants.BELT_DEAD_ZONE || xbox.getRawAxis(2) > Constants.BELT_DEAD_ZONE;

            }

        };
        
        shifter = new Trigger(){

            @Override
            public boolean get() {

                return wheel.getRawButton(SHIFTER_PORT);

            }

        };

        armUp.whenActive(new ArmUp());
        armDown.whenActive(new ArmDown());
        intake.whenActive(new RunIntake());
        spinTo.whenActive(new SpinTo());
        conveyorPosition.whenActive(new ToggleConveyor());
        runBelt.whenActive(new RunBelt());
        shifter.whenActive(new Shift());

        // elevatorUp = new POVButton(xbox, 0);
        // elevatorDown = new POVButton(xbox, 180);
        // elevatorUp.whenActive(new CommandBase() {
        // @Override
        // public void initialize() {

        // Elevator.getInstance().set(1);

        // }

        // @Override
        // public void end(boolean interrupted) {

        // Elevator.getInstance().set(0);

        // }

        // @Override
        // public boolean isFinished() {

        // return !elevatorUp.get();

        // }

        // });

        // elevatorDown.whenActive(new CommandBase() {
        // @Override
        // public void initialize() {

        // Elevator.getInstance().set(-1);

        // }

        // @Override

        // public void end(boolean interrupted) {

        // Elevator.getInstance().set(0);
        // }

        // @Override
        // public boolean isFinished() {

        // return !elevatorDown.get();

        // }

        // });

        // belt = new Trigger(){

        // @Override
        // public boolean get(){

        // return xbox.getRawAxis(3) >= BELT_DEAD_ZONE || xbox.getRawAxis(2) >=
        // BELT_DEAD_ZONE;

        // }

        // };
        // belt.whenActive(new CommandBase() {

        // @Override
        // public void execute() {

        // Belt.getInstance().run(Math.pow(xbox.getRawAxis(3),
        // 2)-Math.pow(xbox.getRawAxis(2), 2));

        // }

        // @Override
        // public void end(boolean interrupted) {

        // Belt.getInstance().run(0);

        // }

        // @Override
        // public boolean isFinished() {

        // return xbox.getRawAxis(3) < BELT_DEAD_ZONE && xbox.getRawAxis(2) <
        // BELT_DEAD_ZONE;

        // }

        // });

        // intake = new Trigger(){
        // @Override
        // public boolean get(){

        // return xbox.getRawButton(1);

        // }

        // };
        // intake.whenActive(new CommandBase() {

        // boolean started;

        // @Override
        // public void initialize() {

        // started = false;
        // Intake.getInstance().run(.6);

        // }

        // @Override
        // public void execute() {

        // if(Belt.getInstance().getUltrasonic() <= Constants.BALL_DETECTION &&
        // !started){

        // new IntakeBall().schedule();
        // started = true;

        // }

        // }

        // @Override
        // public void end(boolean interrupted) {

        // Intake.getInstance().run(0);

        // }

        // @Override
        // public boolean isFinished() {

        // return !intake.get();
        // }

        // });

        // lift = new Trigger(){

        // @Override
        // public boolean get(){

        // return xbox.getRawButton(5);

        // }

        // };
        // lift.whenActive(new CommandBase() {

        // @Override
        // public void initialize() {

        // Lifter.getInstance().cycle();

        // }

        // @Override
        // public boolean isFinished() {

        // return true;

        // }

        // });

        // spin = new Trigger(){

        // @Override
        // public boolean get(){

        // return xbox.getRawAxis(4) >= 0.1 || xbox.getRawAxis(4) <= -0.1;

        // }

        // };
        // spin.whenActive(new CommandBase() {

        // @Override
        // public void execute() {

        // Arm.getInstance().setArmSpeed(xbox.getRawAxis(4));

        // }

        // @Override
        // public void end(boolean interrupted) {

        // Arm.getInstance().setArmSpeed(0);

        // }

        // @Override
        // public boolean isFinished() {

        // return xbox.getRawAxis(4) < 0.1 && xbox.getRawAxis(4) > -0.1;

        // }

        // });

        // togglePID = new Trigger(){

        // @Override
        // public boolean get(){

        // return xbox.getRawButton(2);

        // }

        // };
        // togglePID.whenActive(new ToggleArm());

        // zeroElevator = new Trigger(){

        // @Override
        // public boolean get(){

        // return xbox.getRawButton(7);

        // }

        // };
        // zeroElevator.whenActive(new CommandBase() {

        // @Override
        // public void initialize() {

        // Arm.getInstance().zeroArmEnc();

        // }

        // @Override
        // public boolean isFinished() {

        // return true;

        // }

        // });

    }

    public static double getDriveHoz() {

        if (Math.abs(wheel.getRawAxis(0)) > Constants.WHEEL_DEADZONE) {

            return -wheel.getRawAxis(0);

        }

        return 0;

    }

    public static double getDriveFwd() {

        if (Math.abs(stick.getRawAxis(1)) > Constants.STICK_DEADZONE) {
            
            return stick.getRawAxis(1);
            
        }
        
        return 0;

    }

}