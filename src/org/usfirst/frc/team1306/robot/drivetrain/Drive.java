package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Axis;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Joystick;
import org.usfirst.frc.team1306.robot.OI.Trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives the robot in "tank-drive" mode with each trigger and joystick controlling it's respective half of the drivetrain.
 * @author Sam Roquitte and Jackson Goth
 */
public class Drive extends CommandBase {

	public Drive() {
		requires(drivetrain);
	}

	@Override
	protected void execute() {

		SmartDashboard.putNumber("tester",OI.getTriggerVal(Controller.P,Trigger.R));
		
		/**
		 * If Trigger pressed it will figure out which one and either go forward or backward based on the values from them.
		 * Otherwise it assumes it is being controlled by joysticks and will drive robot based on their respective inputs.
		 */
		if(OI.getTriggerVal(Controller.P, Trigger.L) >= Constants.DEADBAND || OI.getTriggerVal(Controller.P, Trigger.R) >= Constants.DEADBAND) {
			if(OI.getTriggerVal(Controller.P, Trigger.R) >= Constants.DEADBAND) {
				drivetrain.driveVBus(OI.getTriggerVal(Controller.P, Trigger.R), OI.getTriggerVal(Controller.P, Trigger.R));
			} else if(OI.getTriggerVal(Controller.P, Trigger.L) >= Constants.DEADBAND) {
				drivetrain.driveVBus(-OI.getTriggerVal(Controller.P, Trigger.L), -OI.getTriggerVal(Controller.P, Trigger.L));
			}
		} else {
			drivetrain.driveVBus(OI.getJoyVal(Controller.P, Joystick.L, Axis.Y), OI.getJoyVal(Controller.P, Joystick.R, Axis.Y));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}
}
