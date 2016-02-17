package org.usfirst.frc.team115.robot;

import edu.wpi.first.wpilibj.command.Command;

public class UseServo extends Command{
	private double vertAngle;
	private double turnAngle;
	
	public UseServo(double vertAngle, double turnAngle) {
		requires(Robot.mysev);
		this.vertAngle = vertAngle;
		this.turnAngle = turnAngle;
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() { //execute runs over and over
		Robot.mysev.setAngleOffset(this.vertAngle, this.turnAngle);
	}
	
	@Override
	protected boolean isFinished() { 
		return true;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
