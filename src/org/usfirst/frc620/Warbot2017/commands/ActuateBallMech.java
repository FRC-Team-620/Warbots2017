package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ActuateBallMech extends Command {
	private double m_power;

	public ActuateBallMech() {
		requires(Robot.ballMech);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(!RobotMap.ballDownLimit.get()){
			m_power=.5;
		}
		else if (RobotMap.ballDownLimit.get()&&RobotMap.ballUpLimit.get()){
			m_power=.75;
		}
		else{
			m_power=0;
		}
		Robot.ballMech.actuate(m_power);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.oi.getLBumper();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
