package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.lib.util.DriveSide;
import org.usfirst.frc.team1306.lib.util.Gyro;
import org.usfirst.frc.team1306.lib.util.Settings;
import org.usfirst.frc.team1306.lib.util.Settings.DriveMode;
import org.usfirst.frc.team1306.robot.Constants;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Drivetrain Subsystem. This subsystem is initialzed with a set of Settings provided from the CommandBase.
 * It uses that configuration to set-up sensors and the control mode for the driver, as well as putting each
 * side of the drivetrain into a seperate DriveSide object with the appropriate amount of Talons SRXs to control.
 * 
 * @author Jackson Goth
 */
public class Drivetrain extends Subsystem {

	public DriveSide leftMotors, rightMotors; //Sides of the drivetrain (each acts like a Talon SRX)
	public DriveMode mode; //Initial control mode for getting input from controllers
	public Gyro gyro; //Main gyro object other classes with reference
	
	public Drivetrain(Settings settings) {
		
		leftMotors = new DriveSide(settings.leftSide);
		rightMotors = new DriveSide(settings.rightSide);
		
		mode = settings.controlMode;
		
		if(settings.gyroPresent) {
			gyro = settings.gyro;
		}
		
		if(settings.encoderPresent) {
			leftMotors.initEncoders(settings.encoderType);
			leftMotors.flipEncoderOutput(false);
			leftMotors.flipLoopOutput(false);
			
			rightMotors.initEncoders(settings.encoderType);
			rightMotors.flipEncoderOutput(false); //TODO Flip this back to true
			rightMotors.flipLoopOutput(false); //TODO Same as above
		}
	}

	/**
	 * Powers each DriveSide with a left and right PercentVbus speed
	 * @param leftVal - Speed for left motors
	 * @param rightVal - Speed for right motors
	 */
	public void tankDrive(double leftVal, double rightVal) {
		leftMotors.changeControlMode(TalonControlMode.PercentVbus);
		rightMotors.changeControlMode(TalonControlMode.PercentVbus);

		if(Constants.DRIVETRAIN_ENABLED) {
			leftMotors.set(leftVal);
			rightMotors.set(-rightVal); 
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
