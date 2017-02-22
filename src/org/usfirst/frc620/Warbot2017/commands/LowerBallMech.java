package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class LowerBallMech extends Command
{

	public LowerBallMech()
	{
		requires(Robot.ballMech);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.ballMech.actuate(1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return timeSinceInitialized() >= 9;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.ballMech.actuate(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
