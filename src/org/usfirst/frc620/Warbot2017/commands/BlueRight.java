package org.usfirst.frc620.Warbot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueRight extends CommandGroup {// Human Player

    public BlueRight() {
    	addParallel(new RaiseBallMech(2.5));
    	//START GET THERE
    	// was 81-13
        addSequential(new DriveDistance(84-13,10,.3));//13 is 1/2 bot length (we want the axis of rotation at 82 inches)
        addSequential(new Turn(-60));//was 57
        addSequential(new DriveDistance(102-13,5));//3 is the offset from the wall (we want to be 3 inches from the airship wall to deposit)
        //was 88
        //END GET THERE
        
//        //START DEPOSIT GEAR
//        addSequential(new LowerGearArm(.5));
//		addParallel(new DriveDistance(-7));
//		addSequential(new RaiseGearArm());
//		//END DEPOSIT GEAR
    }
}
