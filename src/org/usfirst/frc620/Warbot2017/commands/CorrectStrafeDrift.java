package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * While the robot is strafing (moving left or right while maintaining it forward/backward position),
 * this command will correct for "significant" twisting of the robot (think yaw) using the navx. What exactly
 * defines "significant" is as of right now undecided. The moment the driver starts to attempt to move forwards,
 * backwards, or turn, this method will stop running and normal control of the robot will resume.
 */
public class CorrectStrafeDrift extends Command {
	private float startingAngle;
    public CorrectStrafeDrift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	startingAngle = Robot.navX.getYaw();
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double strafe = Robot.oi.xbox.getRawAxis(4);
//    	strafe = (Math.abs(strafe) < 0.3) ? 0 : strafe*(1-(Robot.oi.xbox.getRawAxis(5)*.6));
//    	float dTheta = Robot.navX.getYaw() - startingAngle;
//    	double rotate = 0.0;
//    	
//    	if(dTheta > 5) {
//    		// pos turn counterclockwise
//    		rotate = 0.5;
//    	} else if(dTheta < 5) {
//    		rotate = -0.5;
//    	}
//    	
//    	Robot.driveTrain.mecanumDrive(rotate, 0, strafe, 0);
    	double strafe = Robot.oi.getRightXAxis();
    	strafe = (Math.abs(strafe) < 0.3) ? 0 : strafe * (1 - (Robot.oi.getRightXAxis() * .6));
    	float dTheta = Robot.navX.getYaw() - startingAngle;
    	double rotate = 0.0;
    	
    	if(dTheta > 5) {
    		// pos turn counterclockwise
    		rotate = 0.5;
    	} else if(dTheta < 5) {
    		rotate = -0.5;
    	}
    	
    	//Robot.driveTrain.mecanumDrive(rotate, 0, strafe, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !false;
//        return Robot.oi.xbox.getRawAxis(0) != 0.0 || Robot.oi.xbox.getRawAxis(1) != 0.0;
        //return Robot.oi.getXbox().getRawAxis(0) != 0.0 || Robot.oi.getXbox().getRawAxis(1) != 0.0;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
