// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc620.Warbot2017.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc620.Warbot2017.Robot;

public class LowerGearArm extends Command {
	private double perTime;
	private double speed;
	
	public LowerGearArm() {
		this(1.0, 0.9);
	}
	
	public LowerGearArm(float speed) {
		this(1.0, (double) speed);
	}

	public LowerGearArm(double perTime) {
		this(perTime, 0.9);
	}
	
	public LowerGearArm(double percentTime, double speed) {
		requires(Robot.gearArm);
		
		this.perTime = percentTime;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Starting LowerGearArm");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(System.currentTimeMillis() < Robot.armLastTriggered + Robot.DELAY * 1000)
			Robot.gearArm.move(0.0);
		else
			Robot.gearArm.move(-speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timeSinceInitialized() > (2.5 * perTime);
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("LowerGearArm finished");
		Robot.gearArm.move(0);
		Robot.armLastTriggered = System.currentTimeMillis();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
