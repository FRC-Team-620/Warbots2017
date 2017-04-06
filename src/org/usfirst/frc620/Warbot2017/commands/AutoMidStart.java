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

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 284.48 cm to baseline robot is 81.28 cm 203.2 cm from front of robot to
 * baseline
 * 
 * 80 in from front of robot to baseline
 */
public class AutoMidStart extends CommandGroup {
	public AutoMidStart() {

		// addSequential(new DriveTime(58));
		// //try the 2 argument DriveTime to increase speed
		//// addSequential(new DriveUntilDist(20, 1.0));
		// addSequential(new DriveUntilDist(11, .5));
		// addSequential(new DriveTime(7));
		// addSequential(new LowerGearArm(.5));
		// addParallel(new DriveTime(.5, -.5);
		// addSequential(new RaiseGearArm());

		// addParallel(new Turn(-90));
		// addSequential(new DriveTime(45));
		// addSequential(new Turn(90));
		// addSequential(new DriveTime(5, 1.0));

		// //try the 2 argument DriveTime to increase speed
		// addSequential(new DriveUntilDist(20, 1.0));
		// addSequential(new DriveUntilDist(11, .5));
		// addSequential(new DriveDistance(7));
		
		// New PID loopy code
		addSequential(new DriveDistance(104,7));
		addSequential(new LowerGearArm(.5));
		addParallel(new DriveDistance(-7));
		addSequential(new RaiseGearArm());
	}
}
