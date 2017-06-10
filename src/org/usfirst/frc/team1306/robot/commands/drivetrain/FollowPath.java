package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		drivetrain.leftMotors.setEncPos(0);
		drivetrain.rightMotors.setEncPos(0);
		
		counter = 0;
	}

	@Override
	protected void execute() {
		double speed = profile.path.get(counter).velocity;
		double leftError = profile.path.get(counter).position - Math.abs(drivetrain.leftMotors.getEncPos()*0.03);
		double rightError = profile.path.get(counter).position - Math.abs(drivetrain.rightMotors.getEncPos()*0.03);
		double leftAdj = leftError * 0.9;
		double rightAdj = rightError * 0.8;
		
		SmartDashboard.putNumber("leftError",leftError);
		SmartDashboard.putNumber("rightError",rightError);
		SmartDashboard.putNumber("leftPos",drivetrain.leftMotors.getEncPos());
		SmartDashboard.putNumber("rightPos",drivetrain.rightMotors.getEncPos());
		
		drivetrain.driveSpeed(speed+leftAdj,speed+rightAdj);
		counter++;
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
