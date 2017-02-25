// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc620.Warbot2017.subsystems;

import java.awt.Robot;

import org.usfirst.frc620.Warbot2017.RobotMap;
import org.usfirst.frc620.Warbot2017.commands.DriveWithXbox;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController pWMJagRL = RobotMap.driveTrainfrontRight;
    SpeedController pWMJagRR = RobotMap.driveTrainbackRight;
    SpeedController pWMJagFL = RobotMap.driveTrainfrontLeft;
    SpeedController pWMJagFR = RobotMap.driveTrainbackLeft;
    RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;
    
    final static double[] vals = {.3, 1};

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    //GyroITG3200 gyro3200 = RobotMap.driveTrainGyro3200;

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	DriveWithXbox DriveWithXbox = new DriveWithXbox();
        setDefaultCommand(DriveWithXbox);
        
    	
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void mecanumDrive(double x,double y,double z, double gyro){
    	if(Math.abs(y) < .25)
    		y = Math.signum(y) * .25;
    	robotDrive.mecanumDrive_Cartesian(x, y, z, gyro);
    }
    
    private static double linearize(double inValue)
    {
    	 int top = (int)Math.ceil(vals.length * Math.abs(inValue));
    	 int bot = (int)Math.floor(vals.length * Math.abs(inValue));
    	 
    	 double val = Math.signum(inValue) * ((Math.abs(inValue) - vals[bot]) * (vals[top] - vals[bot]) + vals[bot]);
    	 if(val < .25)
    		 return 0;
    	 else
    		 return val;
    }
}

