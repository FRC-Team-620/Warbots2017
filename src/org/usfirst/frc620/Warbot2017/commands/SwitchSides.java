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
import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SwitchSides extends InstantCommand {
    public SwitchSides() {
        requires(Robot.driveTrain);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.gyro=(Robot.oi.gyro+90)%270;
    	Robot.cameras.switchToCamera(Robot.oi.gyro/90);
    }
    
//    protected double switchX(){
//    	return 0;
//    }
//    protected double switchY(){
//    	return 0;
//    }
//    protected double switchZ(){
//    	return 0;
//    }
}
