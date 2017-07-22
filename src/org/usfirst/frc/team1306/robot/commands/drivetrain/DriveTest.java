package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class DriveTest extends CommandBase {

	@Override
	protected void initialize() {
		new Drive().start();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
