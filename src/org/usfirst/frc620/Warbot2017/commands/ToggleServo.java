package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleServo extends Command {
	@Override
	protected void initialize() {
		System.out.println("ToggleServo starting");
		if(RobotMap.ropeServo.get() > .5){
			RobotMap.ropeServo.set(0);
		}else{
			RobotMap.ropeServo.set(1);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void end() {
		System.out.println("ToggleServo finished");
	}

	@Override
	public void interrupted() {
		end();
	}
}
