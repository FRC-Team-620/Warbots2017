package org.usfirst.frc620.Warbot2017.subsystems;

import edu.wpi.first.wpilibj.XboxController;

public class ButtonReader
{
	private int button;
	private boolean oldVal;
	private boolean lastToggle;
	private boolean togglingVal = false;
	
	public ButtonReader(int button)
	{
		this.button = button;
	}
	
	public void update(XboxController controller)
	{
		lastToggle = oldVal ^ controller.getRawButton(button);
		oldVal = controller.getRawButton(button);
		if(pressed())
			togglingVal = !togglingVal;
	}
	
	public boolean pressed()
	{
		return lastToggle && oldVal;
	}
	
	public boolean released()
	{
		return  lastToggle && !oldVal;
	}
	
	public boolean isOn()
	{
		return togglingVal;
	}
}
