 package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithXbox extends Command {
	private int ramplvl;
	private static final double SECONDS_TO_RAMP = 1.0;
	private static final double RAMP_RATE = 1.0 / (50 * SECONDS_TO_RAMP);
	private XboxController xbox;
	// UsbCamera test = new UsbCamera("test",0 ); Are we still using this?
	
	public DriveWithXbox() {
		requires(Robot.driveTrain);
	}

	protected void initialize() {
		//RobotMap.visionlightSpike.set(Relay.Value.kForward);
		xbox = Robot.oi.getXbox();
		ramplvl = 0;
		
		Robot.cameras.brightenCamera(0);
	}

//	boolean thing = false;
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
//		System.out.println("Bottom: " + RobotMap.climberDownLimit.get());
//		System.out.println("1: " + RobotMap.ballDownLimit.get());
//		System.out.println("5: " + RobotMap.ballUpLimit.get());
//		System.out.println("Top: " + RobotMap.climberUpLimit.get());
//		System.out.println("Stop: " + RobotMap.climberContactLimit.get());
		
//		float temp= Robot.navX.getYaw();
//		System.out.println(timeSinceInitialized()+"NavX "+temp);
		
		double lTrigger = Robot.oi.getLTrigger();
		double z = -xbox.getRawAxis(0);
		double y = xbox.getRawAxis(1);
		double x = -xbox.getRawAxis(4);
		
		if (Robot.oi.getLBumper()) {
			x = (Math.abs(x) < 0.2) ? 0 : x * .5; // X Dead Zone and scaling
			y = (Math.abs(y) < 0.2) ? 0 : y * .5; // Y Dead Zone and scaling
			z = (Math.abs(z) < 0.25) ? 0 : z * (0.6 - Math.abs(y)); // Z Dead Zone and scaling
		} else if(Robot.oi.getLTrigger() > .3) {
			x = (Math.abs(x) < 0.25) ? 0 : x * (1 - (lTrigger * .6)); // X Dead Zone
			y = (Math.abs(y) < 0.25) ? 0 : y * (1 - (lTrigger * .75)); // Y Dead Zone
			z = (Math.abs(z) < 0.25) ? 0 : z * (1 - (lTrigger * .75)); // Z Dead Zone
			 /* z *= (y == 0.0) ? 1.0 : 1 / (Math.abs(y) + 1);
			 *  if(z < .25) z = .25;
			 */
			
			/* Something new we are trying */
//			x = (Math.abs(x) < 0.3) ? 0 : x * .8; // X Dead Zone
//			y = (Math.abs(y) < 0.3) ? 0 : y * .8; // Y Dead Zone
//			z = (Math.abs(z) < 0.3) ? 0 : z * .8;
		} else {
			x = (Math.abs(x) < 0.25) ? 0 : x; // X Dead Zone
			y = (Math.abs(y) < 0.25) ? 0 : y; // Y Dead Zone
			z = (Math.abs(z) < 0.25) ? 0 : z * (1.1 - Math.abs(y));
		}//if(y>.8)z=0;x=0;
		
//		if(!thing && (Robot.oi.getRTrigger() > .5))
//			Robot.cameras.nextCamera();
//		thing = Robot.oi.getRTrigger() > .5;

//		if(y == 0)
//			ramplvl = 0;
//		else if(ramplvl < 1.0 / RAMP_RATE)
//			ramplvl++;
		
		Robot.driveTrain.mecanumDrive(-x, -y /** ramplvl * RAMP_RATE*/, -z, Robot.oi.gyro);
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
