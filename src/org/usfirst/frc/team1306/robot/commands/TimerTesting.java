package org.usfirst.frc.team1306.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimerTesting extends CommandBase {
	
	private Timer timer;
	
	@Override
	protected void initialize() {
		timer = new Timer();
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("Counter",timer.get() / 0.01);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
