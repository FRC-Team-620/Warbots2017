package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.util.DummyPIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilDistLidar extends Command {
	private int dist;
	private double k = -1.0;
	private PIDController distController;
	private static final double DIST_TOLERANCE = 2;
	private static final double DIST_P = 0.03;
	private static final double DIST_I = 0.00;
	private static final double DIST_D = 0.00;
	private static final double DIST_F = 0.00;
	private DummyPIDOutput distOutput;
	private PIDController turnController;
	private static final double TURN_TOLERANCE = 5;
	private static final double TURN_P = 0.03;
	private static final double TURN_I = 0.00;
	private static final double TURN_D = 0.00;
	private static final double TURN_F = 0.00;
	private DummyPIDOutput turnOutput;

    public DriveUntilDistLidar(int distInCentimeters, double scaling) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	dist = distInCentimeters;
//    	if(scaling < .25) scaling = .25;
//    	k = scaling;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Starting DriveUntilDist");
    	turnOutput = new DummyPIDOutput();
    	turnController = new PIDController(TURN_P, TURN_I,TURN_D, TURN_F, Robot.navX, turnOutput);
		turnController.setInputRange(-180.0, 180.0);
		turnController.setOutputRange(-.5, .5);
		turnController.setAbsoluteTolerance(TURN_TOLERANCE);
		turnController.setContinuous(true);
		turnController.setSetpoint(Robot.navX.getYaw());
		
		distOutput = new DummyPIDOutput();
//		distController = new PIDController(DIST_P, DIST_I,DIST_D, DIST_F, Robot.lidar, distOutput);
		distController = new PIDController(DIST_P, DIST_I,DIST_D, DIST_F, Robot.betterLidar, distOutput);
		distController.setInputRange(0, 700);
		distController.setOutputRange(-1.0, 1.0);
		distController.setAbsoluteTolerance(DIST_TOLERANCE);
		distController.setContinuous(false);
		distController.setSetpoint(dist);
		
		turnController.enable();
		distController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.mecanumDrive(0.0, distOutput.getOutput() * k, turnOutput.getOutput(), 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("DriveUntilDist finished");
    	distController.disable();
    	turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

