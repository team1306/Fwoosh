package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends CommandBase {

	public Drive() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		SmartDashboard.putString("Test","made it");
	}

	@Override
	protected void execute() {
		if(OI.getTriggerVal(controller.p, trigger.r) > 0.15) {//right trigger drives forwards
			double triggerValR = (OI.getTriggerVal(controller.p, trigger.r)); //set value that controller sends to triggerVal
			drivetrain.driveVBus(triggerValR,triggerValR);					//triggerVal returns double between 0 and 1... driveVBus acceps input between -1  and 1
		}
		
		if(OI.getTriggerVal(controller.p, trigger.l)> 0.15){ //left trigger drives backwards
			double triggerValL = (OI.getTriggerVal(controller.p, trigger.l));
			drivetrain.driveVBus(triggerValL, triggerValL);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
