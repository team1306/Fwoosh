package org.usfirst.frc.team1306.robot.pathing;

public class ProfileParams {

	public double velocity, acceleration, jerk;
	
	public ProfileParams(double v, double a, double j) {
		velocity = v;
		acceleration = a;
		jerk = j;
	}
}
