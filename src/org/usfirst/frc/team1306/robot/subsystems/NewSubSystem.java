package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class NewSubSystem extends Subsystem {

	CANTalon shooterMotor;
	
	public NewSubSystem() {
		shooterMotor = new CANTalon(RobotMap.SHOOTER_PORT);
	}
	
	public void runmotor(double Pct){
		shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
		shooterMotor.set(Pct);
	}
	
	
	public void stop() {
		shooterMotor.set(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	/*
	 when run:
	 runmotor(.50);
	 runs shooterMotor at 50%
	 */
}
