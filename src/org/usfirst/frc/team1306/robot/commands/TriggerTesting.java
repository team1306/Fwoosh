package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TriggerTesting extends CommandBase {

	@Override
	protected void initialize() {
		setRunWhenDisabled(true);
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("LeftTriggerStatus",OI.getTriggerStatus());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
