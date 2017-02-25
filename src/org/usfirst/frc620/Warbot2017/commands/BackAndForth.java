package org.usfirst.frc620.Warbot2017.commands;

import java.lang.reflect.AnnotatedArrayType;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BackAndForth extends Command 
{
	double speed = 0;
	long time;
	
	@Override
	protected void initialize() {
		System.out.println("Starting back and forth test.");
		time = System.currentTimeMillis();
	}
	
	@Override
	protected void execute() 
	{
		if(System.currentTimeMillis() - time >= 1000)
		{
			time = System.currentTimeMillis();
			speed = -speed;
			if(speed >= 0)
			{
				speed += .2;
				System.out.println("About to start speed " + speed);
			}
		}
		
		if(speed > 0 && System.currentTimeMillis() - time < 100)
			Robot.driveTrain.mecanumDrive(0, 0, 0, 0);
		
		Robot.driveTrain.mecanumDrive(0, speed, 0, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return speed >= .4;
	}
}
