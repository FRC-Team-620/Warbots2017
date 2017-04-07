package org.usfirst.frc620.Warbot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoNothing extends CommandGroup {

    public DoNothing() {
		addSequential(new RaiseBallMech(2.5));

        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
}
