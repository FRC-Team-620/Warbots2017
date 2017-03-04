package org.usfirst.frc620.Warbot2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class StopsAllThings extends Command {

	public StopsAllThings() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		setInterruptible(false);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Starting StopsAllThings()");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Scheduler.getInstance().removeAll();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
