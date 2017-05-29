package org.usfirst.frc.team1306.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Jackson Goth
 */
public class RobotMap {
	
	//OI Ports
	public static final int PRIMARY_PORT = 0;
	public static final int SECONDARY_PORT = 1;
	
	//Drivetrain Ports
	public static final int LEFT_TALON_1_PORT = 1;
	public static final int LEFT_TALON_2_PORT = 3;
	public static final int RIGHT_TALON_1_PORT = 2;
	public static final int RIGHT_TALON_2_PORT = 4; 
}
