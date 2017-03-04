package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.RobotMap;
import org.usfirst.frc620.Warbot2017.util.DummyPIDOutput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

public class AlignForGearPeg extends Command {
	// private int dist;
	// private double k = -.3;
	// private PIDController distController;
	// private static final double DIST_TOLERANCE = 2;
	// private static final double DIST_P = 0.03;
	// private static final double DIST_I = 0.00;
	// private static final double DIST_D = 0.00;
	// private static final double DIST_F = 0.00;
	// private DummyPIDOutput distOutput;
	private PIDController turnController;
	private static final double TURN_TOLERANCE = 1;
	private static final double TURN_P = 0.03;
	private static final double TURN_I = 0.0002;
	private static final double TURN_D = 0.00;
	private static final double TURN_F = 0.00;
	private DummyPIDOutput turnOutput;

	private PIDController strafeController;
	private static final double STRAFE_TOLERANCE = 5;
	private static final double STRAFE_P = 0.0315;
	private static final double STRAFE_I = 0.00;
	private static final double STRAFE_D = 0.00;
	private static final double STRAFE_F = 0.00;
	private DummyPIDOutput strafeOutput;
	
	private boolean finished = false;

	public AlignForGearPeg(int distInCentimeters, double scaling) {
		// Use requires() here to declare subsystem dependence
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		// dist = distInCentimeters;
		// if(scaling < .25) scaling = .25;
		// k = scaling;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		finished = false;
		RobotMap.visionlightSpike.set(Relay.Value.kForward);
		
		turnOutput = new DummyPIDOutput();
		turnController = new PIDController(TURN_P, TURN_I, TURN_D, TURN_F, Robot.navX, turnOutput);
		turnController.setInputRange(-180.0, 180.0);
		turnController.setOutputRange(-.9, .9);
		turnController.setAbsoluteTolerance(TURN_TOLERANCE);
		turnController.setContinuous(true);
		turnController.setSetpoint(Robot.navX.getYaw());

		strafeOutput = new DummyPIDOutput();
		strafeController = new PIDController(STRAFE_P, STRAFE_I, STRAFE_D, STRAFE_F, Robot.vision, strafeOutput);
		strafeController.setInputRange(0, 320);
		strafeController.setOutputRange(-1, 1);
		strafeController.setAbsoluteTolerance(STRAFE_TOLERANCE);
		strafeController.setContinuous(false);
		strafeController.setSetpoint(54.0);

		// distOutput = new DummyPIDOutput();
		// distController = new PIDController(DIST_P, DIST_I, DIST_D, DIST_F,
		// Robot.lidar, turnOutput);
		// distController = new PIDController(DIST_P, DIST_I, DIST_D, DIST_F,
		// Robot.ultra, distOutput);
		// distController.setInputRange(0, 500);
		// distController.setOutputRange(-1.0, 1.0);
		// distController.setAbsoluteTolerance(DIST_TOLERANCE);
		// distController.setContinuous(false);
		// distController.setSetpoint(dist);

		turnController.enable();
		strafeController.enable();
		// distController.enable();

		// Robot.cameras.switchToCamera(0);

		Robot.cameras.darkenCamera(0);
		Robot.vision.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		double dX = Robot.vision.pidGet();
		System.out.println(dX);
		double straffe = 0;
		final int deadzone = 10;
		if(dX == Double.NaN)
			finished = true;
		else if (dX < -100 - deadzone)
			straffe = -.35;
		else if (dX > -100 + deadzone)
			straffe = .35;
		else
			finished = true;
//		System.out.println(straffe);
//		 System.out.println(-strafeOutput.getOutput());
		Robot.driveTrain.mecanumDrive(straffe, /* distOutput.getOutput() * k */ 0.0, turnOutput.getOutput(), 0.0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timeSinceInitialized() > 3 || finished;
		// return strafeController.onTarget();
		// return distController.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.cameras.brightenCamera(0);
//		RobotMap.visionlightSpike.set(Relay.Value.kOff);
		// distController.disable();
		turnController.disable();
		strafeController.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
