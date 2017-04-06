package org.usfirst.frc620.Warbot2017.subsystems;

import org.usfirst.frc620.Warbot2017.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BackupNavX extends Subsystem implements PIDSource {
	private AHRS navX;

	public BackupNavX() {
		navX = new AHRS(SerialPort.Port.kMXP); // SerialPort.Port.kUSB
	}

	public AHRS getNavX()
	{
		return navX;
	}
	
	public float getYaw() {
		return navX.getRoll();
	}
	public boolean isConnected(){
		return navX.isConnected();
	}
	
	public void reset(){
		navX.reset();
	}
	
	public void initDefaultCommand() {}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		navX.setPIDSourceType(pidSource);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return navX.getPIDSourceType();
	}

	@Override
	public double pidGet() {
		return Robot.getAngle();
	}
}

