package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ConfirmNavx extends InstantCommand {
    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Navx is not connected " + Robot.navX.isConnected());
    }

}
