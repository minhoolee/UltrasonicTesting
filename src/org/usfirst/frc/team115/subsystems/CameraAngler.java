package org.usfirst.frc.team115.subsystems;

import org.usfirst.frc.team115.command.UseAngle;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraAngler extends Subsystem {
	private Servo servo;
	
	public CameraAngler() {
		servo = new Servo(0);
		servo.setPosition(0);
	}

	public double returnAngle() {
		return servo.getAngle();
	}
	
	public void reset() {
		servo.setAngle(0);
	}

	public void setSpeed(double speed) {
		servo.set(speed);
	}
	
	public void stop() {
		setSpeed(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UseAngle(Preferences.getInstance().getDouble("Angle", 45)));
	}
}