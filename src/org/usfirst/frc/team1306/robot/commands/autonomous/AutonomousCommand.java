package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class AutonomousCommand extends CommandGroup {

	public enum AutoMode {FOLLOW_PATH, NEW_AUTO, DO_NOTHING};

	public AutonomousCommand(AutoMode mode) {

		if(mode.equals(AutoMode.FOLLOW_PATH)) {
			
			addSequential(new FollowPath(new Profile(60,15,7.5,7.5,15))); //Distance, Velocity, Accel, Jerk, Time
		} 
		else if(mode.equals(AutoMode.NEW_AUTO)) {
			addSequential (new DriveForwards(5.5, 0.2, 0.2));

			
		}
	}
}
