package org.usfirst.frc.team1306.lib.util;

import org.usfirst.frc.team1306.lib.util.CommandParameters.CommandType;
import org.usfirst.frc.team1306.lib.util.CommandParameters.FinishedType;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class PrimitiveCommand extends CommandBase {
	
	private PrimitiveSubsystem mechanism;
	private CommandParameters params;
	private Timer timer;
	
	public PrimitiveCommand(PrimitiveSubsystem s, CommandParameters p) {
		mechanism = s;
		params = p;
		
		if(params.finishedType.equals(FinishedType.TIME)) {
			timer = new Timer();
		}
	}

	@Override
	protected void initialize() {
		if(params.finishedType.equals(FinishedType.TIME)) {
			timer.reset();
			timer.start();
		}
	}

	@Override
	protected void execute() {
		if(params.commandType.equals(CommandType.SPIN)) {
			mechanism.spinAllMotors(mechanism.motorSpeed);
		} else if(params.commandType.equals(CommandType.PUSH)) {
			mechanism.pushOut();
		} else if(params.commandType.equals(CommandType.PULL)) {
			mechanism.pullIn();
		}
	}

	@Override
	protected boolean isFinished() {
		if(params.finishedType.equals(FinishedType.INSTANT)) {
			return true;
		} else if(params.finishedType.equals(FinishedType.TIME)) {
			if(timer.hasPeriodPassed(params.time)) { return true; } 
			else { return false; }
		} else if(params.finishedType.equals(FinishedType.WHILE_HELD)) {
			if(OI.getButtonStatus(params.controller,params.button)) { return false; }
			else { return true; }
		} else if(params.finishedType.equals(FinishedType.TOGGLED)) {
			return false;
		}
		return false;
	}

	@Override
	protected void end() {
		if(params.finishedType.equals(FinishedType.TOGGLED)) {
			mechanism.stopAll();
		}
	}

	@Override
	protected void interrupted() {
		end();
	}
}
