package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class DriveForwards extends CommandBase {

	Timer timer;
	double time, powerL, powerR = 0;
	
	public DriveForwards(double time, double powerL, double powerR) {
		requires(drivetrain);
		timer = new Timer();
		
		this.time = time;
		this.powerL = powerL;
		this.powerR = powerR;
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();

	}

	@Override
	protected void execute() {
		
		drivetrain.driveVBus(powerL,powerR);

	}
	
	@Override
	protected boolean isFinished() {

		return timer.hasPeriodPassed(time);
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
