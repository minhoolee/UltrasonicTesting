package org.usfirst.frc.team115.subsystems;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MyServo extends Subsystem{

	private Servo vertServo;
	
	public MyServo() {
		vertServo = new Servo(1);
	}
	
	public double toPosition(double angle) {
		return (angle/180) + 0.5;
	}
	
	public void setAngleOffset(double increaseAngle) {
		double position = toPosition(increaseAngle);
		System.out.println("Position is " + position);
		vertServo.setPosition(vertServo.getPosition() + position);
	}
	
	public double getAngle(Servo servo) {
		return 180 * (servo.getPosition() - 0.5);
	}
	
	public void reset() {
		vertServo.setPosition(0);
	}
	
	public double getAngleLeft(double angle) {
		// in real time you dont want to use absolute value because could take a lot of time, just set and variable and check if less than 0, if so return negative value
		return angle - vertServo.getAngle(); // might have to change get angle, I'm not sure what you want exactly
	}
	@Override
	protected void initDefaultCommand() {}

}
