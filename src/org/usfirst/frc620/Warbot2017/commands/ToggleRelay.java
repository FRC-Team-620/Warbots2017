package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleRelay extends InstantCommand {

    public ToggleRelay() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.visionlightSpike.get() == Relay.Value.kForward)
    		RobotMap.visionlightSpike.set(Relay.Value.kOff);
    	else if(RobotMap.visionlightSpike.get() == Relay.Value.kOff)
    		RobotMap.visionlightSpike.set(Relay.Value.kForward);
    }
}
