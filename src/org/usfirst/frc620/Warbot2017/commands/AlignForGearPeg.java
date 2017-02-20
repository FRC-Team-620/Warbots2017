package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class AlignForGearPeg extends Command {
	ITable dataTable;
	
    public AlignForGearPeg() {
        requires(Robot.driveTrain);

        dataTable = NetworkTable.getTable("GRIP").getSubTable("myContoursReport");
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.cameras.switchToCamera(0);
    }

    int cnt = 0;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
//    	cnt++;
//    	if(cnt % 3 != 0)
//    		return;
//    	System.out.println("exec");
    	if(dataTable == null)
    		return;
    	
    	Robot.cameras.darkenCamera(0);
    	
    	double[] centers = dataTable.getNumberArray("centerX", new double[]{});
		double[] sizes = dataTable.getNumberArray("area", new double[]{});
		double[] widths = dataTable.getNumberArray("width", new double[]{});
		
		double center;
		double dist;
		
		//TODO Better contour filtering
		
		if(centers.length == 2) //Two detected segments of reflective tape
		{
			center = centers[0] / 2 + centers[1] / 2;
			dist = Math.abs(centers[0] - centers[1]); //dist gets bigger as the bot gets closer
		}
		else if(centers.length == 3) //If the peg visually "cuts" one of the pieces of tape
		{
			if(sizes.length != 3) //This bit shouldn't ever happen but just in case
			{
				System.out.println("Wrong Number of Sizes");
				return;
			}
			else
			{
				int big = 0;
				for(int i = 0; i < sizes.length; i++) //Figure out which side is being "cut"
					if(sizes[i] > sizes[big])
						big = i;
				
//				System.out.println("zoomp " + Math.abs(centers[(big + 1) % centers.length] - centers[(big + 2) % centers.length])); //Double check that one "piece" is directly over the other
				
				double centerSmall = centers[(big + 1) % centers.length] / 2 + centers[(big + 2) % centers.length] / 2; //Average the x positions of the two segments of the cut piece, which should be almost the same.
				if(centerSmall < centers[big]) //Print out which side is being cut
					System.out.print("(left) ");
				else
					System.out.print("(right) ");
				
				center = centers[big] / 2 + centerSmall / 2;
				dist = Math.abs(centers[big] - centerSmall);
			}
		}
		else //Not detecting the right number of contours for it to be the tape
		{
			System.out.println("Wrong Number of Contours"); //The actual program will know to avoid relying on vision if this happens
			return;
		}
		
		double ang = center - 180;
		
		if(ang < -20)
			Robot.driveTrain.mecanumDrive(.4, 0, 0, 0);
		else if(ang > 20)
			Robot.driveTrain.mecanumDrive(-.4, 0, 0, 0);
		else
			Robot.driveTrain.mecanumDrive(0, 0, 0, 0);
		
		System.out.println("ang:" + ang + " dist:" + dist);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
