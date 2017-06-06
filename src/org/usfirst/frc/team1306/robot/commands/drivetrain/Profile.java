package org.usfirst.frc.team1306.robot.commands.drivetrain;

import java.util.ArrayList;
import org.usfirst.frc.team1306.robot.Constants;

public class Profile {

	ProfileStatus status;
	ArrayList<Point> path;
	double distance, maxVel, maxAccel, maxTime;
	
	public Profile(double d, double v, double a, double t) {
		distance = d;
		maxVel = v;
		maxAccel = a;
		maxTime = t;
		
		double midpoint = distance / 2;
		double maxSteps = maxTime / Constants.PROFILE_UPDATE_RATE;
		int accelerationSteps = 0;
		int constantPreSteps = 0;
		int constantPostSteps = 0;
		int decelerationSteps = 0;
		
		status = ProfileStatus.ACCELERATING;
		
		path = new ArrayList<Point>();
		path.add(new Point(0,0,0));
		
		for(int i = 1; i < maxSteps; i++) {
			
			double acceleration = 0, velocity = 0, position = 0;
			Point prevPoint = path.get(i - 1);
			
			if(prevPoint.velocity >= maxVel) {
				status = ProfileStatus.CONSTANT_PRE_MIDPOINT;
			}
			
			if(prevPoint.position >= midpoint) {
				status = ProfileStatus.CONSTANT_POST_MIDPOINT;
			}
			
			if(status.equals(ProfileStatus.CONSTANT_POST_MIDPOINT) && constantPreSteps == constantPostSteps) {
				status = ProfileStatus.DECELERATING;
			}
			
			if(status.equals(ProfileStatus.ACCELERATING)) {
				acceleration = maxAccel;
				velocity = prevPoint.velocity + (acceleration * Constants.PROFILE_UPDATE_RATE);
				accelerationSteps++;
			} else if(status.equals(ProfileStatus.CONSTANT_PRE_MIDPOINT)) {
				acceleration = 0;
				velocity = prevPoint.velocity;
				constantPreSteps++;
			} else if(status.equals(ProfileStatus.CONSTANT_POST_MIDPOINT)) {
				acceleration = 0;
				velocity = prevPoint.velocity;
				constantPostSteps++;
			} else if(status.equals(ProfileStatus.DECELERATING)) {
				if(decelerationSteps == accelerationSteps) {
					acceleration = 0;
					velocity = 0;
					status = ProfileStatus.FINISHED;
				} else {
					acceleration = maxAccel;
					velocity = prevPoint.velocity - (acceleration * Constants.PROFILE_UPDATE_RATE);
					decelerationSteps++;
				}				
			} else if(status.equals(ProfileStatus.FINISHED)) {
				acceleration = 0;
				velocity = 0;
			}
			
			position = prevPoint.position + (velocity * Constants.PROFILE_UPDATE_RATE);
			path.add(new Point(acceleration, velocity, position));
		}
	}
	
	public enum ProfileStatus {ACCELERATING, CONSTANT_PRE_MIDPOINT, CONSTANT_POST_MIDPOINT, DECELERATING, FINISHED};
}
