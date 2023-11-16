package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * Drivetrain subsystem.
 */

public class DrivetrainIOReal extends DrivetrainIO {
    private final MotorControllerGroup leftMotors =
        new MotorControllerGroup(new VictorSP(4), new VictorSP(5));
    private final MotorControllerGroup rightMotors =
        new MotorControllerGroup(new VictorSP(6), new VictorSP(7));

    public DrivetrainIOReal() {
        rightMotors.setInverted(true);
    }

    @Override
    public void drive(DrivetrainInputs inputs, double axis1, double axis2) {
        inputs.leftMotorsPercentOutput = axis2;
        inputs.rightMotorsPercentOutput = axis1;
        leftMotors.set(axis2);
        rightMotors.set(axis1);
    }

    @Override
    public void updateInputs(DrivetrainInputs inputs) {}

}
