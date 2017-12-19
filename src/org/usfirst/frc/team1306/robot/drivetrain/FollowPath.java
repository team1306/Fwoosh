package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.pathing.Profile;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain.Side;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @FollowPath
 * 
 * This command is meant to drive the robot along a given path/profile and applies a P-loop 
 * to how far the drivetrain is off from the path.
 * 
 * @author Jackson Goth
 */
public class FollowPath extends CommandBase {

	private Profile profile;
	private Timer timer;
	private int counter;
	private double initAngle;
	
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
		
		drivetrain.resetEncoders();
		
		counter = 0; //Resets counter that's used to determine position in profile
		initAngle = drivetrain.gyro.getAngle();
	}

	@Override
	protected void execute() {
		
		counter = (int) (timer.get() / 0.01);
		
		double speed = profile.path.get(counter).velocity;
		double leftError = profile.path.get(counter).position - (Math.abs(drivetrain.getEncoderPos(Side.LEFT)/1024)*12.5663);
		double rightError = profile.path.get(counter).position - (Math.abs(drivetrain.getEncoderPos(Side.RIGHT)/1024)*12.5663);
		double leftAdj = leftError * 2.25;
		double rightAdj = rightError * 2.25;
		
		double angleError = (drivetrain.gyro.getAngle() - initAngle) * 9;
		
		SmartDashboard.putNumber("angleError", angleError);
		
		SmartDashboard.putNumber("timer", timer.get());
		SmartDashboard.putNumber("speed",speed);
		SmartDashboard.putNumber("counter",counter);
		SmartDashboard.putNumber("leftError",leftError);
		SmartDashboard.putNumber("rightError",rightError);
		
		drivetrain.driveSpeed((((speed+leftAdj)/12.5663)*60)-angleError,(((speed+rightAdj)/12.5663)*60)+angleError);
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
