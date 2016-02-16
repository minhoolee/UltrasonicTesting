package org.usfirst.frc.team115.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UseServo extends Command{
	private final double THRESHOLD = 0.05;
	private double actualFinalAngle;
	private double angle;
	
	public UseServo(double angle) {
		requires(Robot.mysev);
		this.angle = angle;
	}
	
	@Override
	protected void initialize() {
		Robot.mysev.reset();
	}

	@Override
	protected void execute() { //execute runs over and over
		Robot.mysev.setAngleOffset(this.angle);
	}
	
	@Override
	protected boolean isFinished() { // everytime execute is run, isFinished checks if you are true or false.rue, then it stops the command
		return Robot.mysev.getAngleLeft(this.angle) <= THRESHOLD; // returns if the angle is less than the threshold of where you are. If you are there, then returns true and the command stops running
	}

	@Override
	protected void end() {
		Robot.mysev.reset();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
