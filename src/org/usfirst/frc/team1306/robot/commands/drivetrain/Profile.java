package org.usfirst.frc.team1306.robot.commands.drivetrain;

import java.util.ArrayList;
import org.usfirst.frc.team1306.robot.Constants;

public class Profile {

	ProfileStatus status;
	ArrayList<Point> path;
	double distance, maxVel, maxAccel;
	int accelSteps;
	
	public Profile(double d, double v, double a) {
		distance = d;
		maxVel = v;
		maxAccel = a;
		accelSteps = 0;
		
		status = ProfileStatus.ACCELERATING;
		
		path = new ArrayList<Point>();
		path.add(new Point(0,0,0));
		
		for(int i = 1; i < Constants.PROFILE_MAX_STEPS; i++) {
			
			double acceleration, velocity, position;
			Point prevPoint = path.get(i - 1);
			
			if(prevPoint.velocity >= maxVel) {
				status = ProfileStatus.HOLD_SPEED;
				accelSteps = i;
			}
			
			if(status.equals(ProfileStatus.HOLD_SPEED) && Constants.PROFILE_MAX_STEPS - accelSteps == 0) {
				status = ProfileStatus.DECELERATING;
			}
			
			if(status.equals(ProfileStatus.ACCELERATING)) {
				acceleration = maxAccel;
				velocity = prevPoint.velocity + (acceleration * Constants.PROFILE_UPDATE_RATE);
				position = prevPoint.position + (velocity * Constants.PROFILE_UPDATE_RATE);
				path.add(new Point(acceleration, velocity, position));
			} else if(status.equals(ProfileStatus.HOLD_SPEED)) {
				acceleration = 0;
				velocity = prevPoint.velocity;
				position = prevPoint.position + (velocity * Constants.PROFILE_UPDATE_RATE);
				path.add(new Point(acceleration, velocity, position));
			} else if(status.equals(ProfileStatus.DECELERATING)) {
				acceleration = maxAccel;
				velocity = prevPoint.velocity - (acceleration * Constants.PROFILE_UPDATE_RATE);
				position = prevPoint.position + (velocity * Constants.PROFILE_UPDATE_RATE);
				path.add(new Point(acceleration, velocity, position));
			}
		}
	}
	
	public enum ProfileStatus {ACCELERATING, HOLD_SPEED, DECELERATING};
}
