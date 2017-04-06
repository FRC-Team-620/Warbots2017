package org.usfirst.frc620.Warbot2017.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ultrasonic extends Subsystem implements PIDSource {
	private PIDSourceType pid_source_type = PIDSourceType.kDisplacement;
	private AnalogInput sensor;
	
	public Ultrasonic() {
		sensor = new AnalogInput(3);
		sensor.setAverageBits(2);
		sensor.setOversampleBits(4);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	/**
	 * in inches
	 */
	public double getDist() {
		return sensor.getAverageVoltage() / .012446;
	}
	
	public AnalogInput getSensor() {
		return sensor;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand()); 
    }

    @Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		if (pidSource == PIDSourceType.kRate) {
			throw new UnsupportedOperationException(pidSource.name());
		}
		pid_source_type = pidSource;

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pid_source_type;
	}

	@Override
	public double pidGet() {
		if (pid_source_type == PIDSourceType.kRate) {
			return 0;// TODO FIX
		} else {
			return getDist();
		}
	}
}

