package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.lib.util.Gyro.Axis;
import org.usfirst.frc.team1306.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @SmartDashboardUpdate
 * 
 * @author Jackson Goth
 */
public class SmartDashboardUpdate extends CommandBase {
	
	public SmartDashboardUpdate() {
		setRunWhenDisabled(true);
	}
	
	@Override
	protected void execute() {
		
		if(Constants.GYRO_DEBUG) {
			SmartDashboard.putNumber("Gyro-X",drivetrain.gyro.getDisplacement(Axis.X));
			SmartDashboard.putNumber("Gyro-Y",drivetrain.gyro.getDisplacement(Axis.Y));
			SmartDashboard.putNumber("Gyro-Z",drivetrain.gyro.getDisplacement(Axis.Z));
			SmartDashboard.putNumber("Gyro-Yaw",drivetrain.gyro.getAngle());
		}
		
		if(Constants.DRIVETRAIN_DEBUG) {
			SmartDashboard.putNumber("LeftSide-Position:",drivetrain.leftMotors.getEncPos());
			SmartDashboard.putNumber("RightSide-Position:",drivetrain.rightMotors.getEncPos());
			SmartDashboard.putNumber("LeftSide-AdjustPos:",Math.abs(drivetrain.leftMotors.getEncPos()/1024)*12.5663);
			SmartDashboard.putNumber("RightSide-AdjustPos:",Math.abs(drivetrain.rightMotors.getEncPos()/1024)*12.5663);
			SmartDashboard.putNumber("LeftSide-Velocity:",drivetrain.leftMotors.getEncVel());
			SmartDashboard.putNumber("RightSide-Velocity:",drivetrain.rightMotors.getEncVel());
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
