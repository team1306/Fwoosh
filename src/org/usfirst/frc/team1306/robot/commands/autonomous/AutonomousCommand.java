package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.lib.util.ProfileParams;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile2D;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @AutonomousCommand
 * 
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup {

	public enum AutoMode {FOLLOW_PATH, FOLLOW_2D_PATH, TESTING, DO_NOTHING};

	public AutonomousCommand(AutoMode mode) {

		if(mode.equals(AutoMode.FOLLOW_PATH)) {
			
			addSequential(new FollowPath(new Profile(96,18.25,45,45,15))); //Distance, Velocity, Accel, Jerk, Max Time
		
		} else if(mode.equals(AutoMode.FOLLOW_2D_PATH)) {
			
			ProfileParams params = new ProfileParams(18.25,45,45);
			addSequential(new Follow2DPath(new Profile2D(params,60,60,90,15))); //Params, DistanceX, DistanceY, Exit Angle, Max Time
			
		} else if(mode.equals(AutoMode.TESTING)) {

			
		}
	}
}
