package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;

public class OI {
    
    private final Joystick stick = new Joystick(1);
    private final Joystick wheel = new Joystick(2);

    private Button shifter;
    

    private static OI instance;
    public static OI getInstance(){
        if(instance == null) instance = new OI();

        return instance;
    }
    private OI() {
        shifter = new Button(){
            
            @Override
            public boolean get() {
                return wheel.getRawButton(5);
            }
        };
        
        shifter.whenPressed(new Command() {
            @Override
			protected void initialize() {
                Drivetrain.getInstance().shift();
            }
			@Override
			protected void execute() {
            }
			
			@Override
			protected void end() {
                Drivetrain.getInstance().shift();
            }
			
			@Override
			protected boolean isFinished() {
                return !shifter.get();
            }
		});
    }

    public double getDriveHoz(){
        return -wheel.getRawAxis(0);

    }
    
    public double getDriveFwd(){
        return stick.getRawAxis(1);
    }
}
