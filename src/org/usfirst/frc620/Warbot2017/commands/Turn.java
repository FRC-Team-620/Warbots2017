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

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command implements PIDOutput {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private double m_angle;
	private double startAngle;
	private long lastOnTarget = -1;
	private static final double TURN_TOLERANCE = 1;
	private static final double P = 0.03;
	private static final double I = 0.0003;
	private static final double D = 0.00;
	private static final double F = 0.00;
	private PIDController turnController;
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public Turn(double angle) {

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		m_angle = angle;
		Robot.navX.reset();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
		requires(Robot.driveTrain);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Navx init value = " + Robot.navX.getYaw());
		startAngle = Robot.navX.getYaw();
		turnController = new PIDController(P, I, D, F, Robot.navX, this);
		turnController.setInputRange(-180.0, 180.0);
		turnController.setOutputRange(-.3, .3);
		turnController.setAbsoluteTolerance(TURN_TOLERANCE);
		turnController.setContinuous(true);
		double setpoint = startAngle + m_angle;
		if(setpoint > 180){ // This should account for offsets and wrapping around -180 to 180
			setpoint = -360 + setpoint;
		}else if(setpoint < -180){
			setpoint = 360 +setpoint;
		}
		turnController.setSetpoint(setpoint);
		turnController.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
//		System.out.println("NavX:" + Robot.navX.getYaw());
	}

	// Make this return true when this Command no longer needs to rune execute()
	protected boolean isFinished() {
		return turnController.onTarget(); //TODO: add time requirement
	}

	// Called once after isFinished returns true
	protected void end() {
		turnController.disable();
		System.out.println("Navx final value = " + Robot.navX.getYaw());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		turnController.disable();
	}

	@Override
	public void pidWrite(double output) {
		Robot.driveTrain.mecanumDrive(0, 0, output, 0);

	}
}
