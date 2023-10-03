package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.AutoLog;

public interface DrivetrainIO {

    @AutoLog
    public static class DrivetrainIOInputs {
        public double drivePositionRad = 0.0;
        public double driveVelocityRadPerSec = 0.0;
        public double driveAppliedVolts = 0.0;

    }

    /** Updates the set of loggable inputs. */
    public default void updateInputs(DrivetrainIOInputs inputs) {}

    /** Run the motor at the specified voltage. */
    public default void setDriveVoltage(double volts) {}


}
