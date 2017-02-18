package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives until the robot is {@code dist} cm away from the target (i.e. whatever is in front of it).
 * The lidar is used to achieve this. The {@code scaling} should be a number in the range (0.0, 1.0],
 * 1.0 meaning the robot will move at its max speed. It is advised (and in fact enforced) that your 
 * scaling factor not be less than 0.25
 */
public class DriveUntilDist extends Command {
	private int dist;
	private double k;

    public DriveUntilDist(int distInCentimeters, double scaling) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	dist = distInCentimeters;
    	if(scaling < .25) scaling = .25;
    	k = scaling;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.mecanumDrive(0.0, 1.0 * k, 0.0, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lidar.getDistance() <= dist;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
