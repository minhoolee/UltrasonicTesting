
package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.subsystems.MyServo;
import org.usfirst.frc.team115.subsystems.UDP;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	private static int LOOPS_PER_SEC = 10;
    
	private AnalogInput ultrasonicFront;
	private AnalogInput ultrasonicBack;
	private AnalogInput ultrasonicLeft;
	private AnalogInput ultrasonicRight;
	
	public static MyServo mysev;

	private UDP net;
	private static final double ANALOG_SCALE_5V = 0.009766; // 5V / 512
    
    public Robot() {
    	ultrasonicFront = new AnalogInput(INPUT_FRONT);
		ultrasonicBack = new AnalogInput(INPUT_BACK);
		ultrasonicLeft = new AnalogInput(INPUT_LEFT);
		ultrasonicRight = new AnalogInput(INPUT_RIGHT);
		
		mysev = new MyServo();
		
		// 10.1.15.6 is the IP address of the Jetson TK1
		net = new UDP("10.1.15.7", 5810);
    }
    
    public void robotInit() {}

    public void log() {
		//SmartDashboard.putNumber("Ultrasonic [" + INPUT_FRONT + "]", getFrontUltrasonicInches());
		//SmartDashboard.putNumber("Ultrasonic [" + INPUT_BACK + "]", getBackUltrasonicInches());
		//SmartDashboard.putNumber("Ultrasonic [" + INPUT_LEFT + "]", getLeftUltrasonicInches());
		//SmartDashboard.putNumber("Ultrasonic [" + INPUT_RIGHT + "]", getRightUltrasonicInches());
		
		SmartDashboard.putString("Received (String): ", net.getString());
		SmartDashboard.putNumber("Received (Double): ", net.getDouble());
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
	
	@Override
	public void teleopInit() {
		//this.mysev.reset();
		double loopTime = 1.0 / LOOPS_PER_SEC;
		Timer time = new Timer();
		while (true) {
			time.start();
			sendData();
			receiveData();
			this.mysev.setAngleOffset(net.getDouble(), net.getDouble());//TODO need to add second parameter
			time.stop();
			double timeElapsed = time.get();
			if (timeElapsed < loopTime)
				Timer.delay(loopTime - time.get());
			else if (timeElapsed >= loopTime)
				System.out.println("Error: " + timeElapsed + " seconds elapsed, FPS = " + (1 / timeElapsed));
		}
	}

    /**
     * Runs during test mode
     */
	@Override
    public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//log();
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
	}
}
