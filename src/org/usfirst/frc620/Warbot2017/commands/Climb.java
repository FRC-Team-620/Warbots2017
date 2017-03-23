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
	boolean madeContact;
	boolean exit;
	Climber climber = Robot.climber;

	public Climb() {
		requires(Robot.climber);
		setInterruptible(false);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// TODO lower ball mech fully
		Scheduler.getInstance().add(new LowerBallMech());
		xbox = Robot.oi.getXbox();
		madeContact = false;
		exit = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
			if (climber.isDown()) {
				// TODO: add drive controls
				climber.climb(0.3325);
				
				 // ensure servo is locked and in the upright position
				//if(RobotMap.ropeServo.get() != 1.0) {
					//RobotMap.ropeServo.set(1.0);
					//System.out.println("Closing servo");
				//}
				
				if (xbox.getRawButton(2))
					exit = true;
			} else if (!climber.isUp()) {
				climber.climb(0.75);
				// TODO: fix this
				// if(++moveTime < 3)
				// Robot.driveTrain.mecanumDrive(0, 0, .4, 0);
				// else
				// Robot.driveTrain.mecanumDrive(0, 0, 0.2, 0);
			} else if (!climber.isInContact())
				climber.climb(1);
			else {
				if (!madeContact) {
					madeContact = true;
				}
				climber.climb(0.3);
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
		System.out.println("Climb Finished");
		climber.kill();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
