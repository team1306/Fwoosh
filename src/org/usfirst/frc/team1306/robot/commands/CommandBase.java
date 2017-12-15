package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.drivetrain.Settings;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.Device;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.DriveMode;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.TalonType;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @CommandBase
 * 
 * This class is the abstract for all other commands. This static class contains instances of all the subsystems and the oi class 
 * so that each command that extends this class can have access to the subsystems.
 * 
 * @author Jackson Goth
 */
public abstract class CommandBase extends Command {

	private static Settings driveConfig;
	
	protected static Drivetrain drivetrain;
	protected static OI oi;
	
	public static void init() {
		
		/* Drivetrain configuration which tells the subsystem how many Talon SRXs are present, if encoders and gyro are present, and what driving mode the driver wants */
		driveConfig = new Settings();
		driveConfig.add(new CANTalon(RobotMap.LEFT_TALON_1_PORT),TalonType.LEFT_MASTER);
		driveConfig.add(new CANTalon(RobotMap.RIGHT_TALON_1_PORT),TalonType.RIGHT_MASTER);
		driveConfig.add(new CANTalon(RobotMap.LEFT_TALON_2_PORT),TalonType.LEFT_SLAVE);
		driveConfig.add(new CANTalon(RobotMap.RIGHT_TALON_2_PORT),TalonType.RIGHT_SLAVE);
		driveConfig.add(Device.ENCODER);
		driveConfig.add(Device.GYRO); 
		driveConfig.setDriveMode(DriveMode.ARCADE);
		
		drivetrain = new Drivetrain(driveConfig);
				
		oi = new OI(); //OI is always initialized last
	}

}
