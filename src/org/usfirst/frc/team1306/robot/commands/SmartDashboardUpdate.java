package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Gyro.Axis;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdate extends CommandBase {
	
	public SmartDashboardUpdate() {
		setRunWhenDisabled(true);
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if(Constants.GYRO_DEBUG) {
			SmartDashboard.putNumber("Gyro-X",drivetrain.gyro.getDisplacement(Axis.X));
			SmartDashboard.putNumber("Gyro-Y",drivetrain.gyro.getDisplacement(Axis.Y));
			SmartDashboard.putNumber("Gyro-Z",drivetrain.gyro.getDisplacement(Axis.Z));
			SmartDashboard.putNumber("Gyro-Yaw",drivetrain.gyro.getYaw());
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
