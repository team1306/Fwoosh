package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class DriveStop extends CommandBase {

	Timer timer;
	
	public DriveStop() {
		requires(drivetrain);
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		drivetrain.driveVBus(0,0);

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
