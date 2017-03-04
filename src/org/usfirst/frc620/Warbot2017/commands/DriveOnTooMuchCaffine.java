package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.util.DummyPIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveOnTooMuchCaffine extends Command {
	private int dist;
	private double k = -.25;
	private double turn;
	private PIDController distController;
	private static final double DIST_TOLERANCE = 2;
	private static final double DIST_P = 0.03;
	private static final double DIST_I = 0.00;
	private static final double DIST_D = 0.00;
	private static final double DIST_F = 0.00;
	private DummyPIDOutput distOutput;

    public DriveOnTooMuchCaffine(int distInCentimeters, double scaling) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	dist = distInCentimeters;
//    	if(scaling < .25) scaling = .25;
//    	k = scaling;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		distOutput = new DummyPIDOutput();
//		distController = new PIDController(DIST_P, DIST_I,DIST_D, DIST_F, Robot.lidar, distOutput);
		distController = new PIDController(DIST_P, DIST_I,DIST_D, DIST_F, Robot.ultra, distOutput);
		distController.setInputRange(0, 500);
		distController.setOutputRange(-.5, .5);
		distController.setAbsoluteTolerance(DIST_TOLERANCE);
		distController.setContinuous(false);
		distController.setSetpoint(dist);
		distController.enable();
		
		turn = .3;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turn = -turn;
    	Robot.driveTrain.mecanumDrive(0.0, distOutput.getOutput() * k, turn, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	distController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
