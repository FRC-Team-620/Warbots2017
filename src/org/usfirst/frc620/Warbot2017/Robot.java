package org.usfirst.frc620.Warbot2017;

import org.usfirst.frc620.Warbot2017.commands.AutonomousCommand;
import org.usfirst.frc620.Warbot2017.commands.LowerBallMech;
import org.usfirst.frc620.Warbot2017.commands.RaiseGearArm;
import org.usfirst.frc620.Warbot2017.subsystems.BallMech;
import org.usfirst.frc620.Warbot2017.subsystems.ButtonReader;
import org.usfirst.frc620.Warbot2017.subsystems.CameraHandler;
import org.usfirst.frc620.Warbot2017.subsystems.Climber;
import org.usfirst.frc620.Warbot2017.subsystems.DriveTrain;
import org.usfirst.frc620.Warbot2017.subsystems.GearArm;
import org.usfirst.frc620.Warbot2017.subsystems.Lidar;
import org.usfirst.frc620.Warbot2017.subsystems.NavX;
import org.usfirst.frc620.Warbot2017.subsystems.Ultrasonic;
import org.usfirst.frc620.Warbot2017.subsystems.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Climber climber;
	public static GearArm gearArm;
	public static BallMech ballMech;
	public static Lidar lidar;
	public static NavX navX;
	public static Vision vision;
	public static CameraHandler cameras;
	public static Ultrasonic ultra;
	
	ButtonReader a = new ButtonReader(1);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
		driveTrain = new DriveTrain();
		climber = new Climber();
		gearArm = new GearArm();
		ballMech = new BallMech();
		lidar = new Lidar();
		navX = new NavX();
		vision = new Vision();
		cameras = new CameraHandler(1);
		ultra = new Ultrasonic();
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		autonomousCommand = new AutonomousCommand();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		Scheduler.getInstance().add(new RaiseGearArm());
		Scheduler.getInstance().add(new LowerBallMech());
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		Scheduler.getInstance().add(new RaiseGearArm());
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
//		System.out.println("Lidar: " + lidar.getDistance());
//		SmartDashboard.putString("Lidar", "" + lidar.getDistance());
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		System.out.println("NavX = " + navX.getYaw());
//		System.out.println("Lidar = " + lidar.getDistance());
		System.out.println("Ultrasonic value = " + ultra.getDist());
//		a.update(oi.getXbox());
//		if(a.pressed()) 
//			cameras.nextCamera();
	}

	//-----------TEST MODE STUFF------------//
	
	private int testPhase = -1;
	private long timer = 0;
	private boolean toggle = false;
	private ButtonReader test;
	
	@Override
	public void testInit() {
		test = new ButtonReader(1);
		System.out.println("Starting test mode. Ensure that the robot has plenty of space around it, and then press A to continue through the phases of the test.");
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
		test.update(Robot.oi.getXbox());
		if (test.pressed()) {
			testPhase++;

			switch (testPhase) {
			case 0:
				System.out.println("This is the basic drive test. The robot should be moving back and forth.");
				break;
			case 1:
				System.out.println("This is the turning test. The robot should be rotating left and right.");
				break;
			case 2:
				System.out.println("This is the straffe test. The robot should be straffing left and right.");
				break;
			case 3:
				if(cameras.NUMBER_OF_CAMERAS > 0)
					System.out.println("There are no connected cameras to test, press A to skip this phase.");
				else
					System.out.println("This is the camera test. The drive station should be cycling through available cameras.");
				break;
			case 4:
				System.out.println("This is the LIDAR test. Verify that the values shown match proper LIDAR values.");
				break;
			default:
				break;
			}
		}
		if ( System.currentTimeMillis() - timer >= 500) {
			toggle = !toggle;
			timer = System.currentTimeMillis();
			switch (testPhase) {
			case 0:
				Robot.driveTrain.mecanumDrive(toggle ? .25 : -.25, 0, 0, 0);
				break;
			case 1:
				Robot.driveTrain.mecanumDrive(0, toggle ? .25 : -.25, 0, 0);
				break;
			case 2:
				Robot.driveTrain.mecanumDrive(0, 0, toggle ? .25 : -.25, 0);
				break;
			case 3:
				RobotMap.driveTrainRobotDrive.stopMotor();
//				if(cameras.NUMBER_OF_CAMERAS > 0)
//					cameras.nextCamera();
				break;
			case 4:
				System.out.println(lidar.getDistance() + " cm");
				break;
			default:
				break;
			}
		}

	}
}
