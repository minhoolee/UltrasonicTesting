
package org.usfirst.frc.team115.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;

import java.net.SocketException;

import org.usfirst.frc.team115.subsystems.UDP;

/**
 * This is a sample program demonstrating how to use an ultrasonic sensor and proportional 
 * control to maintain a set distance from an object.
 * 
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */

public class Robot extends IterativeRobot {
    // Ultrasonics
    public static final int INPUT_FRONT = 0;
	public static final int INPUT_BACK = 1;
	public static final int INPUT_LEFT = 2;
	public static final int INPUT_RIGHT = 3;
    
	private AnalogInput ultrasonicFront;
	private AnalogInput ultrasonicBack;
	private AnalogInput ultrasonicLeft;
	private AnalogInput ultrasonicRight;

	private Servo cameraServo;
	//private CANTalon motor;
	private UDP net;
	
	private static final double ANALOG_SCALE_5V = 0.009766; // 5V / 512
    
    public Robot() {
    	
    	//ultrasonicFront = new AnalogInput(INPUT_FRONT);
		//ultrasonicBack = new AnalogInput(INPUT_BACK);
		//ultrasonicLeft = new AnalogInput(INPUT_LEFT);
		//ultrasonicRight = new AnalogInput(INPUT_RIGHT);
		
		//motor = new CANTalon(2);
		cameraServo = new Servo(0);
		
		// 10.20.89.65 is the IP address of the other side
		net = new UDP("10.20.89.65", 8888);
		
    	//autonomous();
    }

    public void log() {
		//SmartDashboard.putNumber("Ultrasonic [" + INPUT_FRONT + "]", getFrontUltrasonicInches());
		//SmartDashboard.putNumber("Ultrasonic [" + INPUT_BACK + "]", getBackUltrasonicInches());
		//SmartDashboard.putNumber("Ultrasonic [" + INPUT_LEFT + "]", getLeftUltrasonicInches());
		//SmartDashboard.putNumber("Ultrasonic [" + INPUT_RIGHT + "]", getRightUltrasonicInches());
		
		SmartDashboard.putString("Received: ", net.receive());
		
		SmartDashboard.putNumber("Servo Angle: ", cameraServo.getAngle());
	}
    
    /**
     * Runs during autonomous.
     */
    public void autonomous() {
    	
    }
    
    public double getFrontUltrasonicInches(){
		//return ultrasonicFront.getVoltage()/ANALOG_SCALE_3_3V;
		return ultrasonicFront.getVoltage() / ANALOG_SCALE_5V;
	}

	/**
	 * @return the distance from back
	 */
	public double getBackUltrasonicInches(){
		//return ultrasonicBack.getVoltage()/ANALOG_SCALE_3_3V;
		return ultrasonicBack.getVoltage() / ANALOG_SCALE_5V;
	}

	/**
	 * @return the distance from left
	 */
	public double getLeftUltrasonicInches(){
		//return ultrasonicLeft.getVoltage()/ANALOG_SCALE_3_3V;
		return ultrasonicLeft.getVoltage() / ANALOG_SCALE_5V;
	}

	/**
	 * @return the distance from right
	 */
	public double getRightUltrasonicInches(){
		//return ultrasonicRight.getVoltage()/ANALOG_SCALE_3_3V;
		return ultrasonicRight.getVoltage() / ANALOG_SCALE_5V;
	}

    /**
     * Tells the robot to drive to a set distance (in inches) from an object using proportional control.
     */
	/*
    public void operatorControl() {

	double currentDistance; //distance measured from the ultrasonic sensor values
	double currentSpeed; //speed to set the drive train motors
	
	while (isOperatorControl() && isEnabled()) {
            currentDistance = ultrasonic.getValue()*valueToInches; //sensor returns a value from 0-4095 that is scaled to inches 
            currentSpeed = (holdDistance - currentDistance)*pGain; //convert distance error to a motor speed
            myRobot.drive(currentSpeed, 0); //drive robot 
	}
	}
	*/

    /**
     * Runs during test mode
     */
	@Override
    public void teleopPeriodic() {
		log();
		//sendData();
		//receiveData();
		cameraServo.setAngle(30.0);
    }
	
	public void sendData()
	{
		for (int i = 0; i < 10; i++)
		{
			net.send("test" + (i + 10));
			SmartDashboard.putString("Status: ", "Sending");
		}
	}
	
	public void receiveData()
	{
		log();
		SmartDashboard.putString("Status: ", "Receiving");
	}
}
