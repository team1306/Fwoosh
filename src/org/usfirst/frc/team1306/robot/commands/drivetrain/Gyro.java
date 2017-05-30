package org.usfirst.frc.team1306.robot.commands.drivetrain;

import org.usfirst.frc.team1306.lib.util.AnalogDevicesGyro;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Settings.GyroType;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

public class Gyro {

	AnalogDevicesGyro ad_imu;
	AHRS navx;
	
	GyroType currentGyro;
	
	public Gyro(GyroType type) {
		currentGyro = type;
		if(type.equals(GyroType.AD_IMU)) {
			ad_imu = new AnalogDevicesGyro();
			
			
		} else if(type.equals(GyroType.NAVX)) {
			try {
				navx = new AHRS(SPI.Port.kMXP);
				navx.reset();
				navx.resetDisplacement();
			} catch(RuntimeException ex) {
				
			}
		}
	}
	
	public double getYaw() {
		if(currentGyro.equals(GyroType.NAVX)) { //Try getCompassHeading()
			return navx.getYaw();
		} else {
			return ad_imu.getYaw();
		}
	}
	
	public double getDisplacement(Axis axis) {
		if(currentGyro.equals(GyroType.NAVX)) {
			if(axis.equals(Axis.X)) {
				return navx.getDisplacementX();
			} else if(axis.equals(Axis.Y)) {
				return navx.getDisplacementY();
			} else {
				return navx.getDisplacementZ();
			}
		} else {
			return -1;
		}
	}
	
	public enum Axis {X,Y,Z};
}
