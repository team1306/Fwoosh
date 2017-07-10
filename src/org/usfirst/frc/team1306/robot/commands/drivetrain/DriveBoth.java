package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.controller;
import org.usfirst.frc.team1306.robot.OI.trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBoth extends CommandBase {

	public DriveBoth() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		//Drive forwards with both triggers
		double ValR = 0;
		double ValL = 0;

		SmartDashboard.putNumber("LeftTrigger",OI.getTriggerVal(controller.p,trigger.l));
		SmartDashboard.putNumber("RightTrigger",OI.getTriggerVal(controller.p,trigger.r));
		
		if(OI.getTriggerVal(controller.p, trigger.r) > 0.15) {//right trigger moves right side forwards
		ValR = (OI.getTriggerVal(controller.p, trigger.r)); //ValR = how depressed the right trigger is
		}
		
		if(OI.getTriggerVal(controller.p, trigger.l) > 0.15) {//left trigger moves left side forwards
		ValL = (OI.getTriggerVal(controller.p, trigger.l)); //ValL = how depressed the left trigger is
		}
		
		if(OI.getButtonVal(controller.p, 5) == true){ //RB moves right side in reverse
		ValR = -1;
		}	
		if(OI.getButtonVal(controller.p, 6) == true){//B moves left side in reverse
		ValL = -1;
		}
		
		SmartDashboard.putNumber("ValR",ValR);
		SmartDashboard.putNumber("ValL",ValL);
		
		drivetrain.driveVBus(ValL,ValR);	//triggerVal returns double between -1 and 1... driveVBus acceps input between -1  and 1
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
