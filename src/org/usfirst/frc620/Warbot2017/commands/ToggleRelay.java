package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleRelay extends Command {

    public ToggleRelay() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.visionlightSpike.get() == Relay.Value.kForward)
    		RobotMap.visionlightSpike.set(Relay.Value.kOff);
    	else if(RobotMap.visionlightSpike.get() == Relay.Value.kOff)
    		RobotMap.visionlightSpike.set(Relay.Value.kForward);
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
