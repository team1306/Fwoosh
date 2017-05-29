package org.usfirst.frc.team1306.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand.AutoMode;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Settings;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Settings.TalonType;

import com.ctre.CANTalon;

/**
 * @Project_Fwoosh
 * Framework Which Occasionally Offers Significant Help
 * 
 * Basic framework for robot code which will hopefully contain most of the subsystems we will use
 * in the next FRC season. 
 * 
 * @author Jackson Goth
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	Settings driveConfig;
	
	/**
	 * This function is run when the robot is first started up and we use it for
	 * sending an autonomous mode selection to the smartdashboard, setting up driver
	 * cameras, and initializing subsystems.
	 */
	@Override
	public void robotInit() {
		
		driveConfig = new Settings();
		driveConfig.add(new CANTalon(RobotMap.LEFT_TALON_1_PORT),TalonType.LEFT_MASTER);
		driveConfig.add(new CANTalon(RobotMap.RIGHT_TALON_1_PORT),TalonType.RIGHT_MASTER);
		driveConfig.add(new CANTalon(RobotMap.LEFT_TALON_1_PORT),TalonType.LEFT_SLAVE);
		driveConfig.add(new CANTalon(RobotMap.RIGHT_TALON_1_PORT),TalonType.RIGHT_SLAVE);
	
		CommandBase.init(driveConfig); //Initializes all Subsystems
		//CameraServer.getInstance().startAutomaticCapture("usb",0); //Camera 1
		
		chooser.addObject("Follow Path", new AutonomousCommand(AutoMode.FOLLOW_PATH));
		chooser.addDefault("Do Nothing", new AutonomousCommand(AutoMode.DO_NOTHING));
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot becomes disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		SmartDashboard.putString("STATUS:","DISABLED");
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called once each time the robot enters autonomous.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putString("STATUS:","AUTONOMOUS");
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called once the robot enters the driver controlled period.
	 */
	@Override
	public void teleopInit() {
		
		if (autonomousCommand != null) {
			autonomousCommand.cancel(); //Stops the autonomous command
		}
	}

	/**
	 * This function is called periodically during the driver controlled period.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putString("STATUS:","ENABLED");
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
