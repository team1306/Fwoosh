package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;

public class FollowPath extends CommandBase {

	Profile profile;
	Timer timer;
	
	int counter;
	
	public FollowPath(Profile p) {
		requires(drivetrain);
		profile = p;
		
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		
		drivetrain.leftMotors.changeControlMode(TalonControlMode.Speed);
		drivetrain.rightMotors.changeControlMode(TalonControlMode.Speed);
		
		counter = 0;
	}

	@Override
	protected void execute() {
		double speed = profile.path.get(counter).velocity;
		drivetrain.driveSpeed(speed,speed);
	}

	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(profile.maxTime);
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
