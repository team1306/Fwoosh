package org.usfirst.frc.team1306.lib.util;

import java.util.ArrayList;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.VelocityMeasurementPeriod;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VelocitySubsystem extends Subsystem {

	private ArrayList<CANTalon> talons;
	private boolean enabled = true;
	private double f, p, i, d;
	private String mechanism;
	
	public VelocitySubsystem(PIDParameters params, String name) {
		mechanism = name;
		talons = new ArrayList<CANTalon>();
		
		f = params.f;
		p = params.p;
		i = params.i;
		d = params.d;
	}
	
	public void spinAll(double speed) {
		if(talons.size() > 0 && enabled) {
			talons.get(0).set(speed);
		}
	}
	
	public void stopAll() {
		if(talons.size() > 0) {
			talons.get(0).changeControlMode(TalonControlMode.PercentVbus);
			talons.get(0).set(0.0);
		}
	}
	
	public void disable() {
		enabled = false;
	}
	
	public void addTalonSRX(int port) {
		try {
			CANTalon t = new CANTalon(port);
			if(talons.size() > 0) {
				t.changeControlMode(TalonControlMode.Follower);
				t.set(talons.get(0).getDeviceID());
			}
			t.enable();
			talons.add(t);
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:",mechanism + " is trying to re-use an existing or non-existent port");
		}
	}
	
	public void setTalonParams(FeedbackDevice device) {
		if(talons.size() > 0) {
			talons.get(0).setFeedbackDevice(device);
			if(device.equals(FeedbackDevice.QuadEncoder)) {
				talons.get(0).configEncoderCodesPerRev(12);
			}
			talons.get(0).configNominalOutputVoltage(+0.0f, -0.0f);
			talons.get(0).configPeakOutputVoltage(+12.0f, 0.0f);
			talons.get(0).SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_10Ms);
			talons.get(0).SetVelocityMeasurementWindow(20);
			talons.get(0).setF(f);
			talons.get(0).setP(p);
			talons.get(0).setI(i);
			talons.get(0).setD(d);	
		}
	}
	
	public void changeControlMode(TalonControlMode mode) {
		if(talons.size() > 0) {
			talons.get(0).changeControlMode(mode);
		}
	}
	
	public void reverseSensor() {
		if(talons.size() > 0) {
			talons.get(0).reverseSensor(true);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
