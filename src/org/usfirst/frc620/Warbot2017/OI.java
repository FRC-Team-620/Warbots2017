package org.usfirst.frc620.Warbot2017;

import org.usfirst.frc620.Warbot2017.commands.AlignForGearPeg;
import org.usfirst.frc620.Warbot2017.commands.AutoLeftStart;
import org.usfirst.frc620.Warbot2017.commands.AutoMidStart;
import org.usfirst.frc620.Warbot2017.commands.AutoRightStart;
import org.usfirst.frc620.Warbot2017.commands.AutonomousCommand;
import org.usfirst.frc620.Warbot2017.commands.ClimbDriving;
import org.usfirst.frc620.Warbot2017.commands.ConfirmNavx;
import org.usfirst.frc620.Warbot2017.commands.DepositGear;
import org.usfirst.frc620.Warbot2017.commands.DriveTime;
import org.usfirst.frc620.Warbot2017.commands.DriveUntilDist;
import org.usfirst.frc620.Warbot2017.commands.DriveWithXbox;
import org.usfirst.frc620.Warbot2017.commands.LowerBallMech;
import org.usfirst.frc620.Warbot2017.commands.LowerGearArm;
import org.usfirst.frc620.Warbot2017.commands.RaiseBallMech;
import org.usfirst.frc620.Warbot2017.commands.RaiseGearArm;
import org.usfirst.frc620.Warbot2017.commands.StopsAllThings;
import org.usfirst.frc620.Warbot2017.commands.ToggleRelay;
import org.usfirst.frc620.Warbot2017.commands.ToggleServo;
import org.usfirst.frc620.Warbot2017.commands.Turn;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private XboxController xbox;
	public JoystickButton a;
	public JoystickButton b;
	public JoystickButton x;
	public JoystickButton y;
	public JoystickButton rBumper;
	public JoystickButton back;
	public JoystickButton start;

	public int gyro = 0;

	public OI() {
		xbox = new XboxController(0);

		rBumper = new JoystickButton(xbox, 6);
		rBumper.whenPressed(new ClimbDriving());

		y = new JoystickButton(xbox, 4);
		y.whenPressed(new RaiseGearArm());

		x = new JoystickButton(xbox, 3);
		x.whenPressed(new LowerGearArm());

		a = new JoystickButton(xbox, 1);
		a.whenPressed(new LowerBallMech());

		b = new JoystickButton(xbox, 2);
		b.whenPressed(new RaiseBallMech());

		back = new JoystickButton(xbox, 7);
		back.whenPressed(new StopsAllThings());

		start = new JoystickButton(xbox, 8);
		start.whenPressed(new ToggleServo());

		// SmartDashboard Buttons 
		SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
		SmartDashboard.putData("DriveWithXbox", new DriveWithXbox());
		SmartDashboard.putData("RaiseGearArm", new RaiseGearArm());
		SmartDashboard.putData("LowerGearArm", new LowerGearArm());
		SmartDashboard.putData("DepositGear", new DepositGear());
		SmartDashboard.putData("AutoLeftStart", new AutoLeftStart());
		SmartDashboard.putData("AutoRightStart", new AutoRightStart());
		SmartDashboard.putData("AutoMidStart", new AutoMidStart());
		SmartDashboard.putData("DriveUntilDist (20)", new DriveUntilDist(20, .5));
		SmartDashboard.putData("Drive With Vision (30)", new AlignForGearPeg(30, .3));
		SmartDashboard.putData("AlignForGearPeg ", new AlignForGearPeg(0, 1.0));
		SmartDashboard.putData("ToggleRelay", new ToggleRelay());
		SmartDashboard.putData("DriveTime 9", new DriveTime(-24));
		SmartDashboard.putData("Lower .5", new LowerGearArm(.5));
		SmartDashboard.putData("Turn(90)", new Turn(90));
		SmartDashboard.putData("Turn(-90)", new Turn(-90));
		SmartDashboard.putData("Toggle Servo", new ToggleServo());
		SmartDashboard.putData("Is the NavX working???", new ConfirmNavx());
		SmartDashboard.putData("Darken Camera", new InstantCommand() {
			@Override
			protected void execute() {
				Robot.cameras.darkenCamera(0);
			}
		});
		SmartDashboard.putData("Lighten Camera", new InstantCommand() {
			@Override
			protected void execute() {
				Robot.cameras.brightenCamera(0);
			}
		});
	}

	public XboxController getXbox() {
		return xbox;
	}

	public boolean getLBumper() {
		return xbox.getRawButton(5);
	}

	public boolean getRBumper() {
		return xbox.getRawButton(6);
	}

	public double getRightXAxis() {
		return xbox.getRawAxis(4);
	}

	public double getLTrigger() {
		return xbox.getRawAxis(2);
	}

	public double getRTrigger() {
		return xbox.getRawAxis(3);
	}
}