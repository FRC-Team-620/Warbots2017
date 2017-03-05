package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DumbDrive extends Command implements PIDOutput {
	private double s, v, startAngle;
	
	private static final double TURN_TOLERANCE = 1;
	private static final double P = 0.03;
	private static final double I = 0.00;
	private static final double D = 0.00;
	private static final double F = 0.00;
	private PIDController turnController;

    public DumbDrive(double seconds, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	s = seconds;
    	v = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startAngle = Robot.navX.getYaw();
		turnController = new PIDController(P, I, D, F, Robot.navX, this);
		turnController.setInputRange(-180.0, 180.0);
		turnController.setOutputRange(-.5, .5);
		turnController.setAbsoluteTolerance(TURN_TOLERANCE);
		turnController.setContinuous(true);
		turnController.setSetpoint(startAngle);
		turnController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() >= s;
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	turnController.disable();
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		Robot.driveTrain.mecanumDrive(0, v, output, 0);
	}
}
