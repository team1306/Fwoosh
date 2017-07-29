package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.lib.util.ProfileParams;

public class Profile2D {

	private ProfileParams params;
	private double leftX;
	private double leftY;
	private double rightX;
	private double rightY;
	private double exitAngle;
	public double maxTime;
	
	public Profile leftPath, rightPath;
	
	public Profile2D(ProfileParams p, double dX, double dY, double a, double t) {
		
		double baseWidth = 25.5;
		
		params = p;
		leftX = dX +(baseWidth / 2);
		leftY = dY + (baseWidth / 2);
		rightX = dX - (baseWidth / 2);
		rightY = dY - (baseWidth / 2);
		exitAngle = a;
		maxTime = t;
		
		double leftDistance = (Math.PI*(leftX+leftY)*((3*(Math.pow(leftX-leftY,2))/(Math.pow(leftX+leftY,2)*Math.sqrt(-3*(Math.pow(leftX-leftY,2)/Math.pow(leftX+leftY,2))+4+10)))+1))/(360/exitAngle);
		double rightDistance = (Math.PI*(rightX+rightY)*((3*(Math.pow(rightX-rightY,2))/(Math.pow(rightX+rightY,2)*Math.sqrt(-3*(Math.pow(rightX-rightY,2)/Math.pow(rightX+rightY,2))+4+10)))+1))/(360/exitAngle);
		
		double conversion = leftDistance / rightDistance;

		leftPath = new Profile(leftDistance,params.velocity*conversion,params.acceleration*conversion,params.jerk*conversion,maxTime);
		rightPath = new Profile(rightDistance,params.velocity,params.acceleration,params.jerk,maxTime);
	}
	
	
}
