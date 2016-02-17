package org.usfirst.frc.team115.subsystems;


import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MyServo extends Subsystem{

	private Servo turnServo;
	private Servo vertServo;
	
	public MyServo() {
		turnServo = new Servo(0);
		vertServo = new Servo(1);
	}
	
	public double toPosition(double angle) {
		return (angle/180);
	}
	
	public void setAngleOffset(double increaseVertServoAngle, double increaseTurnServoAngle) {
		vertServo.setPosition(vertServo.getPosition() + toPosition(increaseVertServoAngle));
		turnServo.setPosition(turnServo.getPosition() + toPosition(increaseTurnServoAngle));
	}
	
	@Override
	protected void initDefaultCommand() {}

}
