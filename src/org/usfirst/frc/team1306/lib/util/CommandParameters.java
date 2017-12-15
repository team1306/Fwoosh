package org.usfirst.frc.team1306.lib.util;

import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

public class CommandParameters {

	public CommandType commandType;
	public FinishedType finishedType;
	public ControllerButton button;
	public Controller controller;
	public double time;
	
	public CommandParameters(CommandType cType, FinishedType fType) {
		commandType = cType;
		finishedType = fType;
	}
	
	public CommandParameters(CommandType cType, Controller c, ControllerButton b, FinishedType fType) {
		commandType = cType;
		finishedType = fType;
		controller = c;
		button = b;
	}
	
	public CommandParameters(CommandType cType, double t, FinishedType fType) {
		commandType = cType;
		finishedType = fType;
		time = t;
	}
	
	public enum CommandType {SPIN,PUSH,PULL};
	public enum FinishedType {WHILE_HELD,TOGGLED,INSTANT,TIME}
	
}

