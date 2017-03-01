package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class JiggleBalls extends Command
{
	@Override
	protected void execute()
	{
		Robot.driveTrain.mecanumDrive(.5 * Math.sin(2 * Math.PI * timeSinceInitialized() / 200), 0, 0, 0);
	}

	@Override
	protected boolean isFinished()
	{
		return timeSinceInitialized() > 1000;
	}
}
