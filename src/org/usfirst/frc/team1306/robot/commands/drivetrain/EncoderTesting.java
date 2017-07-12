package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderTesting extends CommandBase {

	public EncoderTesting() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		double currentPos = drivetrain.leftMotors.getEncPos();
		
		SmartDashboard.putNumber("Encoder-Units:",currentPos);
		SmartDashboard.putNumber("Encoder-Rotations:",currentPos/256);
		SmartDashboard.putNumber("Encdoder-Disance:",(currentPos/256)*12.5663);
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
