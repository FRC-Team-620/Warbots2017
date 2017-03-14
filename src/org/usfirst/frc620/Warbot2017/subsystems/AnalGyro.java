package org.usfirst.frc620.Warbot2017.subsystems;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AnalGyro extends Subsystem implements PIDSource {
	private AnalogGyro g;

	public AnalGyro() {
//		g = new AnalogGyro(0);
	}
	
	public AnalGyro(int portNum) {
//		g = new AnalogGyro(portNum);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public double get() {
		double a = g.getAngle();
		a = a - (360.0 * (a % 360.0));
		if(a <= 180.0) return a;
		else return -(a - 180.0);
	}

	public double getRaw() {
		return g.getAngle();
	}
	
	public void reset() {
		g.reset();
	}
	
	public AnalogGyro getGyro() {
		return g;
	}

	public void initDefaultCommand() {}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		g.setPIDSourceType(pidSource);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return g.getPIDSourceType();
	}

	@Override
	public double pidGet() {
		return Robot.getAngle();
	}
}
