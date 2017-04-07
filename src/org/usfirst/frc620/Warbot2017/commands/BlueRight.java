package org.usfirst.frc620.Warbot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueRight extends CommandGroup {// Human Player

    public BlueRight() {
    	addParallel(new RaiseBallMech(2.5));
    	//START GET THERE
        addSequential(new DriveDistance(81-13,10));//13 is 1/2 bot length (we want the axis of rotation at 82 inches)
        addSequential(new Turn(-57));
        addSequential(new DriveDistance(88-3));//3 is the offset from the wall (we want to be 3 inches from the airship wall to deposit)
        //END GET THERE
        
//        //START DEPOSIT GEAR
//        addSequential(new LowerGearArm(.5));
//		addParallel(new DriveDistance(-7));
//		addSequential(new RaiseGearArm());
//		//END DEPOSIT GEAR
    }
}
