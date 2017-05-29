package org.usfirst.frc.team1306.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public enum AutoMode {FOLLOW_PATH, DO_NOTHING};
	
	public AutonomousCommand(AutoMode mode) {
		
		if(mode.equals(AutoMode.FOLLOW_PATH)) {
			
		} else {
			
		}
	}
}
