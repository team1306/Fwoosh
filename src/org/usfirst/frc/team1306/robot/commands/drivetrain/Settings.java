package org.usfirst.frc.team1306.robot.commands.drivetrain;

import java.util.ArrayList;
import org.usfirst.frc.team1306.robot.Constants;
import com.ctre.CANTalon;

public class Settings {

	public int NUMBER_OF_MOTORS;
	public ArrayList<CANTalon> leftSide, rightSide;
	
	public Settings() {
		NUMBER_OF_MOTORS = Constants.DRIVETRAIN_MOTOR_COUNT;
		leftSide = new ArrayList<CANTalon>();
		rightSide = new ArrayList<CANTalon>();
		
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
	
	public enum TalonType {LEFT_MASTER, RIGHT_MASTER, LEFT_SLAVE, RIGHT_SLAVE};
}
