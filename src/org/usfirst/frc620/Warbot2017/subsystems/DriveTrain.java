package org.usfirst.frc620.Warbot2017.subsystems;

import org.usfirst.frc620.Warbot2017.RobotMap;
import org.usfirst.frc620.Warbot2017.commands.DriveWithXbox;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	public double gyro;
	SpeedController pWMJagRL = RobotMap.driveTrainfrontRight;
	SpeedController pWMJagRR = RobotMap.driveTrainbackRight;
	SpeedController pWMJagFL = RobotMap.driveTrainfrontLeft;
	SpeedController pWMJagFR = RobotMap.driveTrainbackLeft;
	RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;

	public DriveTrain() {
		gyro = 0.0;
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithXbox());
	}

	public void mecanumDrive(double x, double y, double z, double gyro) {
		robotDrive.mecanumDrive_Cartesian(x, y, z, this.gyro);
	}
}
