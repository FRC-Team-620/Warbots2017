package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueLeft extends CommandGroup {//Hopper

    public BlueLeft() {
    	addParallel(new RaiseBallMech(2.5));
    	//START GET THERE
//        addSequential(new DriveDistance(80 - Robot.LENGTHHALF,10,.3));//13 is 1/2 bot length (we want the axis of rotation at 82 inches)
//        addSequential(new Turn(57));
//        addSequential(new DriveDistance(104 - Robot.LENGTHHALF * 2,5));//3 is the offset from the wall (we want to be 3 inches from the airship wall to deposit)
        //END GET THERE
    	addSequential(new DriveDistance(118.28 - Robot.LENGTHHALF,10,.3));//13 is 1/2 bot length (we want the axis of rotation at 82 inches)
        addSequential(new Turn(57));
        addSequential(new DriveDistance(21.94 - Robot.LENGTHHALF,5));//3 is the offset from the wall (we want to be 3 inches from the airship wall to deposit)
        //END GET THERE 
        
//        //START DEPOSIT GEAR
//        addSequential(new LowerGearArm(.5));
//		addParallel(new DriveDistance(-7));
//		addSequential(new RaiseGearArm());
//		//END DEPOSIT GEAR
    }
}
