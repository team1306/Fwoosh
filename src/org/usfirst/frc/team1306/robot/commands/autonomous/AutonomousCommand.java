package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.TriggerTesting;
import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public enum AutoMode {FOLLOW_PATH, TESTING, DO_NOTHING};

	public AutonomousCommand(AutoMode mode) {

		if(mode.equals(AutoMode.FOLLOW_PATH)) {
			
			addSequential(new FollowPath(new Profile(100,50,100,200,15))); //Distance, Velocity, Accel, Jerk, Time
		} 
		else if(mode.equals(AutoMode.TESTING)) {

			addSequential(new TriggerTesting());
		}
	}
}
