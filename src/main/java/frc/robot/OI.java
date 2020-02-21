package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.RunBelt;
import frc.robot.commands.RunIntake;
import frc.robot.commands.SpinTo;
import frc.robot.commands.ToggleConveyor;
import frc.robot.commands.ToggleElevator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;


public class OI {

    private static final int ARM_UP_PORT = 4; //XBox
    private static final int ARM_DOWN_PORT = 3; //XBox
    private static final int INTAKE_PORT = 1; //XBox
    private static final int SPIN_TO_PORT = 2; //XBox
    private static final int CONVEYOR_POSITION_PORT = 5; //XBox
    private static final int SHIFTER_PORT = 5; //wheel
    
    private static final int ELEVATOR_UP_ANGLE = 0; //XBox
    private static final int ELEVATOR_DOWN_ANGLE = 180; //XBox

    private static Joystick stick;
    private static Joystick wheel;
    public static XboxController xbox;

    // private Trigger shifter, belt, intake, lift, spin, togglePID, zeroElevator,
    // lowerArm, colorExpedition;
    // private POVButton elevatorUp, elevatorDown;

    public static Trigger armUp, armDown, intake, spinTo, conveyorPosition, elevatorToggle, runBelt, shifter;
    
    
    public static POVButton elevUp, elevDown;



    public OI() {

        stick = new Joystick(1);
        wheel = new Joystick(2);

        xbox = new XboxController(0);


        elevUp = new POVButton(xbox, ELEVATOR_UP_ANGLE);
        elevDown = new POVButton(xbox, ELEVATOR_DOWN_ANGLE);

        elevUp.whenActive(new CommandBase() {
            @Override
            public void initialize() {
                System.out.println("UP");
                Elevator.getInstance().set(0.6);
            }
            @Override
            public void end(boolean interrupted) {
                Elevator.getInstance().set(0);
            }
            @Override
            public boolean isFinished() {
                return !elevUp.get();
            }
        });
        elevDown.whenActive(new CommandBase() {
            @Override
            public void initialize() {
                System.out.println("Down");
                Elevator.getInstance().set(-0.6);
            }
            @Override
            public void end(boolean interrupted) {
                Elevator.getInstance().set(0);
            }
            @Override
            public boolean isFinished() {
                return !elevDown.get();
            }
        });


        armUp = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(4);

            }

        };

        armDown = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(3);

            }

        };

        intake = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(1);

            }

        };

        spinTo = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(2);

            }

        };

        conveyorPosition = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(5);

            }

        };

        elevatorToggle = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(6);

            }

        };

        runBelt = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawAxis(3) > Constants.BELT_DEAD_ZONE || xbox.getRawAxis(2) > Constants.BELT_DEAD_ZONE;

            }

        };

        armUp.whenActive(new ArmUp());
        armDown.whenActive(new ArmDown());
        intake.whenActive(new RunIntake());
        spinTo.whenActive(new SpinTo());
        conveyorPosition.whenActive(new ToggleConveyor());
        elevatorToggle.whenActive(new ToggleElevator());
        runBelt.whenActive(new RunBelt());

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

        shifter = new Trigger(){

            @Override
            public boolean get() {

                return wheel.getRawButton(5);

            }

        };

        shifter.whenActive(new CommandBase() {

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

        return !shifter.get();

        }

        });

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