package org.usfirst.frc.team1306.robot;

/**
 * Constants
 * 
 * To store finalized variables all in one place
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	//Subsystem Control (Switching to false will disable all output for that subsystem)	
	public final static boolean DRIVETRAIN_ENABLED = true;		
	
	//SmartDashboard Debug Modes
	public static final boolean DRIVETRAIN_DEBUG = true;
	public static final boolean GYRO_DEBUG = true;
	
	//OI Constants
	public final static double DEADBAND = 0.15; //Joystick and trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0; //Joystick inputs raised to this power
	
	//Drivetrain Constants
	public final static double PROFILE_UPDATE_RATE = 0.01;
	public final static double ENCODER_CONVERSION = 1;
}
