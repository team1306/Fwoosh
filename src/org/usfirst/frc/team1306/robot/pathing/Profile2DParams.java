package org.usfirst.frc.team1306.robot.pathing;


/**
 * @PathParams
 * 
 * @author Jackson Goth
 */
public class Profile2DParams {

	public double time, timeInterval, robotTrackWidth;
	
	public Profile2DParams(double time, double timeInterval, double robotTrackWidth) {
		this.time = time;
		this.timeInterval = timeInterval;
		this.robotTrackWidth = robotTrackWidth;
	}
}
