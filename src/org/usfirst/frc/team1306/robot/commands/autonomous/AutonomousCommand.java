package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public enum AutoMode {FOLLOW_PATH, NEW_AUTO, DO_NOTHING};

	public AutonomousCommand(AutoMode mode) {

		if(mode.equals(AutoMode.FOLLOW_PATH)) {
			
			addSequential(new FollowPath(new Profile(60,15,7.5,7.5,15))); //Distance, Velocity, Accel, Jerk, Time
		} 
		else if(mode.equals(AutoMode.NEW_AUTO)) {

			
		}
	}
}
