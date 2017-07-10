package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	CANTalon shooterMotor;
	
	public Shooter() {
		shooterMotor = new CANTalon(RobotMap.SHOOTER_PORT);
	}
	
	public void spinMotor() {
		shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		shooterMotor.set(1);
	}
	
	public void stop() {
		shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		shooterMotor.set(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
