package org.usfirst.frc.team115.command;

import org.usfirst.frc.team115.robot.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class UseAngle extends PIDCommand{
	private static final double P = 0.0000000025;
	private static final double I = 0.0000008;
	private static final double D = 0.00006;
	private static double angle;
	
	public UseAngle(double angle) {
		super(P, I, D);
		requires(Robot.camAngler);
		this.angle = angle;
	}
	
	@Override
	protected double returnPIDInput() {
		return Robot.camAngler.returnAngle();
	}
	@Override
	protected void usePIDOutput(double output) {
		Robot.camAngler.setSpeed(output);
	}
	
	@Override
	protected void initialize() {
		setInputRange(-20, 20);
		getPIDController().setOutputRange(-0.5, 0.5);
		setSetpoint(this.angle);
		Robot.camAngler.reset();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.camAngler.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
