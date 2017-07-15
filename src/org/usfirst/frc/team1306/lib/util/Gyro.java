package org.usfirst.frc.team1306.lib.util;

import org.usfirst.frc.team1306.lib.util.Settings.GyroType;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * @Gyro
 * 
 * This class is the key to accessing information from any type of gyro. If you want to switch between different
 * gyros, just create this object with a different gyrotype.  All methods will react accordingly and return values from
 * the correct kind of gyro.
 * @author Jackson Goth
 */
public class Gyro {

	private AnalogDevicesGyro ad_imu; //For using ADIS16448
	private AHRS navx; //For using NavX
	public GyroType currentGyro; //What gyro the robot is using, used by other classes
	
	public Gyro(GyroType type) {
		
		currentGyro = type;
		
		/* Initializes the correct gyro */
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
	
	/**
	 * Returns angle/yaw from correct gyro
	 */
	public double getAngle() {
		if(currentGyro.equals(GyroType.NAVX)) {
			return navx.getAngle();
		} else {
			return ad_imu.getAngle();
		}
	}
	
	/**
	 * Returns displacement from navx, or -1 from ad_imu because it doesn't have that functionality
	 */
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
	
	public enum Axis {X,Y,Z}; //Enum used to store possible axis for dislacement acquisition
}
