package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;


public class DrivetrainVictorSP implements DrivetrainIO {
	private final MotorControllerGroup leftMotors =
		new MotorControllerGroup(new VictorSP(4), new VictorSP(5));
	private final MotorControllerGroup rightMotors =
		new MotorControllerGroup(new VictorSP(6), new VictorSP(7));

	/**
	 * Drivetrain VictorSP
	 */
	public DrivetrainVictorSP() {
		rightMotors.setInverted(true);
	}

	/**
	 * Drive Voltage
	 */
	public void setDriveVoltage(double lvolts, double rvolts) {
		leftMotors.set(lvolts);
		rightMotors.set(rvolts);
	}

}
