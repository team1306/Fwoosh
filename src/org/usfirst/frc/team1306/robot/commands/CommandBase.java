package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.lib.util.Settings;
import org.usfirst.frc.team1306.lib.util.Settings.EncoderType;
import org.usfirst.frc.team1306.lib.util.Settings.GyroType;
import org.usfirst.frc.team1306.lib.util.Settings.TalonType;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the abstract for all other commands. This static class contains
 * instances of all the subsystems and the oi class so that each command that
 * extends this class can have access to the subsystems.
 * 
 * @author Jackson Goth
 */
public abstract class CommandBase extends Command {

	private static Settings driveConfig;
	
	protected static Drivetrain drivetrain;
	protected static OI oi;
	
	public static void init() {
		
		driveConfig = new Settings(); //Drivetrain Configuration
		
		/* Adding all of the TalonSRXs, one master and one slave for each side */
		driveConfig.add(new CANTalon(RobotMap.LEFT_TALON_1_PORT),TalonType.LEFT_MASTER);
		driveConfig.add(new CANTalon(RobotMap.RIGHT_TALON_1_PORT),TalonType.RIGHT_MASTER);
		driveConfig.add(new CANTalon(RobotMap.LEFT_TALON_2_PORT),TalonType.LEFT_SLAVE);
		driveConfig.add(new CANTalon(RobotMap.RIGHT_TALON_2_PORT),TalonType.RIGHT_SLAVE);
		
		driveConfig.add(EncoderType.GRAYHILL); //Adding encoders to the config
		driveConfig.add(GyroType.NAVX); //Adding a gyro to the config
		
		drivetrain = new Drivetrain(driveConfig);
		oi = new OI(); //OI is always initialized last
	}

}
