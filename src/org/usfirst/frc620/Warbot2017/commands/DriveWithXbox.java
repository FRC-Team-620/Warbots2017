package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.RobotMap;
import org.usfirst.frc620.Warbot2017.subsystems.ButtonReader;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class DriveWithXbox extends Command {
	// UsbCamera test = new UsbCamera("test",0 ); Are we still using this?

	public DriveWithXbox() {
		requires(Robot.driveTrain);
	}

	protected void initialize() {
		//RobotMap.visionlightSpike.set(Relay.Value.kForward);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		XboxController xbox = Robot.oi.getXbox();
		double lTrigger = Robot.oi.getLTrigger();
		
		double z = -xbox.getRawAxis(0);
		double y = xbox.getRawAxis(1);
		double x = -xbox.getRawAxis(4);

		if (Robot.oi.getLBumper()) {
			x = (Math.abs(x) < 0.3) ? 0 : x * .7; // X Dead Zone and scaling
			y = (Math.abs(y) < 0.3) ? 0 : y * .5; // Y Dead Zone and scaling
			z = (Math.abs(z) < 0.3) ? 0 : z * .5; // Z Dead Zone and scaling
		} else {
			x = (Math.abs(x) < 0.3) ? 0 : x * (1 - (lTrigger * .6)); // X Dead Zone
			y = (Math.abs(y) < 0.3) ? 0 : y * (1 - (lTrigger * .75)); // Y Dead Zone
			z = (Math.abs(z) < 0.3) ? 0 : z * (1 - (lTrigger * .75)); // Z Dead Zone
		}

		Robot.driveTrain.mecanumDrive(-x, -y, -z, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
