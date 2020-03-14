package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.auto_commands.TrenchAuto;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.Rotation;
import frc.robot.commands.ManualBeltControl;
import frc.robot.commands.RunIntake;
import frc.robot.commands.Shift;
import frc.robot.commands.SpinTo;
import frc.robot.commands.ToggleCommand;
import frc.robot.commands.ToggleConveyor;
import frc.robot.utilities.Limelight;


public class OI {

    private static final int ARM_UP_PORT = 4; //Xbox Y
    private static final int ARM_DOWN_PORT = 3; //Xbox X
    private static final int INTAKE_PORT = 1; //Xbox A
    private static final int SPIN_TO_PORT = 2; //Xbox B
    private static final int SPIN_THREE_PORT = 6; //Xbox Right Bumper
    private static final int CONVEYOR_POSITION_PORT = 5; //XBox Left Bumper
    private static final int SHIFTER_PORT = 5; //Right Wheel Shifter *Unverified*
    
    private static final int ELEVATOR_UP_ANGLE = 0; //Xbox DPad Up
    private static final int ELEVATOR_DOWN_ANGLE = 180; //Xbox DPad Down
    private static final int LIMELIGHT_SHIFT_PORT = 1; //Stick Trigger

    private static Joystick stick;
    private static Joystick wheel;
    public static XboxController xbox;

    public static Trigger armUp, armDown, intake, spinTo, spinThree, conveyorPosition, runBelt, shifter, limelightState;

    public static Trigger spinCommand;
    
    public static POVButton elevUp, elevDown;



    public OI() {

        stick = new Joystick(1);
        wheel = new Joystick(2);

        xbox = new XboxController(0);


        elevUp = new POVButton(xbox, ELEVATOR_UP_ANGLE);
        elevDown = new POVButton(xbox, ELEVATOR_DOWN_ANGLE);

        elevUp.whenActive(new ElevatorUp());
        elevDown.whenActive(new ElevatorDown());

        spinCommand = new POVButton(xbox, 90);
        spinCommand.whenActive(new TrenchAuto());

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

        spinThree = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(SPIN_THREE_PORT);

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

        limelightState = new Trigger(){

            @Override
            public boolean get() {
                
                return stick.getRawButton(LIMELIGHT_SHIFT_PORT);

            }

        };

        armUp.whenActive(new ArmUp());
        armDown.whenActive(new ArmDown());
        intake.whenActive(new RunIntake());
        spinTo.whenActive(new ToggleCommand(SpinTo.getInstance()));
        spinThree.whenActive(new ToggleCommand(Rotation.getInstance()));
        conveyorPosition.whenActive(new ToggleConveyor());
        runBelt.whenActive(new ManualBeltControl());
        shifter.whenActive(new Shift());
        limelightState.whenActive(new CommandBase() {

            @Override
            public void initialize() {

                Limelight.getInstance().switchState();

            }

            @Override
            public boolean isFinished() {
                
                return true;

            }
            
        });

    }

    public static double getDriveHoz() {

        if (Math.abs(wheel.getRawAxis(0)) > Constants.WHEEL_DEADZONE) {

            if(wheel.getRawAxis(0) > 0){
                return -Math.pow(wheel.getRawAxis(0), 2);
            }else{
                return Math.pow(wheel.getRawAxis(0), 2);
            }

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