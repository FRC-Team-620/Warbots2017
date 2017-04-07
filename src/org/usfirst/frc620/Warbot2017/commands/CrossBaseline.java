package org.usfirst.frc620.Warbot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaseline extends CommandGroup {

    public CrossBaseline() {
		addParallel(new RaiseBallMech(2.5));
        addSequential(new DriveDistance(96));
    }
}
