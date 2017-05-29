package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.drivetrain.DriveSide;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Settings;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	DriveSide leftMotors, rightMotors;
	
	public Drivetrain(Settings settings) {
		
		leftMotors = new DriveSide(settings.leftSide);
		rightMotors = new DriveSide(settings.rightSide);
		
	}
	
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
