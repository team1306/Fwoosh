package org.usfirst.frc.team1306.lib.util;

import java.util.ArrayList;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

/**
 * @Settings
 * 
 * This class manages and stores all the drivetrain settings.
 * @author Jackson Goth
 */
public class Settings {

	public Gyro gyro;
	public ArrayList<CANTalon> leftSide, rightSide;
	public boolean encoderPresent = false;
	public boolean gyroPresent = false;
	public EncoderType encoderType;
	public DriveMode controlMode;
	
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
	
	public void add(EncoderType type) {
		encoderPresent = true;
		encoderType = type;
	}
	
	public enum EncoderType {
		
		GRAYHILL(FeedbackDevice.QuadEncoder,256),
		RS7(FeedbackDevice.QuadEncoder,12), 
		CTRE_MAG(FeedbackDevice.CtreMagEncoder_Relative,-1);
		
		FeedbackDevice device;
		double nominalForwardVoltage = +0.0f, nominalReverseVoltage = -0.0f; //TODO Make this subsystem specialized
		double peakForwardVoltage = +12.0f, peakReverseVoltage = -12.0f; //TODO Same as above
		int codesRev;
		
		private EncoderType(FeedbackDevice d, int cR) {
			this.device = d;
			this.codesRev = cR;
		}
		
	};
	
	public enum GyroType {NAVX, AD_IMU};
	
	public enum TalonType {LEFT_MASTER, RIGHT_MASTER, LEFT_SLAVE, RIGHT_SLAVE};
	
	public enum DriveMode {ARCADE, TANK_DRIVE, OUTREACH};
}
