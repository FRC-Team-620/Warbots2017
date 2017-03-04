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

public class AutoLeftStart extends CommandGroup {
    public AutoLeftStart() {
//    	System.out.println("Starting DriveTme(108)");
    	addSequential(new DriveTime(108));
//    	System.out.println("Starting Turn(60)");
    	addSequential(new Turn(60));
//    	System.out.println("Starting AlignForGearPeg(0, .3)");
//    	addSequential(new AlignForGearPeg(0, .3));
//    	System.out.println("Starting DriveUntilDist(12, .3");
    	addSequential(new DriveUntilDist(12, .3));
//    	System.out.println("Starting DepositGear())");
    	addSequential(new DepositGear());
//    	System.out.println("Starting RaiseGearArm()");
    	addSequential(new RaiseGearArm());
    } 
}
