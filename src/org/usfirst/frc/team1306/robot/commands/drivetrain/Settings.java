package org.usfirst.frc.team1306.robot.commands.drivetrain;

import java.util.ArrayList;
import com.ctre.CANTalon;

/**
 * This class manages and stores all the drivetrain settings.
 * @author Jackson Goth
 */
public class Settings {

	public ArrayList<CANTalon> leftSide, rightSide;
	public DriveMode controlMode;
	
	public Gyro gyro;
	public boolean gyroPresent = false;
	
	public Settings() {
		leftSide = new ArrayList<CANTalon>();
		rightSide = new ArrayList<CANTalon>();
		
		controlMode = DriveMode.ARCADE;
	}
	
	public void add(CANTalon talon, TalonType type) {
		if(type.equals(TalonType.LEFT_MASTER)) {
			leftSide.add(0,talon);
		} else if(type.equals(TalonType.LEFT_SLAVE)) {
			leftSide.add(talon);
		} else if(type.equals(TalonType.RIGHT_MASTER)) {
			rightSide.add(0,talon);
		} else if(type.equals(TalonType.RIGHT_SLAVE)) {
			rightSide.add(talon);
		}
	}
	
	public void add(GyroType type) {
		gyroPresent = true;
		gyro = new Gyro(type);
	}
	
	public enum GyroType {NAVX, AD_IMU};
	
	public enum TalonType {LEFT_MASTER, RIGHT_MASTER, LEFT_SLAVE, RIGHT_SLAVE};
	
	public enum DriveMode {ARCADE, TANK_DRIVE};
}
