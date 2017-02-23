package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ClimbDriving extends Command {
	private boolean climb;

    public ClimbDriving() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	climb = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double lTrigger = Robot.oi.getLTrigger();
		double z = -Robot.oi.getXbox().getRawAxis(0);
		double y = Robot.oi.getXbox().getRawAxis(1);
		double x = -Robot.oi.getXbox().getRawAxis(4);
		
    	x = (Math.abs(x) < 0.3) ? 0 : x * (1 - (lTrigger * .6)); // X Dead Zone
		y = (Math.abs(y) < 0.3) ? 0 : y * (1 - (lTrigger * .75)); // Y Dead Zone
		z = (Math.abs(z) < 0.3) ? 0 : z * (1 - (lTrigger * .75)); // Z Dead Zone
		
		if(Robot.oi.getRTrigger() > .3) climb = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.oi.getRBumper() || climb;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Scheduler.getInstance().add(new Climb());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
