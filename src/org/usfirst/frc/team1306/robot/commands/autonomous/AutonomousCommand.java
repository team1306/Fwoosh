package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.lib.util.ProfileParams;
import org.usfirst.frc.team1306.robot.commands.TriggerTesting;
import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public enum AutoMode {FOLLOW_PATH, FOLLOW_2D_PATH, TESTING, DO_NOTHING};

	public AutonomousCommand(AutoMode mode) {

		if(mode.equals(AutoMode.FOLLOW_PATH)) {
			
			addSequential(new FollowPath(new Profile(96,18.25,45,45,15))); //Distance, Velocity, Accel, Jerk, Time
		} else if(mode.equals(AutoMode.FOLLOW_2D_PATH)) {
			
			ProfileParams params = new ProfileParams(18.25,45,45);
					
		} else if(mode.equals(AutoMode.TESTING)) {

			addSequential(new TriggerTesting());
		}
	}
}
