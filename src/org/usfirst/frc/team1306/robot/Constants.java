package org.usfirst.frc.team1306.robot;

/**
 * To store finalized variables all in one place
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	//Subsystem Control (Switching to false will disable all output for that subsystem)	
	public final static boolean DRIVETRAIN_ENABLED = true;		
	
	//OI Constants
	public final static double DEADBAND = 0.15; //Joystick and trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0; //Joystick inputs raised to this power
	
	//Drivetrain Constants
	public final static int DRIVETRAIN_MOTOR_COUNT = 4;
}
