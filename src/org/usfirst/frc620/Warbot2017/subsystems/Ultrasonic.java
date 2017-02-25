package org.usfirst.frc620.Warbot2017.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ultrasonic extends Subsystem {
	private AnalogInput sensor;
	
	public Ultrasonic() {
		sensor = new AnalogInput(1);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	/**
	 * in inches
	 */
	public double getDist() {
		return sensor.getVoltage() / .012446;
	}
	
	public AnalogInput getSensor() {
		return sensor;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand()); 
    }
}

