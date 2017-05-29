package org.usfirst.frc.team1306.robot.commands.drivetrain;

import java.util.ArrayList;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class DriveSide {

	ArrayList<CANTalon> talons;
	
	public DriveSide(ArrayList<CANTalon> talons) {
		this.talons = new ArrayList<CANTalon>();
		this.talons = talons;
		
		if(talons.size() > 0) {
			talons.get(0).changeControlMode(TalonControlMode.PercentVbus);
			talons.get(0).set(0.0);
			talons.get(0).enable();
			
			for(int i = 1; i < talons.size(); i++) {
				talons.get(i).changeControlMode(TalonControlMode.Follower);
				talons.get(i).set(talons.get(0).getDeviceID());
				talons.get(i).enable();
			}
		}
	}
	
	public void changeControlMode(TalonControlMode mode) {
		talons.get(0).changeControlMode(mode);
	}
	
	public void set(double speed) {
		talons.get(0).set(speed);
	}

}
