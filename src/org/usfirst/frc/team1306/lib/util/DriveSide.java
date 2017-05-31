package org.usfirst.frc.team1306.lib.util;

import java.util.ArrayList;
import org.usfirst.frc.team1306.lib.util.Settings.EncoderType;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class DriveSide {

	ArrayList<CANTalon> talons;
	CANTalon master;
	
	public DriveSide(ArrayList<CANTalon> talons) {
		
		this.talons = new ArrayList<CANTalon>();
		this.talons = talons;
		
		master = talons.get(0);
		
		if(talons.size() > 0) {
			master.changeControlMode(TalonControlMode.PercentVbus);
			master.set(0.0);
			master.enable();
			
			for(int i = 1; i < talons.size(); i++) {
				talons.get(i).changeControlMode(TalonControlMode.Follower);
				talons.get(i).set(talons.get(0).getDeviceID());
				talons.get(i).enable();
			}
		}
	}
	
	public void changeControlMode(TalonControlMode mode) {
		master.changeControlMode(mode);
	}
	
	public void set(double speed) {
		master.set(speed);
	}
	
	public void initEncoders(EncoderType type) {
		
		master.setFeedbackDevice(type.device);
		if(!type.equals(EncoderType.CTRE_MAG)) {
			master.configEncoderCodesPerRev(type.codesRev);
		}
		master.configNominalOutputVoltage(type.nominalForwardVoltage,type.nominalReverseVoltage); //TODO Make this subsystem specialized instead of encoder specialized
		master.configPeakOutputVoltage(type.peakForwardVoltage,type.peakReverseVoltage); //TODO Same as above
	}
	
	public void setPIDParams(PIDParameters params) {
		master.setF(params.f);
		master.setP(params.p);
		master.setI(params.i);
		master.setD(params.d);
	}
	
	public void setMotionMagicParams(double cruiseVelocity, double acceleration) {
		master.setMotionMagicCruiseVelocity(cruiseVelocity);
		master.setMotionMagicAcceleration(acceleration);
	}
	
	public void flipLoopOutput(boolean flipped) {
		master.reverseOutput(flipped);
	}
	
	public void flipEncoderOutput(boolean flipped) {
		master.reverseSensor(flipped);
	}
	
	public double getEncPos() {
		return master.getEncPosition();
	}
	
	public double getEncVel() {
		return master.getEncVelocity();
	}
}
