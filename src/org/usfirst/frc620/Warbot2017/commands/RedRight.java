package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedRight extends CommandGroup {//Hopper

    public RedRight() {
    	addParallel(new RaiseBallMech(2.5));
    	//START GET THERE				
//        addSequential(new DriveDistance(71 - Robot.LENGTHHALF,10));//13 is 1/2 bot length (we want the axis of rotation at 82 inches)
//        // was 82-13
//        addSequential(new Turn(-55));//was 57
//        addSequential(new DriveDistance(112.5 - Robot.LENGTHHALF * 2, 5));//3 is the offset from the wall (we want to be 3 inches from the airship wall to deposit)
//        //END GET THERE
        addSequential(new DriveDistance(118.8 - Robot.LENGTHHALF,10));//13 is 1/2 bot length (we want the axis of rotation at 82 inches)
        // was 82-13
        addSequential(new Turn(-55));//was 57
        addSequential(new DriveDistance(25.4 - Robot.LENGTHHALF, 5));//3 is the offset from the wall (we want to be 3 inches from the airship wall to deposit)
        //END G
        
//        //START DEPOSIT GEAR
//        addSequential(new LowerGearArm(.5));
//		addParallel(new DriveDistance(-7));
//		addSequential(new RaiseGearArm());
//		//END DEPOSIT GEAR// Use requires() here to declare subsystem dependencies
    }
}
