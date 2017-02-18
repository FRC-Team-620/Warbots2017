// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc620.Warbot2017.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavX extends Subsystem {
	private AHRS navX;

	public NavX() {
		navX = new AHRS(SerialPort.Port.kUSB);
	}

	public float getYaw() {
		return navX.getYaw();
	}
	public boolean isConnected(){
		return navX.isConnected();
	}
	
	public void reset(){
		navX.reset();
	}

	public void initDefaultCommand() {}
}
