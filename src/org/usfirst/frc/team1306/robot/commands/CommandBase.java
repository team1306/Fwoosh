package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Settings;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the abstract for all other commands. This static class contains
 * instances of all the subsystems and the oi class so that each command that
 * extends this class can have access to the subsystems.
 * 
 * @author Jackson Goth
 */
public abstract class CommandBase extends Command {

	protected static Drivetrain drivetrain;
	protected static OI oi;
	
	public static void init(Settings driveConfig) {
		drivetrain = new Drivetrain(driveConfig);
		oi = new OI(); //This must be initialized last
	}

}
