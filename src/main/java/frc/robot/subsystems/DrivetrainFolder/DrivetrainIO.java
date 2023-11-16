package frc.robot.subsystems.DrivetrainFolder;

import org.littletonrobotics.junction.AutoLog;

public class DrivetrainIO {

    @AutoLog
    public static class DrivetrainInputs {
        public double leftMotorsPercentOutput;
        public double rightMotorsPercentOutput;

    }

    public void updateInputs(DrivetrainInputs inputs) {}

    public void drive(DrivetrainInputs inputs, double axis1, double axis2) {
        inputs.leftMotorsPercentOutput = axis2;
        inputs.leftMotorsPercentOutput = axis1;

    }
}
