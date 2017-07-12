package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This command is meant to drive the robot along a given path/profile and applies a P-loop to how far the drivetrain is off from the path.
 * @author Jackson Goth
 */
public class FollowPath extends CommandBase {

	private Profile profile;
	private Timer timer;
	private int counter;
	
	public FollowPath(Profile p) {
		requires(drivetrain);
		profile = p;
		
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		
		/* Resets the timer that will determine when the command will end */
		timer.reset();
		timer.start();
		
		drivetrain.leftMotors.changeControlMode(TalonControlMode.Speed);
		drivetrain.rightMotors.changeControlMode(TalonControlMode.Speed);
		drivetrain.leftMotors.setEncPos(0);
		drivetrain.rightMotors.setEncPos(0);
		
		counter = 0; //Resets counter that's also used to determine po
	}

	@Override
	protected void execute() {
		double speed = profile.path.get(counter).velocity;
		double leftError = profile.path.get(counter).position - (Math.abs(drivetrain.leftMotors.getEncPos()/256)*12.5663);
		double rightError = profile.path.get(counter).position - (Math.abs(drivetrain.rightMotors.getEncPos()/256)*12.5663);
		double leftAdj = leftError * 1.2;
		double rightAdj = rightError * 1.2;
		
		SmartDashboard.putNumber("leftError",leftError);
		SmartDashboard.putNumber("rightError",rightError);
		SmartDashboard.putNumber("leftPos",drivetrain.leftMotors.getEncPos());
		SmartDashboard.putNumber("rightPos",drivetrain.rightMotors.getEncPos());
		
//		drivetrain.driveSpeed(300,300);
//		drivetrain.driveSpeed(((speed+leftAdj)/12.5663)*60,((speed+rightAdj)/12.5663)*60);
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
