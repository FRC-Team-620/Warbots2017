package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.subsystems.Climber;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
*
*/
public class Climb extends Command {
	private XboxController xbox;
	private long buttonTimer;
	boolean madeContact;
	boolean exit;
	// private int moveTime = 0;
	Climber climber = Robot.climber;

	public Climb() {
		// requires(Robot.driveTrain);
		setInterruptible(false);
		Scheduler.getInstance().add(new LowerBallMech());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// TODO lower ball mech fully
		buttonTimer = 0;
		// System.out.println("initialize");
		// Robot.cameras.switchToCamera(2);
		xbox = Robot.oi.getXbox();
		madeContact = false;
		exit = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*if (!Robot.autoClimbEnded)*/ {
			if (climber.isDown()) {
				System.out.println("Down");
				// TODO: add drive controls

				climber.climb(0.3325);
				if (xbox.getRawButton(2))
					exit = true;
			} else if (!climber.isUp()) {
				climber.climb(0.5);
				// TODO: fix this
				// if(++moveTime < 3)
				// Robot.driveTrain.mecanumDrive(0, 0, .4, 0);
				// else
				// Robot.driveTrain.mecanumDrive(0, 0, 0.2, 0);
			} else if (!climber.isInContact())
				climber.climb(0.8);
			else {
				if (!madeContact) {
					buttonTimer = System.currentTimeMillis();
					madeContact = true;
				}
				climber.climb(0.3);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return exit || madeContact;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.autoClimbEnded = true;
		Robot.oi.rBumper.whenPressed(new ManualClimb());
		System.out.println("END");
		climber.kill();
		// Robot.cameras.switchToCamera(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		// Robot.cameras.switchToCamera(0);
		end();
		// throw new Error("CLIMB COMMAND INTERRUPTED. THIS IS VERY
		// DANGEROUS.");
	}
}
