package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.pathing.FalconPathPlanner;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Follow2DPath
 * 
 * This command drives the robot along a FalconPathPlanner 2D path, using feedback from the gyro and will end
 * after a certain amount of time.
 * 
 * @author Jackson Goth
 */
public class Follow2DPath extends CommandBase {

	private FalconPathPlanner path;
	private Timer timer;
	private int counter;
	private double initAngle;
	private boolean reverse;
	
	public Follow2DPath(FalconPathPlanner p, boolean r) {
		requires(drivetrain);
		path = p;
		reverse = r;
		
		timer = new Timer();
		
		initAngle = drivetrain.gyro.getAngle();
	}
	
	@Override
	protected void initialize() {
		
		/* Resets the timer that will determine when the command will end */
		timer.reset();
		timer.start();
		
		drivetrain.resetEncoders();
		
		counter = 0; //Resets counter that's used to determine position in profile
	}

	@Override
	protected void execute() {
		
		counter = (int) (timer.get() / 0.1);
		
		double leftSpeed;
		double rightSpeed;
		
		try {
			leftSpeed = path.smoothLeftVelocity[counter][1];
			rightSpeed = path.smoothRightVelocity[counter][1];
		} catch(Exception e) {
			leftSpeed = 0;
			rightSpeed = 0;
		}
		
		
		//SmartDashboard.putNumber("LeftSpeed",((leftSpeed*12)/12.5663)*60);
		//SmartDashboard.putNumber("RightSpeed",((rightSpeed*12)/12.5663)*60);
		
		SmartDashboard.putNumber("initAngle",initAngle);
		SmartDashboard.putNumber("ProgressAngle",initAngle + drivetrain.gyro.getAngle());
		
		double gyroCorrection;
		
		try {
			if(reverse) {
				gyroCorrection = (path.heading[counter][1] + (initAngle - drivetrain.gyro.getAngle())) * 2;
			} else {
				gyroCorrection = -path.heading[counter][1] + (initAngle - drivetrain.gyro.getAngle());
			}
		} catch(Exception e) {
			gyroCorrection = 0;
		}
		
		SmartDashboard.putNumber("gyroError",gyroCorrection);

		if(reverse) {
			drivetrain.driveSpeed(-((((leftSpeed*12)/12.5663)*60)-gyroCorrection),-((((rightSpeed*12)/12.5663)*60)+gyroCorrection));
		} else {
			drivetrain.driveSpeed((((leftSpeed*12)/12.5663)*60)+gyroCorrection,(((rightSpeed*12)/12.5663)*60)-gyroCorrection);
		}
	}

	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(4);
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