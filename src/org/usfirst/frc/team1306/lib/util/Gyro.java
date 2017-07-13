package org.usfirst.frc.team1306.lib.util;

import org.usfirst.frc.team1306.lib.util.Settings.GyroType;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * Gyro
 * 
 * 
 * @author Jackson Goth
 */
public class Gyro {

	private AnalogDevicesGyro ad_imu;
	private AHRS navx;
	public GyroType currentGyro;
	
	public Gyro(GyroType type) {
		
		currentGyro = type;
		
		if(type.equals(GyroType.AD_IMU)) {
			ad_imu = new AnalogDevicesGyro();
		} else if(type.equals(GyroType.NAVX)) {
			try {
				navx = new AHRS(SPI.Port.kMXP);
				navx.reset();
				navx.resetDisplacement();
				navx.zeroYaw();
			} catch(RuntimeException ex) {
				
			}
		}
	}
	
	public double getAngle() {
		if(currentGyro.equals(GyroType.NAVX)) { //Try getCompassHeading()
			return navx.getAngle();
		} else {
			return ad_imu.getAngle();
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
