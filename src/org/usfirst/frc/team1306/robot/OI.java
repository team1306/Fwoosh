package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.triggers.Button;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * @OI
 * 
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow control of the robot.
 * It is also where commands can get joystick/trigger readings and set the rumble on the controller.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public class OI {
	
	//Declare primary and secondary xbox controllers
	private static XboxController primaryController;
	private static XboxController secondaryController;
	
	//Declare buttons on primary controller
//	private final Button pbuttonA;
//	private final Button pbuttonB;
//	private final Button pbuttonX;
//	private final Button pbuttonY;
//	private final Button pbuttonRB;
//	private final Button pbuttonLB;
//	private final Button pbuttonStart;
//	private final Button pbuttonBack;
//	private final Trigger primaryDPadUp;
//	private final Trigger primaryDPadRight;
//	private final Trigger primaryDPadLeft;
//	private final Trigger primaryDPadDown;
	
	//Declare buttons on secondary controller
//	private final Button sbuttonA;
//	private final Button sbuttonB;
//	private final Button sbuttonX;
//	private final Button sbuttonY;
//	private final Button sbuttonRB;
//	private final Button sbuttonLB;
//	private final Button sbuttonStart;
//	private final Button sbuttonBack;	
//	private final Trigger secondaryDPadUp;
//	private final Trigger secondaryDPadRight;
//	private final Trigger secondaryDPadLeft;
//	private final Trigger secondaryDPadDown;
	
	public OI() {
		
		//Declare ports of xbox controllers
		primaryController = new XboxController(RobotMap.PRIMARY_PORT);
		secondaryController = new XboxController(RobotMap.SECONDARY_PORT);
		
		//Map buttons to xbox controller buttons for primary controller
//		pbuttonA = new JoystickButton(primaryController, XboxController.A);
//		pbuttonB = new JoystickButton(primaryController, XboxController.B);
//		pbuttonX = new JoystickButton(primaryController, XboxController.X);
//		pbuttonY = new JoystickButton(primaryController, XboxController.Y);
//		pbuttonRB = new JoystickButton(primaryController, XboxController.RB);
//		pbuttonLB = new JoystickButton(primaryController, XboxController.LB); 
//		pbuttonStart = new JoystickButton(primaryController, XboxController.START);
//		pbuttonBack = new JoystickButton(primaryController, XboxController.BACK);
//		primaryDPadUp = new DPadPress(primaryController, DPadDirection.UP);
//		primaryDPadRight = new DPadPress(primaryController, DPadDirection.RIGHT);
//		primaryDPadLeft = new DPadPress(primaryController, DPadDirection.LEFT);
//		primaryDPadDown = new DPadPress(primaryController, DPadDirection.DOWN);
		
		//Map buttons to xbox controller buttons for secondary controller
//		sbuttonA = new JoystickButton(secondaryController, XboxController.A);
//		sbuttonB = new JoystickButton(secondaryController, XboxController.B);
//		sbuttonX = new JoystickButton(secondaryController, XboxController.X);
//		sbuttonY = new JoystickButton(secondaryController, XboxController.Y);
//		sbuttonRB = new JoystickButton(secondaryController, XboxController.RB);
//		sbuttonLB = new JoystickButton(secondaryController, XboxController.LB);
//		sbuttonStart = new JoystickButton(secondaryController, XboxController.START);
//		sbuttonBack = new JoystickButton(secondaryController, XboxController.BACK);
//		secondaryDPadUp = new DPadPress(secondaryController, DPadDirection.UP);
//		secondaryDPadRight = new DPadPress(secondaryController, DPadDirection.RIGHT);
//		secondaryDPadLeft = new DPadPress(secondaryController, DPadDirection.LEFT);
//		secondaryDPadDown = new DPadPress(secondaryController, DPadDirection.DOWN);
		
	}
	
	public enum Controller {P,S}; //Controller (primary or secondary)
	public enum Joystick {L,R}; //Joystick (left or right)
	public enum Axis {X,Y}; //Joystick axis (x or y)
	
	public enum Trigger {L,R}; //Trigger (left or right)
	
	public enum Side {L,R}; //Side (left or right) (for rumble)
	
	/**
	 * Returns the joystick value (from -1.0 to 1.0) for a specified controller's joystick's axis (uses deadband)
	 */
	public static double getJoyVal(Controller c, Joystick j, Axis a) {
		
		XboxController controller;
		Hand side;
		
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		if(j.equals(Joystick.L)) { side = Hand.kLeft; }
		else { side = Hand.kRight; }
		
		if(a.equals(Axis.X)) {
			return Math.pow(deadband(controller.getXNew(side)),Constants.JOYSTICK_MULTIPLIER);
		} else {
			return Math.pow(deadband(controller.getYNew(side)),Constants.JOYSTICK_MULTIPLIER);
		}
	}
	
	/**
	 * Returns the value of the specified trigger (from 0.0 to 1.0)
	 */
	public static double getTriggerVal(Controller c, Trigger t) {
		
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(t.equals(Trigger.L)) {
			return controller.getLT();
		} else {
			return controller.getRT();
		}
	}
	
	/**.
	 * Returns the value of a specified button on a specified controller
	 */
	public static boolean getButtonStatus(Controller c, Button b) {
		
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		return controller.getRawButton(b.value);
	}
	
	/**
	 * Sets the rumble of a specified controller to a specified amount of rumble
	 */
	public static void setRumble(Controller c, Side s, double rumbleness) {
		
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(s.equals(Side.L)) {
			controller.setRumble(GenericHID.RumbleType.kLeftRumble, rumbleness);
		} else {
			controller.setRumble(GenericHID.RumbleType.kRightRumble, rumbleness);
		}
	}
	
	/**
	 * Resets the rumble on the specified side of a specified controller
	 */
	public static void resetRumble(Controller c, Side s) {
		
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(s.equals(Side.L)) {
			controller.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
		} else {
			controller.setRumble(GenericHID.RumbleType.kRightRumble, 0);
		}
	}
	
	/**
	 * Applies deadband to joystick values to prevent false readings when joystick is idle. It prevents 
	 * very small changes to an idle joystick to trigger anything.
	 */
	private static double deadband(double value) {
		if (value < -Constants.DEADBAND) {
			return (value + Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		} else if (value > Constants.DEADBAND) {
			return (value - Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		} else {
			return 0;
		}
	}
}